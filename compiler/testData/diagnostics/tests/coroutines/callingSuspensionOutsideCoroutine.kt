// !DIAGNOSTICS: -UNUSED_PARAMETER
// !CHECK_TYPE
class Controller {
    suspend fun suspendHere(a: String, x: Continuation<Int>) {
    }
}

fun builder(coroutine c: Controller.() -> Continuation<Unit>) {}

fun test() {
    builder {
        suspendHere("")
    }

    with(Controller()) {
        suspendHere(""<!NO_VALUE_FOR_PARAMETER!>)<!>
        // This test checks that suspending functions
        // that are not from coroutine controller can't be called by suspending convention
        suspendHere(""<!NO_VALUE_FOR_PARAMETER!>)<!>
    }
}
