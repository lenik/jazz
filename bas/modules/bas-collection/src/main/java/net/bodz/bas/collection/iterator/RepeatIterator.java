package net.bodz.bas.collection.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Repeat object self to iterator.
 */
public class RepeatIterator<T>
        implements Iterator<T> {

    private final T object;
    private final int count;

    private int index;

    /**
     * Repeat once by default.
     * 
     * @param object
     *            The object to repeat.
     */
    public RepeatIterator(T object) {
        this(object, 1);
    }

    /**
     * Repeat specified times.
     * 
     * @param object
     *            The object to repeat.
     * @param count
     *            Count of times to repeat.
     */
    public RepeatIterator(T object, int count) {
        this.object = object;
        this.count = count;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < count;
    }

    @Override
    public T next() {
        if (index >= count)
            throw new NoSuchElementException();
        index++;
        return object;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
