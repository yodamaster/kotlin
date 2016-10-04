package org.jetbrains.kotlin.gradle.tasks

import org.jetbrains.kotlin.annotation.AnnotationFileUpdater
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.incremental.DirtyData
import org.jetbrains.kotlin.incremental.IncReporter
import org.jetbrains.kotlin.incremental.LookupSymbol
import org.jetbrains.kotlin.name.FqName
import java.io.File
import java.util.*

internal class KaptFileUpdateExtension(private var annotationFileUpdater: AnnotationFileUpdater?) : IncrementalJvmCompilerRunner.Extension {
    override val name: String
        get() = "KaptFileUpdateExtension"

    override fun beforeBuild(buildState: IncrementalJvmCompilerRunner.BuildState) {
        if (buildState.compilationMode is IncrementalJvmCompilerRunner.CompilationMode.Rebuild) {
            // there is no point in updating annotation file since all files will be compiled anyway
            annotationFileUpdater = null
        }
    }

    override fun afterCompileIteration(
            buildState: IncrementalJvmCompilerRunner.BuildState,
            compileIterationState: IncrementalJvmCompilerRunner.CompileIterationState
    ) {
        if (buildState.exitCode == ExitCode.OK) {
            annotationFileUpdater?.updateAnnotations(compileIterationState.outdatedClasses)
        } else {
            annotationFileUpdater?.revert()
        }
    }
}

internal class MultiProjectICExtension(
        private val reporter: IncReporter,
        private val artifactDifferenceRegistryProvider: ArtifactDifferenceRegistryProvider,
        private val outputArtifact: File
) : IncrementalJvmCompilerRunner.Extension, IncrementalJvmCompilerRunner.ClasspathChangesProvider {
    override val name: String
        get() = "MultiProjectICExtension"

    override fun onCacheCorruption() {
        artifactDifferenceRegistryProvider.clean()
    }

    override fun afterCompileIteration(
            buildState: IncrementalJvmCompilerRunner.BuildState,
            compileIterationState: IncrementalJvmCompilerRunner.CompileIterationState
    ) {
        if (buildState.exitCode != ExitCode.OK || buildState.compilationMode is IncrementalJvmCompilerRunner.CompilationMode.Rebuild) {
            artifactDifferenceRegistryProvider.withRegistry(reporter) { registry ->
                registry.remove(outputArtifact)
            }
        }
    }

    override fun afterBuild(buildState: IncrementalJvmCompilerRunner.BuildState) {
        val buildTS = buildState.buildInfo.startTS
        val dirtyData = DirtyData(buildState.dirtyLookupSymbols, buildState.dirtyFqNames)
        val artifactDifference = ArtifactDifference(buildTS, dirtyData)
        artifactDifferenceRegistryProvider.withRegistry(reporter) {registry ->
            registry.add(outputArtifact, artifactDifference)
        }

        reporter.report {
            val dirtySymbolsSorted = buildState.dirtyLookupSymbols.map { it.scope + "#" + it.name }.sorted()
            "Added artifact difference for $outputArtifact (ts: $buildTS): " +
                    "[\n\t${dirtySymbolsSorted.joinToString(",\n\t")}]"
        }
    }

    override fun getClasspathChanges(
            modifiedClasspath: List<File>,
            lastBuildInfo: BuildInfo?
    ): ChangesEither {
        if (modifiedClasspath.isEmpty()) {
            reporter.report {"No classpath changes"}
            return ChangesEither.Known()
        }

        val lastBuildTS = lastBuildInfo?.startTS
        if (lastBuildTS == null) {
            reporter.report {"Could not determine last build timestamp"}
            return ChangesEither.Unknown()
        }

        val symbols = HashSet<LookupSymbol>()
        val fqNames = HashSet<FqName>()
        for (file in modifiedClasspath) {
            val diffs = artifactDifferenceRegistryProvider.withRegistry(reporter) {artifactDifferenceRegistry ->
                artifactDifferenceRegistry[file]
            }
            if (diffs == null) {
                reporter.report {"Could not get changes for file: $file"}
                return ChangesEither.Unknown()
            }

            val (beforeLastBuild, afterLastBuild) = diffs.partition {it.buildTS < lastBuildTS}
            if (beforeLastBuild.isEmpty()) {
                reporter.report {"No known build preceding timestamp $lastBuildTS for file $file"}
                return ChangesEither.Unknown()
            }

            afterLastBuild.forEach {
                symbols.addAll(it.dirtyData.dirtyLookupSymbols)
                fqNames.addAll(it.dirtyData.dirtyClassesFqNames)
            }
        }

        return ChangesEither.Known(symbols, fqNames)
    }
}
