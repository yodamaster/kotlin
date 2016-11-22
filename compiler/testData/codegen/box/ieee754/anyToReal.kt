// FILE: JavaClass.java

public class JavaClass {

    public Object minus0()
    {
        return -0.0;
    }

    public Object plus0()
    {
        return 0.0;
    }

    public Object null0()
    {
        return null;
    }

}

// FILE: b.kt

fun box(): String {
    val plusZero: Any = 0.0
    val minusZero: Any = -0.0
    if ((minusZero as Double) < (plusZero as Double)) return "fail 0"

    val plusZeroF: Any = 0.0F
    val minusZeroF: Any = -0.0F
    if ((minusZeroF as Float) < (plusZeroF as Float)) return "fail 1"

    val jClass = JavaClass()

    if ((jClass.minus0() as Double) < (jClass.plus0() as Double)) return "fail 2"


    if ((minusZero as Double) != (plusZero as Double)) return "fail 3"

    if ((minusZeroF as Float) != (plusZeroF as Float)) return "fail 4"

    if ((jClass.minus0() as Double) != (jClass.plus0() as Double)) return "fail 5"

    return "OK"
}