package net.bodz.bas.util.ints;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @see Iterator
 */
public interface IntIterator {

    /**
     * @see Iterator#hasNext()
     */
    boolean hasNext();

    /**
     * @see Iterator#next()
     */
    int next();

    /**
     * @see Iterator#remove()
     */
    void remove();

    IntIterator empty = new EmptyIntIterator();

}

final class EmptyIntIterator implements IntIterator {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public int next() {
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new NoSuchElementException();
    }

}
