/*
 * Copyright 2010-2015 JetBrains s.r.o.
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

package org.jetbrains.kotlin.resolve.jvm

import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.DelegatingGlobalSearchScope
import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.analyzer.ModuleContent
import org.jetbrains.kotlin.analyzer.ModuleInfo
import org.jetbrains.kotlin.builtins.JvmBuiltInsPackageFragmentProvider
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.JVMConfigurationKeys
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl
import org.jetbrains.kotlin.container.ComponentProvider
import org.jetbrains.kotlin.container.get
import org.jetbrains.kotlin.context.ContextForNewModule
import org.jetbrains.kotlin.context.MutableModuleContext
import org.jetbrains.kotlin.context.ProjectContext
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.PackagePartProvider
import org.jetbrains.kotlin.frontend.java.di.initJvmBuiltInsForTopDownAnalysis
import org.jetbrains.kotlin.incremental.components.LookupTracker
import org.jetbrains.kotlin.load.java.structure.impl.JavaClassImpl
import org.jetbrains.kotlin.load.kotlin.DeserializationComponentsForJava
import org.jetbrains.kotlin.load.kotlin.incremental.IncrementalPackageFragmentProvider
import org.jetbrains.kotlin.load.kotlin.incremental.IncrementalPackagePartProvider
import org.jetbrains.kotlin.modules.TargetId
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.platform.JvmBuiltIns
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.CompilerEnvironment
import org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer
import org.jetbrains.kotlin.resolve.TopDownAnalysisMode
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisCompletedHandlerExtension
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactory
import org.jetbrains.kotlin.resolve.lazy.declarations.FileBasedDeclarationProviderFactory
import org.jetbrains.kotlin.storage.StorageManager

object TopDownAnalyzerFacadeForJVM {
    @JvmStatic
    @JvmOverloads
    fun analyzeFilesWithJavaIntegration(
            project: Project,
            files: Collection<KtFile>,
            trace: BindingTrace,
            configuration: CompilerConfiguration,
            packagePartProvider: (GlobalSearchScope) -> PackagePartProvider,
            declarationProviderFactory: (StorageManager, Collection<KtFile>) -> DeclarationProviderFactory = ::FileBasedDeclarationProviderFactory,
            sourceModuleSearchScope: GlobalSearchScope = newModuleSearchScope(project, files)
    ): AnalysisResult {
        val container = createContainer(
                project, files, trace, configuration, packagePartProvider, declarationProviderFactory, sourceModuleSearchScope
        )

        container.get<LazyTopDownAnalyzer>().analyzeDeclarations(TopDownAnalysisMode.TopLevelDeclarations, files)

        val module = container.get<ModuleDescriptor>()
        for (extension in AnalysisCompletedHandlerExtension.getInstances(project)) {
            val result = extension.analysisCompleted(project, module, trace, files)
            if (result != null) return result
        }

        return AnalysisResult.success(trace.bindingContext, module)
    }

    fun createContainer(
            project: Project,
            files: Collection<KtFile>,
            trace: BindingTrace,
            configuration: CompilerConfiguration,
            packagePartProvider: (GlobalSearchScope) -> PackagePartProvider,
            declarationProviderFactory: (StorageManager, Collection<KtFile>) -> DeclarationProviderFactory,
            sourceModuleSearchScope: GlobalSearchScope = newModuleSearchScope(project, files)
    ): ComponentProvider {
        val incrementalComponents = configuration.get(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS)
        val lookupTracker = incrementalComponents?.getLookupTracker() ?: LookupTracker.DO_NOTHING
        val targetIds = configuration.get(JVMConfigurationKeys.MODULES)?.map(::TargetId)

        val separateModules = !configuration.getBoolean(JVMConfigurationKeys.USE_SINGLE_MODULE)
        val dependOnBuiltIns = configuration.getBoolean(JVMConfigurationKeys.ADD_BUILT_INS_TO_DEPENDENCIES)

        val sourceScope = if (separateModules) sourceModuleSearchScope else GlobalSearchScope.allScope(project)
        // Scope for the dependency module contains everything except files present in the scope for the source module
        val dependencyScope = GlobalSearchScope.notScope(sourceScope)

        val languageVersionSettings =
                configuration.get(CommonConfigurationKeys.LANGUAGE_VERSION_SETTINGS, LanguageVersionSettingsImpl.DEFAULT)

        val moduleName = configuration.getNotNull(CommonConfigurationKeys.MODULE_NAME)
        val dependencyModuleInfo = JvmModuleInfo(
                Name.special("<dependencies of $moduleName>"), ModuleContent(emptySet(), dependencyScope), dependOnBuiltIns, isLibrary = true
        )
        val sourceModuleInfo = JvmModuleInfo(
                Name.special("<$moduleName>"), ModuleContent(files, sourceScope), dependOnBuiltIns, listOf(dependencyModuleInfo)
        )

        val projectContext = ProjectContext(project)
        val storageManager = projectContext.storageManager
        val builtIns = JvmBuiltIns(storageManager)
        val resolverForProject = JvmAnalyzerFacade.setupResolverForProject(
                "JVM project",
                projectContext,
                listOf(dependencyModuleInfo, sourceModuleInfo),
                JvmModuleInfo::content,
                JvmPlatformParameters(
                        trace, languageVersionSettings, lookupTracker, useBuiltInsProvider = true, useLazyResolve = false,
                        moduleByJavaClass = { javaClass ->
                            val isFromSourceModule = javaClass is JavaClassImpl && javaClass.psi.containingFile.virtualFile in sourceScope
                            if (isFromSourceModule) sourceModuleInfo else dependencyModuleInfo
                        },
                        moduleParameters = { moduleInfo ->
                            when (moduleInfo) {
                                sourceModuleInfo -> JvmModuleParameters(declarationProviderFactory(storageManager, files)) { container ->
                                    if (incrementalComponents != null && targetIds != null) {
                                        targetIds.map { targetId ->
                                            IncrementalPackageFragmentProvider(
                                                    files, container.get<ModuleDescriptor>(), storageManager,
                                                    container.get<DeserializationComponentsForJava>().components,
                                                    incrementalComponents.getIncrementalCache(targetId), targetId
                                            )
                                        }
                                    }
                                    else emptyList()
                                }
                                dependencyModuleInfo -> JvmModuleParameters(DeclarationProviderFactory.EMPTY) { container ->
                                    listOf(container.get<JvmBuiltInsPackageFragmentProvider>())
                                }
                                else -> error("Unexpected module info: $moduleInfo")
                            }
                        }
                ),
                CompilerEnvironment,
                builtIns,
                packagePartProviderFactory = { moduleInfo, _ ->
                    when (moduleInfo) {
                        sourceModuleInfo -> IncrementalPackagePartProvider.create(
                                packagePartProvider(sourceScope), targetIds, incrementalComponents, storageManager
                        )
                        dependencyModuleInfo -> packagePartProvider(dependencyScope)
                        else -> error("Unexpected module info: $moduleInfo")
                    }
                }
        )

        // It's necessary to create container for dependencies _before_ creation of container for sources because
        // CliLightClassGenerationSupport#initialize is invoked when container is created, so only the last module descriptor is going
        // to be stored in CliLightClassGenerationSupport, and it better be the source one (otherwise light classes would not be found)
        // TODO: get rid of duplicate invocation of CodeAnalyzerInitializer#initialize, or refactor CliLightClassGenerationSupport
        resolverForProject.resolverForModule(dependencyModuleInfo)

        val resolverForModule = resolverForProject.resolverForModule(sourceModuleInfo)
        return resolverForModule.componentProvider.apply {
            initJvmBuiltInsForTopDownAnalysis(resolverForProject.descriptorForModule(sourceModuleInfo), languageVersionSettings)
        }
    }

    class JvmModuleInfo(
            override val name: Name,
            val content: ModuleContent,
            val dependOnBuiltIns: Boolean,
            dependencies: List<ModuleInfo> = emptyList(),
            override val isLibrary: Boolean = false
    ) : ModuleInfo {
        private val dependencies = listOf(this) + dependencies

        override fun dependencies(): List<ModuleInfo> = dependencies

        override fun dependencyOnBuiltIns(): ModuleInfo.DependencyOnBuiltIns =
                if (dependOnBuiltIns) ModuleInfo.DependenciesOnBuiltIns.LAST else ModuleInfo.DependenciesOnBuiltIns.NONE

        override fun modulesWhoseInternalsAreVisible(): Collection<ModuleInfo> = dependencies.subList(1, dependencies.size)
    }

    fun newModuleSearchScope(project: Project, files: Collection<KtFile>): GlobalSearchScope {
        // In case of separate modules, the source module scope generally consists of the following scopes:
        // 1) scope which only contains passed Kotlin source files (.kt and .kts)
        // 2) scope which contains all Java source files (.java) in the project
        return GlobalSearchScope.filesScope(project, files.map { it.virtualFile }.toSet()).uniteWith(AllJavaSourcesInProjectScope(project))
    }

    // TODO: limit this scope to the Java source roots, which the module has in its CONTENT_ROOTS
    class AllJavaSourcesInProjectScope(project: Project) : DelegatingGlobalSearchScope(GlobalSearchScope.allScope(project)) {
        // 'isDirectory' check is needed because otherwise directories such as 'frontend.java' would be recognized
        // as Java source files, which makes no sense
        override fun contains(file: VirtualFile) =
                file.fileType === JavaFileType.INSTANCE && !file.isDirectory

        override fun toString() = "All Java sources in the project"
    }

    fun createContextWithSealedModule(project: Project, configuration: CompilerConfiguration): MutableModuleContext =
            createModuleContext(project, configuration).apply {
                setDependencies(module, module.builtIns.builtInsModule)
            }

    private fun createModuleContext(project: Project, configuration: CompilerConfiguration): MutableModuleContext {
        val projectContext = ProjectContext(project)
        return ContextForNewModule(
                projectContext, Name.special("<${configuration.getNotNull(CommonConfigurationKeys.MODULE_NAME)}>"),
                JvmBuiltIns(projectContext.storageManager)
        )
    }
}
