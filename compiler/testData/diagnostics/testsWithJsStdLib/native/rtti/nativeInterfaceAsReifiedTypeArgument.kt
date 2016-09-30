// !DIAGNOSTICS: -UNUSED_PARAMETER

fun <reified T> foo(x: T) {
    println(x)
}

@native interface I

@native class C : I

operator fun <reified T> C.plus(other: T) = this

fun bar() {
    foo(C())

    val c: I = C()
    foo(<!NATIVE_INTERFACE_AS_REIFIED_TYPE_ARGUMENT!>c<!>)
    foo<<!NATIVE_INTERFACE_AS_REIFIED_TYPE_ARGUMENT!>I<!>>(C())

    C() + <!NATIVE_INTERFACE_AS_REIFIED_TYPE_ARGUMENT!>c<!>
}