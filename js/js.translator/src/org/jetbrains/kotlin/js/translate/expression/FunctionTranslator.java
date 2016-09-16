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

package org.jetbrains.kotlin.js.translate.expression;

import com.google.dart.compiler.backend.js.ast.*;
import com.google.dart.compiler.backend.js.ast.metadata.MetadataProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.descriptors.*;
import org.jetbrains.kotlin.js.translate.context.AliasingContext;
import org.jetbrains.kotlin.js.translate.context.Namer;
import org.jetbrains.kotlin.js.translate.context.TranslationContext;
import org.jetbrains.kotlin.js.translate.general.AbstractTranslator;
import org.jetbrains.kotlin.js.translate.utils.FunctionBodyTranslator;
import org.jetbrains.kotlin.js.translate.utils.TranslationUtils;
import org.jetbrains.kotlin.psi.KtDeclarationWithBody;
import org.jetbrains.kotlin.psi.KtLambdaExpression;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.KotlinType;

import java.util.*;

import static org.jetbrains.kotlin.js.translate.reference.CallExpressionTranslator.shouldBeInlined;
import static org.jetbrains.kotlin.js.translate.utils.BindingUtils.getFunctionDescriptor;
import static org.jetbrains.kotlin.js.translate.utils.ErrorReportingUtils.message;
import static org.jetbrains.kotlin.js.translate.utils.FunctionBodyTranslator.translateFunctionBody;
import static org.jetbrains.kotlin.js.translate.utils.JsAstUtils.setParameters;

public final class FunctionTranslator extends AbstractTranslator {
    @NotNull
    public static FunctionTranslator newInstance(@NotNull KtDeclarationWithBody function,
            @NotNull TranslationContext context) {
        return new FunctionTranslator(function, context);
    }

    @NotNull
    private TranslationContext functionBodyContext;
    @NotNull
    private final KtDeclarationWithBody functionDeclaration;
    @Nullable
    private JsName extensionFunctionReceiverName;
    @NotNull
    private final JsFunction functionObject;
    @NotNull
    private final FunctionDescriptor descriptor;

    private FunctionTranslator(@NotNull KtDeclarationWithBody functionDeclaration, @NotNull TranslationContext context) {
        super(context);
        this.descriptor = getFunctionDescriptor(context.bindingContext(), functionDeclaration);
        this.functionDeclaration = functionDeclaration;
        this.functionObject = context().getFunctionObject(descriptor);
        assert this.functionObject.getParameters().isEmpty()
                : message(descriptor, "Function " + functionDeclaration.getText() + " processed for the second time.");
        //NOTE: it's important we compute the context before we start the computation
        this.functionBodyContext = getFunctionBodyContext();
        MetadataProperties.setFunctionDescriptor(functionObject, descriptor);
    }

    @NotNull
    private TranslationContext getFunctionBodyContext() {
        AliasingContext aliasingContext;
        if (isExtensionFunction()) {
            DeclarationDescriptor expectedReceiverDescriptor = descriptor.getExtensionReceiverParameter();
            assert expectedReceiverDescriptor != null;
            extensionFunctionReceiverName = functionObject.getScope().declareName(Namer.getReceiverParameterName());
            //noinspection ConstantConditions
            aliasingContext = context().aliasingContext().inner(expectedReceiverDescriptor, extensionFunctionReceiverName.makeRef());
        }
        else {
            aliasingContext = null;
        }
        return context().newFunctionBody(functionObject, aliasingContext, descriptor);
    }

    @NotNull
    public JsPropertyInitializer translateAsEcma5PropertyDescriptor() {
        List<JsParameter> parameters = new ArrayList<JsParameter>();
        functionBodyContext = translateParameters(functionBodyContext, parameters);
        setParameters(functionObject, parameters);
        translateBody();
        return TranslationUtils.translateFunctionAsEcma5PropertyDescriptor(functionObject, descriptor, context());
    }

    @NotNull
    public List<JsPropertyInitializer> translateAsMethod() {
        JsName functionName = context().getNameForDescriptor(descriptor);

        if (shouldBeInlined(descriptor) && DescriptorUtilsKt.isEffectivelyPublicApi(descriptor)) {
            translateSimple();
            InlineMetadata metadata = InlineMetadata.compose(functionObject, descriptor);
            return Collections.singletonList(new JsPropertyInitializer(functionName.makeRef(), metadata.getFunctionWithMetadata()));
        }

        List<JsPropertyInitializer> propertyInitializers = new ArrayList<JsPropertyInitializer>();
        if (TranslationUtils.isOverridableFunctionWithDefaultParameters(descriptor)) {
            JsName bodyName = context().scope().declareName(functionName.getIdent() + Namer.DEFAULT_PARAMETER_IMPLEMENTOR_SUFFIX);
            if (DescriptorUtilsKt.hasOwnParametersWithDefaultValue(descriptor)) {
                propertyInitializers.add(new JsPropertyInitializer(functionName.makeRef(), translateCaller(bodyName)));
            }

            if (descriptor.getModality() != Modality.ABSTRACT) {
                functionBodyContext = translateParameters(functionBodyContext, functionObject.getParameters());
                translateBody();
                propertyInitializers.add(new JsPropertyInitializer(bodyName.makeRef(), functionObject));
            }
        }
        else if (descriptor.getModality() != Modality.ABSTRACT) {
            translateSimple();
            propertyInitializers.add(new JsPropertyInitializer(functionName.makeRef(), functionObject));
        }

        return propertyInitializers;
    }

