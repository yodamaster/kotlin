// !DIAGNOSTICS: -UNUSED_PARAMETER
// !CHECK_TYPE
@AllowSuspendExtensions
class Controller1

@AllowSuspendExtensions
class Controller2<T>

fun builder1(coroutine c: Controller1.() -> Continuation<Unit>) {}
fun <E> builder2(coroutine c: Controller2<E>.() -> Continuation<Unit>) {}

suspend fun String.foo(x: Continuation<Int>, y: Controller1) {}
suspend fun String.bar(x: Continuation<Int>, y: Controller1) {}
suspend fun <F> F.foobar(x: Continuation<F>, y: Controller1) {}

suspend fun <E> String.foo(x: Continuation<Double>, y: Controller2<E>) {}
suspend fun <E> String.baz(x: Continuation<E>, y: Controller2<E>) {}

suspend fun String.forIntController(x: Continuation<String>, y: Controller2<Int>) {}

fun foo() {
    builder1 {
        "".foo() checkType { _<Int>() }
        "".bar() checkType { _<Int>() }
        "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH, TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>baz<!>()

        "".foobar() checkType { _<String>() }
        123.foobar() checkType { _<Int>() }

        builder2<String> {
            "".foo() checkType { _<Double>() }
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>bar<!>() checkType { _<Int>() }
            "".baz() checkType { _<String>() }

            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH, TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>foobar<!>()
            123.<!CONTROLLER_PARAMETER_TYPE_MISMATCH, TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>foobar<!>()

            "".foo<String>() checkType { _<Double>() }
            "".baz<String>() checkType { _<String>() }
            "".<!NONE_APPLICABLE!>foo<!><Int>()
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>baz<!><Int>()

            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>forIntController<!>()
        }
    }

    builder2<String> {
        "".foo() checkType { _<Double>() }
        "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>bar<!>() checkType { _<Int>() }
        "".baz() checkType { _<String>() }

        "".foo<String>() checkType { _<Double>() }
        "".baz<String>() checkType { _<String>() }
        "".<!NONE_APPLICABLE!>foo<!><Int>()
        "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>baz<!><Int>()

        "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH, TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>foobar<!>()
        123.<!CONTROLLER_PARAMETER_TYPE_MISMATCH, TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>foobar<!>()

        builder1 {
            "".foo() checkType { _<Int>() }
            "".bar() checkType { _<Int>() }
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH, TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>baz<!>()

            "".foobar() checkType { _<String>() }
            123.foobar() checkType { _<Int>() }

            "".<!NONE_APPLICABLE!>foo<!><String>()
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>baz<!><String>()
        }

        builder2<Int> {
            "".foo() checkType { _<Double>() }
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>bar<!>() checkType { _<Int>() }
            "".baz() checkType { _<Int>() }

            "".<!NONE_APPLICABLE!>foo<!><String>()
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>baz<!><String>()
            "".foo<Int>() checkType { _<Double>() }
            "".baz<Int>() checkType { _<Int>() }

            "".forIntController() checkType { _<String>() }
        }
    }
}
