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

package org.jetbrains.kotlin.resolve.checkers

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.coroutines.isValidContinuation
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.diagnostics.DiagnosticSink
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.hasDefaultValue
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.isUnit
import org.jetbrains.kotlin.utils.sure

object SuspendModifierChecker : SimpleDeclarationChecker {
    private val ALLOW_SUSPEND_EXTENSIONS_ANNOTATION_FQ_NAME =
            KotlinBuiltIns.COROUTINES_PACKAGE_FQ_NAME.child(Name.identifier("AllowSuspendExtensions"))

    override fun check(
            declaration: KtDeclaration,
            descriptor: DeclarationDescriptor,
            diagnosticHolder: DiagnosticSink,
            bindingContext: BindingContext
    ) {
        val functionDescriptor = descriptor as? FunctionDescriptor ?: return
        if (!functionDescriptor.isSuspend) return

        val suspendModifierElement = declaration.modifierList?.getModifier(KtTokens.SUSPEND_KEYWORD).sure { declaration.text }
        fun report(message: String) {
            diagnosticHolder.report(Errors.INAPPLICABLE_MODIFIER.on(suspendModifierElement, KtTokens.SUSPEND_KEYWORD, message))
        }

        if (functionDescriptor.dispatchReceiverParameter == null && functionDescriptor.extensionReceiverParameter == null) {
            report("function must be either a class member or an extension")
            return
        }

        val lastParameter = functionDescriptor.valueParameters.lastOrNull()
        val lastParameterType = lastParameter?.type
        val lastButOneParameterType = functionDescriptor.valueParameters.getOrNull(functionDescriptor.valueParameters.lastIndex - 1)?.type

        val continuationParameterType: KotlinType
        val controllerType: KotlinType
        val isExtension: Boolean
        if (lastParameterType?.isValidContinuation() != true) {
            if (lastButOneParameterType?.isValidContinuation() != true) {
                report("last parameter of suspend function should have a type of Continuation<T>")
                return
            }

            if (functionDescriptor.extensionReceiverParameter == null) {
                report("last parameter is allowed to be a constructor only for extension functions")
                return
            }

            if (lastParameter!!.varargElementType != null || lastParameter.hasDefaultValue() || lastParameterType!!.isMarkedNullable) {
                report("controller parameter can't be a vararg or have a default value or be nullable")
                return
            }

            // TODO: report deprecation warning?
            controllerType = lastParameterType
            continuationParameterType = lastButOneParameterType
            isExtension = true
        }
        else {
            controllerType = functionDescriptor.dispatchReceiverParameter?.type ?: functionDescriptor.extensionReceiverParameter!!.type
            isExtension = functionDescriptor.dispatchReceiverParameter == null
            continuationParameterType = lastParameterType
        }

        val classDescriptor = controllerType.constructor.declarationDescriptor as? ClassDescriptor
        if (classDescriptor == null) {
            report("function must be an extension to class")
            return
        }

        if (isExtension && !classDescriptor.annotations.hasAnnotation(ALLOW_SUSPEND_EXTENSIONS_ANNOTATION_FQ_NAME)) {
            report("controller class must be annotated with AllowSuspendExtensions annotation")
            return
        }

        if (continuationParameterType.arguments.firstOrNull()?.isStarProjection == true) {
            report("Continuation<*> is prohibited as a last parameter of suspend function")
            return
        }

        if (functionDescriptor.returnType?.isUnit() != true) {
            report("return type of suspension function must be a kotlin.Unit, but ${functionDescriptor.returnType} was found")
            return
        }
    }
}
