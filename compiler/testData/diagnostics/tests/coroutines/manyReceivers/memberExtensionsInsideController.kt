// !DIAGNOSTICS: -UNUSED_PARAMETER
// !CHECK_TYPE
@AllowSuspendExtensions
class Controller1 {
    suspend fun String.foo(x: Continuation<Int>, y: Controller1) {}
    suspend fun <E> String.bar(x: Continuation<Double>, y: Controller2<E>) {}

    suspend fun Controller1.threeControllers(x: Continuation<Unit>, y: Controller1) {}
}

@AllowSuspendExtensions
class Controller2<T> {
    suspend fun String.baz(x: Continuation<String>, y: Controller1) {}
    suspend fun <E> String.foobar(x: Continuation<E>, y: Controller2<E>) {}

    suspend fun Controller1.threeControllers2(x: Continuation<Unit>, y: Controller2<T>) {}
}

fun builder1(coroutine c: Controller1.() -> Continuation<Unit>) {}
fun <E> builder2(coroutine c: Controller2<E>.() -> Continuation<Unit>) {}

fun test() {
    builder1 {
        // TODO: Is it ok?
        // Seems like it's not
        "".foo() checkType { _<Int>() }

        "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH, TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>bar<!>()
        "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>bar<!><Int>()

        threeControllers()
        this@builder1.threeControllers()

        builder2<String> {
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>baz<!>() checkType { _<String>() }
            "".foobar() checkType { _<String>() }

            threeControllers2()
            this@builder1.threeControllers2()

            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH, NON_LOCAL_SUSPENSION_POINT!>foo<!>()

            "".<!NON_LOCAL_SUSPENSION_POINT!>bar<!>()
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH, NON_LOCAL_SUSPENSION_POINT!>bar<!><Int>()

            <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>threeControllers<!>()
            this@builder1.<!CONTROLLER_PARAMETER_TYPE_MISMATCH, NON_LOCAL_SUSPENSION_POINT!>threeControllers<!>()
        }
    }
}
