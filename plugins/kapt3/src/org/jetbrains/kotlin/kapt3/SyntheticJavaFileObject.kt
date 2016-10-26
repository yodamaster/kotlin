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

package org.jetbrains.kotlin.kapt3

import com.sun.tools.javac.file.BaseFileObject
import com.sun.tools.javac.file.JavacFileManager
import com.sun.tools.javac.tree.JCTree
import java.io.*
import java.net.URI
import java.net.URL
import javax.lang.model.element.Modifier
import javax.lang.model.element.NestingKind
import javax.tools.JavaFileObject

class SyntheticJavaFileObject(
        val compilationUnit: JCTree.JCCompilationUnit,
        val clazz: JCTree.JCClassDecl,
        val timestamp: Long,
        fileManager: JavacFileManager
) : BaseFileObject(fileManager) {
    override fun getShortName() = clazz.simpleName.toString()

    override fun inferBinaryName(path: MutableIterable<File>?) = throw UnsupportedOperationException()

    override fun isNameCompatible(simpleName: String?, kind: JavaFileObject.Kind?) = true

    override fun getKind() = JavaFileObject.Kind.SOURCE

    override fun getName() = compilationUnit.packageName.toString().replace('.', '/') + clazz.name + ".java"

    override fun getAccessLevel(): Modifier? {
        val flags = clazz.modifiers.getFlags()
        if (Modifier.PUBLIC in flags) return Modifier.PUBLIC
        if (Modifier.PROTECTED in flags) return Modifier.PROTECTED
        if (Modifier.PRIVATE in flags) return Modifier.PRIVATE
        return null
    }

    override fun openInputStream() = getCharContent(false).byteInputStream()

    override fun getCharContent(ignoreEncodingErrors: Boolean) = compilationUnit.toString()

    override fun getNestingKind() = NestingKind.TOP_LEVEL

    override fun toUri(): URI? = URL(name).toURI()

    override fun openReader(ignoreEncodingErrors: Boolean) = getCharContent(ignoreEncodingErrors).reader()

    override fun openWriter() = throw UnsupportedOperationException()

    override fun getLastModified() = timestamp

    override fun openOutputStream() = throw UnsupportedOperationException()

    override fun delete() = throw UnsupportedOperationException()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as SyntheticJavaFileObject

        if (compilationUnit != other.compilationUnit) return false
        if (clazz != other.clazz) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + compilationUnit.hashCode()
        result = 31 * result + clazz.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}