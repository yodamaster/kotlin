/**
Some documentation

 * @param T the type parameter
 * @param a Some int
 * @param b String
 */
fun <T> testMethod(a: Int, b: String) {

}

fun test() {
    <caret>testMethod(1, "value")
}

//INFO: <b>public</b> <b>fun</b> &lt;T&gt; testMethod(a: <a href="psi_element://kotlin.Int">Int</a>, b: <a href="psi_element://kotlin.String">String</a>): <a href="psi_element://kotlin.Unit">Unit</a> <i>defined in</i> root package<p>Some documentation</p>
//INFO: <dl><dt><b>Parameters:</b></dt><dd><code>T</code> - the type parameter</dd><dd><code>a</code> - Some int</dd><dd><code>b</code> - String</dd></dl>
