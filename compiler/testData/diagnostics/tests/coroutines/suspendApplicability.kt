// !DIAGNOSTICS: -UNUSED_PARAMETER -NOTHING_TO_INLINE
<!INAPPLICABLE_MODIFIER!>suspend<!> fun notMember(x: Continuation<Int>) {

}

<!INAPPLICABLE_MODIFIER!>suspend<!> fun String.wrongExtension(x: Continuation<Int>) {
}

suspend fun Controller.correctExtension(x: Continuation<Int>) {
}

suspend fun String.implicitControllerParameter1(x: Continuation<Int>, y: Controller) {}
// controversial case
suspend fun Controller.implicitControllerParameter2(x: Continuation<Int>, y: Controller) {}
<!INAPPLICABLE_MODIFIER!>suspend<!> fun String.implicitControllerParameter3(x: Continuation<Int>, y: Controller?) {}
<!INAPPLICABLE_MODIFIER!>suspend<!> fun String.implicitControllerParameter4(x: Continuation<Int>, y: Controller = Controller()) {}
<!INAPPLICABLE_MODIFIER!>suspend<!> fun String.implicitControllerParameter5(x: Continuation<Int>, vararg y: Controller) {}
<!INAPPLICABLE_MODIFIER!>suspend<!> fun String.implicitControllerParameter6(x: Continuation<Int>, y: String) {}

class NotController {
    suspend fun String.implicitControllerParameter(x: Continuation<Int>, y: Controller) {}
}

@AllowSuspendExtensions
class Controller {
    suspend fun String.implicitControllerParameter1(x: Continuation<Int>, y: Controller) {}
    // controversial case
    suspend fun Controller.implicitControllerParameter2(x: Continuation<Int>, y: Controller) {}

    suspend fun valid(x: Continuation<Int>) {

    }

    inline suspend fun inlineFun(x: Continuation<Int>) {

    }

    <!INAPPLICABLE_MODIFIER!>suspend<!> fun noParams() {

    }

    <!INAPPLICABLE_MODIFIER!>suspend<!> fun wrongParam(x: Collection<Int>) {

    }

    <!INAPPLICABLE_MODIFIER!>suspend<!> fun starProjection(x: Continuation<*>) {

    }

    <!INAPPLICABLE_MODIFIER!>suspend<!> fun varargs(vararg x: Continuation<Any>) {

    }

    suspend fun String.memberExtension(x: Continuation<Int>) {

    }

    suspend fun returnsUnit(x: Continuation<Int>) = Unit
    <!INAPPLICABLE_MODIFIER!>suspend<!> fun returnsNotUnit(x: Continuation<Int>) = 1
}
