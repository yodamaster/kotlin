class A {

}

fun foo(x : A) { }

fun main(args: Array<String>) {
    <caret>foo()
}

//INFO: <b>public</b> <b>fun</b> foo(x: <a href="psi_element://A">A</a>): <a href="psi_element://kotlin.Unit">Unit</a> <i>defined in</i> root package
