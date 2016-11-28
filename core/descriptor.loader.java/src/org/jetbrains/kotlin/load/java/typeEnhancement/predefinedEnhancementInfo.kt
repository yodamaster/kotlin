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

package org.jetbrains.kotlin.load.java.typeEnhancement

import org.jetbrains.kotlin.load.kotlin.signatures

class TypeEnhancementInfo(val map: Map<Int, JavaTypeQualifiers>) {
    constructor(vararg pairs: Pair<Int, JavaTypeQualifiers>) : this(mapOf(*pairs))
}

class PredefinedFunctionEnhancementInfo(
        val returnTypeInfo: TypeEnhancementInfo? = null,
        val parametersInfo: List<TypeEnhancementInfo?> = emptyList()
)

private val NULLABLE = JavaTypeQualifiers(NullabilityQualifier.NULLABLE, null, isNotNullTypeParameter = false)
private val NOT_NULLABLE = JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, isNotNullTypeParameter = false)
private val NOT_NULLABLE_T = JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, isNotNullTypeParameter = true)

val PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE = signatures {
    mapOf(
            signature(javaUtil("Iterator"), "forEachRemaining(Ljava/util/function/Consumer;)V") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(
                                    TypeEnhancementInfo(
                                            0 to NOT_NULLABLE,
                                            1 to NOT_NULLABLE
                                    )
                            )
                    ),
            signature(javaUtil("Collection"), "removeIf(Ljava/util/function/Predicate;)Z") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(TypeEnhancementInfo(0 to NOT_NULLABLE, 1 to NOT_NULLABLE))
                    ),
            signature(javaUtil("Map"), "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(
                                    TypeEnhancementInfo(0 to NOT_NULLABLE),
                                    TypeEnhancementInfo(0 to NOT_NULLABLE_T),
                                    TypeEnhancementInfo(0 to NOT_NULLABLE, 1 to NOT_NULLABLE_T, 2 to NOT_NULLABLE_T, 3 to NULLABLE)
                            ),
                            returnTypeInfo = TypeEnhancementInfo(0 to NULLABLE)
                    ),
            signature("java/util/function/Consumer", "accept(Ljava/lang/Object;)V") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(TypeEnhancementInfo(0 to NOT_NULLABLE))
                    ),
            signature("java/util/function/Predicate", "test(Ljava/lang/Object;)Z") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(TypeEnhancementInfo(0 to NOT_NULLABLE))
                    ),
            signature("java/util/function/Function", "apply(Ljava/lang/Object;)Ljava/lang/Object;") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(TypeEnhancementInfo(0 to NOT_NULLABLE)),
                            returnTypeInfo = TypeEnhancementInfo(0 to NOT_NULLABLE)
                    ),
            signature("java/util/function/BiFunction", "apply(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(
                                    TypeEnhancementInfo(0 to NOT_NULLABLE),
                                    TypeEnhancementInfo(0 to NOT_NULLABLE)
                            ),
                            returnTypeInfo = TypeEnhancementInfo(0 to NOT_NULLABLE)
                    ),
            signature(javaUtil("Optional"), "empty()Ljava/util/Optional;") to
                    PredefinedFunctionEnhancementInfo(
                            returnTypeInfo = TypeEnhancementInfo(0 to NOT_NULLABLE, 1 to NOT_NULLABLE_T)
                    ),
            signature(javaUtil("Optional"), "of(Ljava/lang/Object;)Ljava/util/Optional;") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(TypeEnhancementInfo(0 to NOT_NULLABLE_T)),
                            returnTypeInfo = TypeEnhancementInfo(0 to NOT_NULLABLE, 1 to NOT_NULLABLE_T)
                    ),
            signature(javaUtil("Optional"), "ofNullable(Ljava/lang/Object;)Ljava/util/Optional;") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(TypeEnhancementInfo(0 to NULLABLE)),
                            returnTypeInfo = TypeEnhancementInfo(0 to NOT_NULLABLE, 1 to NOT_NULLABLE_T)
                    ),
            signature(javaUtil("Optional"), "get()Ljava/lang/Object;") to
                    PredefinedFunctionEnhancementInfo(
                            returnTypeInfo = TypeEnhancementInfo(0 to NOT_NULLABLE)
                    ),
            signature(javaUtil("Optional"), "ifPresent(Ljava/util/function/Consumer;)V") to
                    PredefinedFunctionEnhancementInfo(
                            parametersInfo = listOf(TypeEnhancementInfo(0 to NOT_NULLABLE, 1 to NOT_NULLABLE_T))
                    )
    )

}
