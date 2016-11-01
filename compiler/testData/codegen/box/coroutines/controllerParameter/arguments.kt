@AllowSuspendExtensions
class Controller

suspend fun String.suspendHere(q: Long, v: String, w: Double, x: Continuation<String>, c: Controller) {
    x.resume("$this#$q$v$w")
}

fun builder(coroutine c: Controller.() -> Continuation<Unit>) {
    c(Controller()).resume(Unit)
}

fun box(): String {
    var result = ""

    builder {
        result = "O".suspendHere(123456790123L, "K", 0.9876)
    }

    if (result != "O#123456790123K0.9876") return "fail: $result"

    return "OK"
}
