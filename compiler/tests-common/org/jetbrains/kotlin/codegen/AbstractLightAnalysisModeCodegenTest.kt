/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.codegen

import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.TopDownAnalysisMode
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisHandlerExtension
import java.io.File

abstract class AbstractLightAnalysisModeCodegenTest : AbstractBytecodeListingTest() {
    override val classBuilderFactory: ClassBuilderFactory
        get() = ClassBuilderFactories.TEST_LIGHT_CLASSES

    override fun setupEnvironment(environment: KotlinCoreEnvironment) {
        AnalysisHandlerExtension.registerExtension(environment.project, object : AnalysisHandlerExtension {
            override fun beforeAnalysis(project: Project, module: ModuleDescriptor, files: Collection<KtFile>, bindingTrace: BindingTrace) {
                bindingTrace.record(BindingContext.TOP_DOWN_ANALYSIS_MODE, Unit, TopDownAnalysisMode.Light)
            }
        })
    }

    override fun getTextFile(ktFile: File): File {
        val boxTestsDir = File("compiler/testData/codegen/box")
        val outDir = File("compiler/testData/codegen/light-analysis", ktFile.toRelativeString(boxTestsDir)).parent
        return File(outDir, ktFile.nameWithoutExtension + ".txt")
    }
}
