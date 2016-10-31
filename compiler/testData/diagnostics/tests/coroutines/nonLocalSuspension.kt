// !DIAGNOSTICS: -UNUSED_PARAMETER

@AllowSuspendExtensions
class Controller<T> {
    suspend fun suspendHere(x: Continuation<Int>) {
    }

    suspend fun another(a: T, x: Continuation<Int>) {
    }
}

suspend fun <E> Controller<E>.suspendExtension(y: Continuation<Int>) {}
suspend fun <E> Controller<E>.anotherExtension(x: E, y: Continuation<Int>) {}
suspend fun <E> String.implicitParameter(y: Continuation<Int>, c: Controller<E>) {}
suspend fun <E> String.anotherImplicitParameter(x: E, y: Continuation<Int>, c: Controller<E>) {}

fun <T> builder(coroutine c: Controller<T>.() -> Continuation<Unit>) { }

inline fun run(x: () -> Unit) {}

inline fun runCross(crossinline x: () -> Unit) {}

fun noinline(x: () -> Unit) {}

fun foo() {
    var result = 1
    builder<String> {
        suspendHere()
        suspendExtension()

        another("")
        another(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)

        anotherExtension("")
        <!TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS!>anotherExtension<!>(1)

        "".implicitParameter()
        "".anotherImplicitParameter("")
        "".<!TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS!>anotherImplicitParameter<!>(1)

        result += suspendHere()

        run {
            result += suspendHere()
            result += suspendExtension()
            result += "".implicitParameter()

            run {
                "".implicitParameter()
                suspendHere()
            }
        }

        runCross {
            result += <!NON_LOCAL_SUSPENSION_POINT!>suspendHere<!>()
            result += <!NON_LOCAL_SUSPENSION_POINT!>suspendExtension<!>()
            result += "".<!NON_LOCAL_SUSPENSION_POINT!>implicitParameter<!>()
            runCross {
                "".<!NON_LOCAL_SUSPENSION_POINT!>implicitParameter<!>()
                <!NON_LOCAL_SUSPENSION_POINT!>suspendHere<!>()
                <!NON_LOCAL_SUSPENSION_POINT!>suspendExtension<!>()
            }
        }

        noinline {
            result += <!NON_LOCAL_SUSPENSION_POINT!>suspendHere<!>()
            result += <!NON_LOCAL_SUSPENSION_POINT!>suspendExtension<!>()
            result += "".<!NON_LOCAL_SUSPENSION_POINT!>implicitParameter<!>()
            noinline {
                <!NON_LOCAL_SUSPENSION_POINT!>suspendHere<!>()
                <!NON_LOCAL_SUSPENSION_POINT!>suspendExtension<!>()
                "".<!NON_LOCAL_SUSPENSION_POINT!>implicitParameter<!>()
            }
        }

        class A {
            fun bar() {
                <!NON_LOCAL_SUSPENSION_POINT!>suspendHere<!>()
                <!NON_LOCAL_SUSPENSION_POINT!>suspendExtension<!>()
                "".<!NON_LOCAL_SUSPENSION_POINT!>implicitParameter<!>()
            }
        }

        object : Any() {
            fun baz() {
                <!NON_LOCAL_SUSPENSION_POINT!>suspendHere<!>()
                <!NON_LOCAL_SUSPENSION_POINT!>suspendExtension<!>()
                "".<!NON_LOCAL_SUSPENSION_POINT!>implicitParameter<!>()
            }
        }

        builder<Int> {
            suspendHere()

            another(1)
            another(<!TYPE_MISMATCH!>""<!>)

            anotherExtension(1)
            <!TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS!>anotherExtension<!>("")

            "".anotherImplicitParameter(1)
            "".<!TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS!>anotherImplicitParameter<!>("")
        }
    }
}
