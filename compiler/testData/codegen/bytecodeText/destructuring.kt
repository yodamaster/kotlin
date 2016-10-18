inline fun foo(x: (Int, Station) -> Unit) {
    x(1, Station(null, "", 1))
}

data class Station(
        val id: String?,
        val name: String,
        val distance: Int)


fun box(): String {
    foo { i, (a1, a2, a3) -> i + a3 }
    return "OK"
}

// 1 LOCALVARIABLE i I L2 L5 1
// 1 LOCALVARIABLE \$a1_a2_a3 LStation; L2 L5 0
// 1 LOCALVARIABLE a1 Ljava/lang/String; L2 L5 2
// 1 LOCALVARIABLE a2 Ljava/lang/String; L2 L5 3
// 1 LOCALVARIABLE a3 I L2 L5 4
// 1 LOCALVARIABLE \$i\$a\$1\$foo I L2 L5 5
// 1 LOCALVARIABLE \$i\$f\$foo I L1 L7 6