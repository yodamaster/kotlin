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


fun less1(a: Double, b: Double) = a < b

fun less2(a: Double?, b: Double?) = a!! < b!!

fun less3(a: Double?, b: Double?) = a != null && b != null && a < b

fun less4(a: Double?, b: Double?) = if (a is Double && b is Double) a < b else null!!

fun less5(a: Any?, b: Any?) = if (a is Double && b is Double) a < b else null!!

fun box(): String {
    if (-0.0 < 0.0) return "fail 0"
    if (less1(-0.0, 0.0)) return "fail 1"
    if (less2(-0.0, 0.0)) return "fail 2"
    if (less3(-0.0, 0.0)) return "fail 3"
    if (less4(-0.0, 0.0)) return "fail 4"
    if (less5(-0.0, 0.0)) return "fail 5"


    val jClass = JavaClass()

    if (jClass.minus0() < jClass.plus0()) return "fail 6"

    //TODO: KT-14989
    //if (jClass.null0() < jClass.plus0()) return "fail 7"

    return "OK"
}