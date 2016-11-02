// !DIAGNOSTICS: -UNUSED_PARAMETER
// !CHECK_TYPE
@AllowSuspendExtensions
class Controller1

@AllowSuspendExtensions
class Controller2<T>

class A {
    suspend fun String.foo(x: Continuation<Int>, y: Controller1) {}
    fun test() {
        builder1 {
            "".foo() checkType { _<Int>() }
            "".foo(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder1<!>) checkType { _<Int>() }
            with("") {
                foo() checkType { _<Int>() }
            }
        }

        builder2<String> {
            "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>foo<!>()
            "".foo(<!EXPLICIT_CONTROLLER_ARGUMENT!>Controller1()<!>)
        }
    }
}

fun builder1(coroutine c: Controller1.() -> Continuation<Unit>) {}
fun <E> builder2(coroutine c: Controller2<E>.() -> Continuation<Unit>) {}

fun test() {
    with(A()) {
        builder1 {
            "".foo(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder1<!>) checkType { _<Int>() }
            "".foo() checkType { _<Int>() }
            with("") {
                foo() checkType { _<Int>() }
            }
        }
    }

    builder1 {
        with(A()) {
            "".foo(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder1<!>) checkType { _<Int>() }
            "".foo() checkType { _<Int>() }
            with("") {
                foo() checkType { _<Int>() }
            }
        }
    }
}
