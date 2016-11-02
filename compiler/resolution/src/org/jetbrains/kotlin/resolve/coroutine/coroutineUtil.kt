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

package org.jetbrains.kotlin.resolve.coroutine

import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameUnsafe
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.resolve.scopes.receivers.ExtensionReceiver
import org.jetbrains.kotlin.resolve.scopes.utils.getImplicitReceiversHierarchy
import org.jetbrains.kotlin.types.KotlinType

val SUSPENSION_POINT_KEY: FunctionDescriptor.UserDataKey<Boolean> = object : FunctionDescriptor.UserDataKey<Boolean> {}
val REPLACED_SUSPENSION_POINT_KEY: FunctionDescriptor.UserDataKey<Boolean> = object : FunctionDescriptor.UserDataKey<Boolean> {}

fun KotlinType.isValidContinuation() =
        (constructor.declarationDescriptor as? ClassDescriptor)?.fqNameUnsafe == DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME.toUnsafe()

fun ValueParameterDescriptor.isImplicitControllerParameter() =
        index == containingDeclaration.valueParameters.size - 1 && !type.isValidContinuation() &&
            (containingDeclaration as? SimpleFunctionDescriptor)?.getSuspensionPointReturnType() != null

// Returns suspension function as it's visible within coroutines:
// E.g. `fun <V> await(f: CompletableFuture<V>): V` instead of `fun <V> await(f: CompletableFuture<V>, machine: Continuation<V>): Unit`
fun <D : CallableDescriptor> D.createCoroutineSuspensionFunctionView(): D? {
    if (this !is SimpleFunctionDescriptor) return null
    if (!isSuspend) return null
    val returnType = getSuspensionPointReturnType() ?: return null

    val newOriginal =
            if (original !== this)
                original.createCoroutineSuspensionFunctionView()
            else null

    val newValueParameters =
        if (valueParameters.lastOrNull()?.returnType?.isValidContinuation() == true)
            valueParameters.subList(0, valueParameters.size - 1)
        else {
            val controllerParameter = valueParameters.last()
            valueParameters.subList(0, valueParameters.size - 2) +
                controllerParameter.copy(this, controllerParameter.name, controllerParameter.index - 1)
        }

    @Suppress("UNCHECKED_CAST")
    return newCopyBuilder().apply {
        setReturnType(returnType)
        setOriginal(newOriginal)
        setValueParameters(newValueParameters)
        setSignatureChange()
        setPreserveSourceElement()
        putUserData(SUSPENSION_POINT_KEY, true)
    }.build()!! as D
}

fun CallableDescriptor.isSuspensionPointView() = this is FunctionDescriptor && isSuspend && getUserData(SUSPENSION_POINT_KEY) == true

fun SimpleFunctionDescriptor.getSuspensionPointReturnType(): KotlinType? =
        sequenceOf(valueParameters.lastOrNull(), valueParameters.getOrNull(valueParameters.lastIndex - 1))
                .firstOrNull { it?.type?.isValidContinuation() == true }
                ?.type?.arguments?.getOrNull(0)?.type

class CoroutineReceiverValue(callableDescriptor: CallableDescriptor, receiverType: KotlinType) : ExtensionReceiver(callableDescriptor, receiverType)

fun LexicalScope.findClosestCoroutineReceiver() =
        getImplicitReceiversHierarchy().firstOrNull { it.value is CoroutineReceiverValue }?.value as CoroutineReceiverValue?
