// !DIAGNOSTICS: -UNUSED_PARAMETER
// !CHECK_TYPE
@AllowSuspendExtensions
class Controller

fun builder(coroutine c: Controller.() -> Continuation<Unit>) {}

suspend fun String.yieldString(c: Continuation<Unit>, controller: Controller) {}

suspend fun <V> Int.await(machine: Continuation<V>, controller: Controller) { }

suspend fun <V> V.await(f: () -> V, machine: Continuation<V>, controller: Controller) {}
suspend fun <V> V.inferFromReceiver(machine: Continuation<V>, controller: Controller) {}

suspend fun String.severalParams(x: String, y: Int, machine: Continuation<Double>, controller: Controller) {}

fun test() {
    builder {
        "".yieldString() checkType { _<Unit>() }
        1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>yieldString<!>()

        "".await<String> { "123" }  checkType { _<String>() }

        // Inference from lambda return type
        123.await { 123 } checkType { _<Int>() }
        123.inferFromReceiver() checkType { _<Int>() }

        // Inference from expected type
        checkSubtype<String>(567.await())

        123.await<Double>() checkType { _<Double>() }

        "".severalParams("", 89) checkType { _<Double>() }
        // TODO: should we prohibit such calls?
        "".severalParams("", 89, <!EXPLICIT_CONTROLLER_ARGUMENT, CONSTANT_EXPECTED_TYPE_MISMATCH!>6.9<!>)
        "".severalParams("", 89, this <!CAST_NEVER_SUCCEEDS!>as<!> Continuation<Double>, this@builder) checkType { _<Unit>() }
    }
}
