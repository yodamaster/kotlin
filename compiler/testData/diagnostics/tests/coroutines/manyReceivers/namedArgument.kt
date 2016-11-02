// !DIAGNOSTICS: -UNUSED_PARAMETER
// !CHECK_TYPE
@AllowSuspendExtensions
class Controller

fun builder(coroutine c: Controller.() -> Continuation<Unit>) {}

suspend fun String.yieldString(c: Continuation<Unit>, controller: Controller) {}

fun test() {
    builder {
        "".yieldString()
        "".yieldString(<!EXPLICIT_CONTROLLER_ARGUMENT!>controller = Controller()<!>)
    }
}
