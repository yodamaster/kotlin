public class C : I {
    override fun f(p: String) = p + "K"
}

fun box() = C().f()