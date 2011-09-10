package net.bodz.bas.collection.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T>
        implements Iterator<T> {

    private final T[] array;
    private int next;

    public ArrayIterator(T[] array) {
        this(array, 0);
    }

    public ArrayIterator(T[] array, int start) {
        if (array == null)
            throw new NullPointerException("array");
        this.array = array;
        this.next = start;
    }

    @Override
    public boolean hasNext() {
        return next < array.length;
    }

    @Override
    public T next() {
        if (next >= array.length)
            throw new NoSuchElementException();
        T item = array[next++];
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static <T> ArrayIterator<T> getInstance(T[] array) {
        return new ArrayIterator<T>(array);
    }

}
