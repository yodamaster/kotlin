// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

fun test(i: Int): Int {
    return i
}

fun box(): String {
    var b = Byte.MAX_VALUE
    var result = test(b.inc().toInt())
    if (result != Byte.MIN_VALUE.toInt()) return "fail 1: $result"

    var s = Short.MAX_VALUE
    result = test(s.inc().toInt())
    if (result != Short.MIN_VALUE.toInt()) return "fail 2: $result"

    return "OK"
}