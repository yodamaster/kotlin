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

fun equals1(a: Double, b: Double) = a.equals(b)

fun equals2(a: Double?, b: Double?) = a!!.equals(b!!)

fun equals3(a: Double?, b: Double?) = a != null && b != null && a.equals(b)

fun equals4(a: Double?, b: Double?) = if (a is Double && b is Double) a.equals(b) else null!!


fun box(): String {
    if ((-0.0).equals(0.0)) return "fail 0"
    if (equals1(-0.0, 0.0)) return "fail 1"
    if (equals2(-0.0, 0.0)) return "fail 2"
    if (equals3(-0.0, 0.0)) return "fail 3"
    if (equals4(-0.0, 0.0)) return "fail 4"

    val jClass = JavaClass()

    if (jClass.minus0().equals(jClass.plus0())) return "fail 5"

    //TODO: KNE
    if (jClass.null0().equals(jClass.plus0())) return "fail 6"
    if (jClass.minus0().equals(jClass.null0())) return "fail 7"
    return "OK"
}

