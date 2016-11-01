@AllowSuspendExtensions
class Controller

suspend fun String.suspendHere(q: Long = -78787878787878L, v: String, w: Double = 1.5, x: Continuation<String>, c: Controller) {
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

    if (result != "O#123456790123K0.9876") return "fail 1: $result"

    builder {
        result = "O".suspendHere(v="K", w=0.9876)
    }

    if (result != "O#-78787878787878K0.9876") return "fail 2: $result"

    builder {
        result = "O".suspendHere(v="K")
    }

    if (result != "O#-78787878787878K1.5") return "fail 3: $result"

    return "OK"
}
