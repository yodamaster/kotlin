external class A(y: Int) {
    fun f(x: Int): String

    inline fun g(x: Int) = "[${f(x)}]"

    inline val h: String
        get() = g(3)
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

    return "OK"
}