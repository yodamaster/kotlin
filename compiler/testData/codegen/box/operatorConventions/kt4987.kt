// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

fun box(): String {
    operator fun Int?.inc() = (this ?: 0) + 1
    var counter: Int? = null
    counter++
    return if (counter == 1) "OK" else "fail: $counter"
}