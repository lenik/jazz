package net.bodz.bas.util.ints;

import java.util.NoSuchElementException;

/**
 * @see java.util.Iterator
 */
public interface IntIterator {

    /**
     * @see java.util.Iterator#hasNext()
     */
    boolean hasNext();

    /**
     * @see java.util.Iterator#next()
     */
    int next();

    /**
     * @see java.util.Iterator#remove()
     */
    void remove();

    IntIterator empty = new EmptyIntIterator();

}

final class EmptyIntIterator
        implements IntIterator {

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
