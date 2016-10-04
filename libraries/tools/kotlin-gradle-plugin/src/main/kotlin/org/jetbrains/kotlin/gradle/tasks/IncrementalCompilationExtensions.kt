package org.jetbrains.kotlin.gradle.tasks

import org.jetbrains.kotlin.annotation.AnnotationFileUpdater
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.resolve.jvm.JvmClassName

internal class KaptFileUpdateExtension(private var annotationFileUpdater: AnnotationFileUpdater?) : IncrementalJvmCompilerRunner.Extension() {
    override fun beforeCompile(compilationMode: IncrementalJvmCompilerRunner.CompilationMode) {
        if (compilationMode is IncrementalJvmCompilerRunner.CompilationMode.Rebuild) {
            // there is no point in updating annotation file since all files will be compiled anyway
            annotationFileUpdater = null
        }
    }

    override fun afterCompileIteration(exitCode: ExitCode, outdatedClasses: Iterable<JvmClassName>) {
        if (exitCode == ExitCode.OK) {
            annotationFileUpdater?.updateAnnotations(outdatedClasses)
        } else {
            annotationFileUpdater?.revert()
        }
    }
}