package net.bodz.bas.t.vector;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayAsVector<T>
        implements
            AnyVector<T> {

    T[] array;

    public ArrayAsVector(T[] array) {
        if (array == null)
            throw new NullPointerException("array");
        this.array = array;
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, T value) {
        array[index] = value;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.asList(array).iterator();
    }

}
