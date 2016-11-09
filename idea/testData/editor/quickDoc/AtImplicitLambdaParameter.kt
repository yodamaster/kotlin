fun foo() {
    listOf(1).forEach {
        println(it<caret>)
    }
}

//INFO: <b>value-parameter</b> it: <a href="psi_element://kotlin.Int">Int</a> <i>defined in</i> foo.&lt;anonymous&gt;