    private void translateSimple() {
        functionBodyContext = translateParameters(functionBodyContext, functionObject.getParameters());
        if (!(descriptor instanceof ConstructorDescriptor) || (((ConstructorDescriptor) descriptor).isPrimary())) {
            functionObject.getBody().getStatements().addAll(
                    FunctionBodyTranslator.setDefaultValueForArguments(descriptor, functionBodyContext));
        }
        translateBody();
    }

    private JsFunction translateCaller(JsName bodyName) {
        JsFunction callerFunction = new JsFunction(context().scope(), new JsBlock(), "");
        TranslationContext context = context().contextWithScope(callerFunction);
        context = translateParameters(context, callerFunction.getParameters());

        JsName callbackName = callerFunction.getScope().declareFreshName("callback" + Namer.DEFAULT_PARAMETER_IMPLEMENTOR_SUFFIX);
        JsExpression callee = new JsNameRef(bodyName, JsLiteral.THIS);

        JsInvocation defaultInvocation = new JsInvocation(callee, new ArrayList<JsExpression>());
        JsInvocation callbackInvocation = new JsInvocation(callbackName.makeRef());
        JsExpression chosenInvocation = new JsConditional(callbackName.makeRef(), callbackInvocation, defaultInvocation);

        for (JsParameter parameter : callerFunction.getParameters()) {
            defaultInvocation.getArguments().add(parameter.getName().makeRef());
            callbackInvocation.getArguments().add(parameter.getName().makeRef());
        }

        callerFunction.getParameters().add(new JsParameter(callbackName));
        callerFunction.getBody().getStatements().addAll(FunctionBodyTranslator.setDefaultValueForArguments(descriptor, context));

        KotlinType returnType = descriptor.getReturnType();
        assert returnType != null : "Function descriptor is meant to be initialized here: " + descriptor;
        JsStatement statement = KotlinBuiltIns.isUnit(returnType) ? chosenInvocation.makeStmt() : new JsReturn(chosenInvocation);
        callerFunction.getBody().getStatements().add(statement);

        return callerFunction;
    }

    private void translateBody() {
        if (!functionDeclaration.hasBody()) {
            assert descriptor instanceof ConstructorDescriptor || descriptor.getModality().equals(Modality.ABSTRACT);
            return;
        }
        translateFunctionBody(descriptor, functionDeclaration, functionBodyContext, functionObject.getBody());
    }

    @NotNull
    private TranslationContext translateParameters(@NotNull TranslationContext context, @NotNull List<JsParameter> jsParameters) {
        Map<DeclarationDescriptor, JsExpression> aliases = new HashMap<DeclarationDescriptor, JsExpression>();

        for (TypeParameterDescriptor type : descriptor.getTypeParameters()) {
            if (type.isReified()) {
                JsName paramNameForType = context().getNameForDescriptor(type);
                jsParameters.add(new JsParameter(paramNameForType));

                String suggestedName = Namer.isInstanceSuggestedName(type);
                JsName paramName = functionObject.getScope().declareName(suggestedName);
                jsParameters.add(new JsParameter(paramName));
                aliases.put(type, paramName.makeRef());
            }
        }

        context = context.innerContextWithDescriptorsAliased(aliases);

        if (extensionFunctionReceiverName != null || !descriptor.getValueParameters().isEmpty()) {
            mayBeAddThisParameterForExtensionFunction(jsParameters);
            addParameters(jsParameters, descriptor, context());
        }

        return context;
    }

    public static void addParameters(List<JsParameter> list, FunctionDescriptor descriptor, TranslationContext context) {
        for (ValueParameterDescriptor valueParameter : descriptor.getValueParameters()) {
            JsParameter jsParameter = new JsParameter(context.getNameForDescriptor(valueParameter));
            MetadataProperties.setHasDefaultValue(jsParameter, valueParameter.getOriginal().declaresDefaultValue());
            list.add(jsParameter);
        }
    }

    private void mayBeAddThisParameterForExtensionFunction(@NotNull List<JsParameter> jsParameters) {
        if (isExtensionFunction()) {
            assert extensionFunctionReceiverName != null;
            jsParameters.add(new JsParameter(extensionFunctionReceiverName));
        }
    }

    private boolean isExtensionFunction() {
        return DescriptorUtils.isExtension(descriptor) && !(functionDeclaration instanceof KtLambdaExpression);
    }
}
