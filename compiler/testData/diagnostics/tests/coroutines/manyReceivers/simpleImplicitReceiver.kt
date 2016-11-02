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
        with("") {
            yieldString() checkType { _<Unit>() }
            severalParams("", 89) checkType { _<Double>() }
            await<String> { "123" }  checkType { _<String>() }
        }

        with(123) {
            // Inference from lambda return type
            await { 123 } checkType { _<Int>() }
            inferFromReceiver() checkType { _<Int>() }
            await<Double>() checkType { _<Double>() }

            // Inference from expected type
            checkSubtype<String>(await())
        }
    }

    with("") {
        builder {
            yieldString() checkType { _<Unit>() }
            severalParams("", 89) checkType { _<Double>() }
            await<String> { "123" }  checkType { _<String>() }
        }
    }

    with(456) {
        builder {
            // Inference from lambda return type
            // TODO: is it ok?
            await { 123 } checkType { _<Any>() }
            inferFromReceiver() checkType { _<Controller>() }
            await<Double>() checkType { _<Double>() }

            // Inference from expected type
            checkSubtype<String>(await())
        }
    }
}
