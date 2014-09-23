package net.bodz.bas.c.java.util;

public class ArrayAndScalar<A, S> {

    public A array;
    public S scalar;

    public ArrayAndScalar(A array, S scalar) {
        this.array = array;
        this.scalar = scalar;
    }

    public static <T> ArrayAndScalar<T[], T> of(T[] array, T scalar) {
        return new ArrayAndScalar<T[], T>(array, scalar);
    }

    public static <A, S> ArrayAndScalar<A, S> of(A array, S scalar) {
        return new ArrayAndScalar<A, S>(array, scalar);
    }

}
