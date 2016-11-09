fun ktTest() {
    Test.<caret>foo("SomeTest")
}

//INFO: <b>public</b> <b>open</b> <b>fun</b> foo(param: <a href="psi_element://kotlin.String">String</a>!): (<a href="psi_element://kotlin.Array">Array</a>&lt;<a href="psi_element://kotlin.Any">Any</a>!&gt;..<a href="psi_element://kotlin.Array">Array</a>&lt;out <a href="psi_element://kotlin.Any">Any</a>!&gt;?)<br/>Java declaration:<br/>Test
//INFO:  public static java.lang.Object[] foo (java.lang.String param)
