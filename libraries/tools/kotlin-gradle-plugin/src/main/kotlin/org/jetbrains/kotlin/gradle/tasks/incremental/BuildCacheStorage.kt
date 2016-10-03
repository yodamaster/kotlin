package org.jetbrains.kotlin.gradle.tasks.incremental

import org.gradle.api.logging.Logging
import org.jetbrains.kotlin.gradle.plugin.kotlinDebug
import org.jetbrains.kotlin.gradle.tasks.ArtifactDifferenceRegistry
import org.jetbrains.kotlin.gradle.tasks.ArtifactDifferenceRegistryProvider
import org.jetbrains.kotlin.incremental.CacheVersion
import org.jetbrains.kotlin.incremental.storage.BasicMapsOwner
import java.io.File

/**
 * "Global" cache holder. Should be created once per root project.
 */
internal class BuildCacheStorage(workingDir: File) : BasicMapsOwner(), ArtifactDifferenceRegistryProvider {
    companion object {
        private val OWN_VERSION = 0
        private val ARTIFACT_DIFFERENCE = "artifact-difference"
    }

    private val log = Logging.getLogger(this.javaClass)
    private val cachesDir: File = File(workingDir, "caches").apply { mkdirs() }
    private val versionFile = File(cachesDir, "version.txt")
    private val version = CacheVersion(
            OWN_VERSION,
            versionFile,
            whenVersionChanged = CacheVersion.Action.REBUILD_ALL_KOTLIN,
            whenTurnedOff = CacheVersion.Action.REBUILD_ALL_KOTLIN,
            whenTurnedOn = CacheVersion.Action.REBUILD_ALL_KOTLIN,
            // assume it's always enabled for simplicity (if IC is not enabled, just don't write to cache)
            isEnabled = { true })

    private var artifactDifferenceRegistry: ArtifactDifferenceRegistryImpl? = null

    private val String.storageFile: File
        get() = File(cachesDir, this + "." + CACHE_EXTENSION)

    @Synchronized
    override fun <T> withRegistry(report: (String)->Unit, fn: (ArtifactDifferenceRegistry)->T): T? {
        try {
            if (artifactDifferenceRegistry == null) {
                artifactDifferenceRegistry = registerMap(ArtifactDifferenceRegistryImpl(ARTIFACT_DIFFERENCE.storageFile))
            }

            return fn(artifactDifferenceRegistry!!)
        }
        catch (e: Throwable) {
            clean()
            report("Error accessing artifact file difference registry: $e")
        }

        return null
    }

    init {
        if (version.checkVersion() != CacheVersion.Action.DO_NOTHING) {
            log.kotlinDebug { "Cache version is not up-to-date" }
            clean()
        }
    }

    override fun clean() {
        try {
            close()
        }
        catch (e: Throwable) {
            log.kotlinDebug { "Exception while closing caches: $e" }
        }

        cachesDir.deleteRecursively()
        cachesDir.mkdirs()
        versionFile.delete()
    }

    override fun close() {
        super.close()
        version.saveIfNeeded()
    }
}