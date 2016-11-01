@AllowSuspendExtensions
class Controller {
    fun foo() = "K"
}

inline suspend fun String.suspendHere(
        v: Long,
        crossinline b: () -> String,
        x: Continuation<String>,
        c: Controller
) {
    x.resume(this + v + b() + c.foo())
}

fun builder(coroutine c: Controller.() -> Continuation<Unit>) {
    c(Controller()).resume(Unit)
}

fun box(): String {
    var result = ""

    builder {
        result = "O".suspendHere(1234567891234L) {
            "56"
        }
    }

    if (result != "O123456789123456K") return "fail: $result"

    return "OK"
}
