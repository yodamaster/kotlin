package org.jetbrains.kotlin.incremental.kapt

import org.jetbrains.kotlin.annotation.AnnotationFileUpdater
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.incremental.IncrementalJvmCompilerRunner

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