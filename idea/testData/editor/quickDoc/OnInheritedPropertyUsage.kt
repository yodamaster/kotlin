open class C() {
    /**
     * This property returns zero.
     */
    open val foo: Int get() = 0
}

class D(): C() {
    override val foo: Int get() = 1
}


fun test() {
    D().f<caret>oo
}

//INFO: <b>public</b> <b>open</b> <b>val</b> foo: <a href="psi_element://kotlin.Int">Int</a> <i>defined in</i> D<p>This property returns zero.</p>
