@AllowSuspendExtensions
class Controller

class A(val k: String) {
    suspend fun String.suspendHere(x: Continuation<String>, c: Controller) {
        x.resume(this + k)
    }
}

fun builder(coroutine c: Controller.() -> Continuation<Unit>) {
    c(Controller()).resume(Unit)
}

fun A.foo(): String {
    var result = ""

    builder {
        result = "O".suspendHere()
    }

    return result
}

fun box() = A("K").foo()
