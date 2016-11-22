// FILE: JavaClass.java

public class JavaClass {

    public Double minus0(){
        return -0.0;
    }

    public Double plus0(){
        return 0.0;
    }

    public Double null0(){
        return null;
    }

}

// FILE: b.kt

fun box(): String {
    if ((-0.0 as Comparable<Double>) >= 0.0) return "fail 0"
    if ((-0.0F as Comparable<Float>) >= 0.0F) return "fail 1"

    val jClass = JavaClass()

    if ((jClass.minus0() as Comparable<Double>) >= jClass.plus0()) return "fail 2"


    if ((-0.0 as Comparable<Double>) == 0.0) return "fail 3"
    if (-0.0 == (0.0 as Comparable<Double>)) return "fail 4"

    if ((-0.0F as Comparable<Float>) == 0.0F) return "fail 5"
    if (-0.0F == (0.0F as Comparable<Float>)) return "fail 6"

    if ((jClass.minus0() as Comparable<Double>) == jClass.plus0()) return "fail 7"
    if (jClass.minus0() == (jClass.plus0() as Comparable<Double>)) return "fail 8"

    return "OK"
}