@AllowSuspendExtensions
class Controller {
    fun foo() = "K"
}

suspend fun String.suspendHere(x: Continuation<String>, c: Controller) {
    x.resume(this + c.foo())
}

fun builder(coroutine c: Controller.() -> Continuation<Unit>) {
    c(Controller()).resume(Unit)
}

fun box(): String {
    var result = ""

    builder {
        result = "O".suspendHere()
    }

    return result
}
