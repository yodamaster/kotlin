external class A {
    inline fun f(<!UNUSED_PARAMETER!>x<!>: Int) = 23

    <!NON_ABSTRACT_FUNCTION_WITH_NO_BODY!>inline fun g(): Unit<!>
}

class B {
    <!NOTHING_TO_INLINE!>inline<!> fun f(x: Int) = x * 2
}