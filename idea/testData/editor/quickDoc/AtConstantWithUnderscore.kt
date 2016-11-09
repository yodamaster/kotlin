class C {
    /** Use [SOME_REFERENCED_VAL] to do something */
    fun fo<caret>o() {

    }

    companion object {
        val SOME_REFERENCED_VAL = 1
    }
}

//INFO: <b>public</b> <b>final</b> <b>fun</b> foo(): <a href="psi_element://kotlin.Unit">Unit</a> <i>defined in</i> C<p>Use <a href="psi_element://SOME_REFERENCED_VAL">SOME_REFERENCED_VAL</a> to do something</p>
