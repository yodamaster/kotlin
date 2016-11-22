// FILE: JavaClass.java

public class JavaClass<T> {

    private T minus0;

    private T plus0;

    JavaClass(T minus0, T plus0)
    {
        this.minus0 = minus0;
        this.plus0 = plus0;
    }

    public T minus0()
    {
        return minus0;
    }

    public T plus0()
    {
        return plus0;
    }

    public T null0()
    {
        return null;
    }

}

// FILE: b.kt

class Foo<T>(val minus0: T, val plus0: T) {

}

fun box(): String {
    val foo = Foo<Double>(-0.0, 0.0)
    val fooF = Foo<Float>(-0.0F, 0.0F)

    if (foo.minus0 < foo.plus0) return "fail 0"
    if (fooF.minus0 < fooF.plus0) return "fail 1"

    val jClass = JavaClass<Double>(-0.0, 0.0)

    if (jClass.minus0() < jClass.plus0()) return "fail 2"


    if (foo.minus0 != foo.plus0) return "fail 3"
    if (fooF.minus0 != fooF.plus0) return "fail 4"

    if (jClass.minus0() != jClass.plus0()) return "fail 5"

    return "OK"
}