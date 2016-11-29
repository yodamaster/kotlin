external class A(y: Int) {
    fun f(x: Int): String

    inline fun g(x: Int) = "[${f(x)}]"

    inline val h: String
        get() = g(3)

    fun v(vararg args: Any): Int

    inline fun w(vararg args: Any) = v(*args) * 2

    inline fun u() = v(2, 3) * 2
}

external object O {
    fun f(x: Int): String

    inline fun g(x: Int) = "[${f(x)}]"

    object P {
        fun f(x: Int): String

        inline fun g(x: Int) = "[${f(x)}]"
    }
}


fun box(): String {
    var result = A(3).g(2)
    if (result != "[A.f(5)]") return "fail: class: $result"

    result = A(120).h
    if (result != "[A.f(123)]") return "fail: class property: $result"

    result = O.g(3)
    if (result != "[O.f(3)]") return "fail: object: $result"

    result = O.P.g(4)
    if (result != "[O.P.f(4)]") return "fail: nested object: $result"

    var intResult = A(1).w(1, 2, 3, 4, 5)
    if (intResult != 12) return "fail: vararg spread: $intResult"

    intResult = A(1).u()
    if (intResult != 6) return "fail: vararg: $intResult"

    return "OK"
}