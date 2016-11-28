// MODULE: lib
// FILE: lib.kt
package lib

external class A(y: Int) {
    fun f(x: Int): String

    inline fun g(x: Int) = "[${f(x)}]"

    inline val h: String
        get() = g(3)
}

// MODULE: main(lib)
// FILE: main.kt
package foo

import lib.*

fun box(): String {
    var result = A(3).g(2)
    if (result != "[A.f(5)]") return "fail: class: $result"

    result = A(120).h
    if (result != "[A.f(123)]") return "fail: class property: $result"

    return "OK"
}