fun some() : String? = null

fun test() {
    val <caret>test = some()
}


//INFO: <pre><b>val</b> test: String? <i>defined in</i> test</pre>
