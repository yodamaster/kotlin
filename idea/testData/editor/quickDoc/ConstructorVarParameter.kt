class C(var v: Int) {
    fun foo() {
        print(<caret>v)
    }
}

//INFO: <b>public</b> <b>final</b> <b>var</b> v: <a href="psi_element://kotlin.Int">Int</a> <i>defined in</i> C
