// FILE: JavaClass.java

public class JavaClass {

    public Float minus0(){
        return -0.0F;
    }

    public Float plus0(){
        return 0.0F;
    }

    public Float null0(){
        return null;
    }

}

// FILE: b.kt


fun greater1(a: Float, b: Float) = a > b

fun greater2(a: Float?, b: Float?) = a!! > b!!

fun greater3(a: Float?, b: Float?) = a != null && b != null && a > b

fun greater4(a: Float?, b: Float?) = if (a is Float && b is Float) a > b else null!!

fun greater5(a: Any?, b: Any?) = if (a is Float && b is Float) a > b else null!!

fun box(): String {
    if (0.0F > -0.0F) return "fail 0"
    if (greater1(0.0F, -0.0F)) return "fail 1"
    if (greater2(0.0F, -0.0F)) return "fail 2"
    if (greater3(0.0F, -0.0F)) return "fail 3"
    if (greater4(0.0F, -0.0F)) return "fail 4"
    if (greater5(0.0F, -0.0F)) return "fail 5"


    val jClass = JavaClass()

    if (jClass.plus0() > jClass.minus0()) return "fail 6"

    //TODO: KT-14989
    //if (jClass.null0() < jClass.plus0()) return "fail 7"

    return "OK"
}