package net.bodz.bas.collection.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T>
        implements Iterator<T> {

    private T[] array;
    private int next = 0;

    public ArrayIterator(T[] array, int next) {
        if (array == null)
            throw new NullPointerException("array");
        this.array = array;
        this.next = next;
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
        if (next == array.length)
            array = null; // free as early as possible.
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
