/**
Some documentation

 * @param a Some int
 * @param b String
 * @return Return [a] and nothing else
 */
fun testMethod(a: Int, b: String) {

}

fun test() {
    <caret>testMethod(1, "value")
}

//INFO: <b>public</b> <b>fun</b> testMethod(a: <a href="psi_element://kotlin.Int">Int</a>, b: <a href="psi_element://kotlin.String">String</a>): <a href="psi_element://kotlin.Unit">Unit</a> <i>defined in</i> root package<p>Some documentation</p>
//INFO: <dl><dt><b>Parameters:</b></dt><dd><code>a</code> - Some int</dd><dd><code>b</code> - String</dd></dl>
//INFO: <dl><dt><b>Returns:</b></dt><dd>Return <a href="psi_element://a">a</a> and nothing else</dd></dl>
