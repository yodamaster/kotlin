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

fun box(): String {
    var result = ""

    builder {
        with(A("K")) {
            result = "O".suspendHere()
        }
    }

    if (result != "OK") return "fail 1: $result"

    with(A("K")) {
        builder {
            result = "O".suspendHere()
        }
    }

    return result
}
