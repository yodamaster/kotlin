/**
 * Some documentation
 * on two lines.
 */
fun testMethod() {

}

fun test() {
    <caret>testMethod()
}

//INFO: <b>public</b> <b>fun</b> testMethod(): <a href="psi_element://kotlin.Unit">Unit</a> <i>defined in</i> root package<p>Some documentation on two lines.</p>
