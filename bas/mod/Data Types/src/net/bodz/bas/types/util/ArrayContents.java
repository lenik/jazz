package net.bodz.bas.types.util;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;

public class ArrayContents<A, E> extends AbstractList<E> {

    private final A          array;
    private final ArrayOp<A> op;
    private final boolean    deep;

    public ArrayContents(A array) {
        this(array, false, null);
    }

    @SuppressWarnings("unchecked")
    public ArrayContents(A array, boolean deep, ArrayOp<A> op) {
        if (deep) {
            if (array == null
                    || array.getClass().getComponentType().isPrimitive()) {
                throw new IllegalArgumentException(
                        "deep mode on primitive array");
            }
        }
        this.array = array;
        if (op != null)
            this.op = op;
        else if (array == null)
            this.op = null;
        else
            this.op = (ArrayOp<A>) ArrayOps.get(array.getClass());
        this.deep = deep;
    }

    public A getArray() {
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        A a;
        if (obj instanceof ArrayContents)
            a = ((ArrayContents<A, E>) obj).array;
        else
            a = (A) obj;
        if (array == a)
            return true;
        if (array == null || a == null)
            return false;
        if (deep)
            return Arrays.deepEquals((E[]) array, (E[]) a);
        return op.equals(array, a);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int hashCode() {
        if (deep)
            return Arrays.deepHashCode((E[]) array);
        return op.hashCode(array);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        return (E) Array.get(array, index);
    }

    @Override
    public int size() {
        return Array.getLength(array);
    }

}
