fun some() : String? = null

fun test() {
    val <caret>test = some()
}


//INFO: <b>val</b> test: <a href="psi_element://kotlin.String">String</a>? <i>defined in</i> test
