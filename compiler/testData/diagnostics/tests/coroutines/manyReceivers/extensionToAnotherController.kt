// !DIAGNOSTICS: -UNUSED_PARAMETER
// !CHECK_TYPE
@AllowSuspendExtensions
class Controller1

suspend fun Controller1.twoControllers(x: Continuation<Unit>, y: Controller1) {}
suspend fun Controller1.oneController(x: Continuation<Unit>) {}

@AllowSuspendExtensions
class Controller2<T>

suspend fun <E> Controller1.twoControllers2(x: Continuation<Unit>, y: Controller2<E>) {}

fun builder1(coroutine c: Controller1.() -> Continuation<Unit>) {}
fun <E> builder2(coroutine c: Controller2<E>.() -> Continuation<Unit>) {}

fun test() {
    builder1 {
        oneController()
        // TODO: is it OK?
        twoControllers()
        this@builder1.twoControllers()
        twoControllers(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder1<!>)
        this@builder1.twoControllers(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder1<!>)

        builder2<String> {
            <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>oneController<!>()
            this@builder1.oneController(<!NO_VALUE_FOR_PARAMETER!>)<!>
            <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>twoControllers<!>()
            this@builder1.<!CONTROLLER_PARAMETER_TYPE_MISMATCH!>twoControllers<!>()
            twoControllers(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder1<!>)
            this@builder1.twoControllers(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder1<!>)

            twoControllers2()
            this@builder1.twoControllers2()
            twoControllers2(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder2<!>)
            this@builder1.twoControllers2(<!EXPLICIT_CONTROLLER_ARGUMENT!>this@builder2<!>)
        }
    }
}
