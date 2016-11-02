// !DIAGNOSTICS: -UNUSED_PARAMETER
// !CHECK_TYPE
@AllowSuspendExtensions
class Controller<E>

fun <E> builder(coroutine c: Controller<E>.() -> Continuation<Unit>) {}

suspend fun <E> String.yieldString(c: Continuation<Unit>, controller: Controller<E>) {}

suspend fun <V, E> Int.await(machine: Continuation<V>, controller: Controller<E>) { }

suspend fun <V, E> V.await(f: () -> V, machine: Continuation<V>, controller: Controller<E>) {}
suspend fun <V, E> V.inferFromReceiver(machine: Continuation<V>, controller: Controller<E>) {}

suspend fun <E> String.severalParams(x: String, y: Int, machine: Continuation<Double>, controller: Controller<E>) {}

fun test() {
    builder<Int> {
        "".yieldString() checkType { _<Unit>() }
        "".yieldString<Int>() checkType { _<Unit>() }
        "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>yieldString<!><String>() checkType { _<Unit>() }
        1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>yieldString<!>()

        "".await<String, Int> { "123" }  checkType { _<String>() }
        "".<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>await<!><String, String> { "123" }  checkType { _<String>() }

        // Inference from lambda return type
        123.await { 123 } checkType { _<Int>() }
        123.inferFromReceiver() checkType { _<Int>() }

        // Inference from the expected type
        checkSubtype<String>(567.await())

        123.await<Double, Int>() checkType { _<Double>() }
        123.<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>await<!><Double, String>() checkType { _<Double>() }

        "".severalParams("", 89) checkType { _<Double>() }
        // TODO: should we prohibit such calls?
        "".<!TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>severalParams<!>("", 89, <!EXPLICIT_CONTROLLER_ARGUMENT, CONSTANT_EXPECTED_TYPE_MISMATCH!>6.9<!>)
        "".severalParams("", 89, <!UNCHECKED_CAST!>this as Continuation<Double><!>, this@builder) checkType { _<Unit>() }
    }
}
