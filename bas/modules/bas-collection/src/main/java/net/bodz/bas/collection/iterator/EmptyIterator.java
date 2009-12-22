package net.bodz.bas.collection.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIterator<T>
        implements Iterator<T> {

    public boolean hasNext() {
        return false;
    }

    public T next() {
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    private static final EmptyIterator<?> EMPTY = new EmptyIterator<Object>();

    @SuppressWarnings ( "unchecked")
    public static <T> Iterator<T> getInstance() {
        return (Iterator<T>) EMPTY;
    }

}
