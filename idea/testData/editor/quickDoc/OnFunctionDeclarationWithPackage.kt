package test

/**

 *
 *
 * Test function

 *
 * @param first Some
 * @param second Other
 */
fun <caret>testFun(first: String, second: Int) = 12

//INFO: <b>public</b> <b>fun</b> testFun(first: <a href="psi_element://kotlin.String">String</a>, second: <a href="psi_element://kotlin.Int">Int</a>): <a href="psi_element://kotlin.Int">Int</a> <i>defined in</i> test<p>Test function</p>
//INFO: <dl><dt><b>Parameters:</b></dt><dd><code>first</code> - Some</dd><dd><code>second</code> - Other</dd></dl>
