package server

import some.TopLevelMethodFromJava_DataKt

class Testing {
    void test() {
        TopLevelMethodFromJava_DataKt.<caret>foo(12);
    }
}

//INFO: <b>public</b> <b>fun</b> foo(bar: <a href="psi_element://kotlin.Int">Int</a>): <a href="psi_element://kotlin.Unit">Unit</a> <i>defined in</i> some<p>KDoc foo</p>
