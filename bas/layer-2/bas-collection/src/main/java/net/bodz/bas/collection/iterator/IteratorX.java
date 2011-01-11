package net.bodz.bas.collection.iterator;

import java.util.Iterator;

public interface IteratorX<T, X extends Exception>
        extends Iterator<T> {

    @Override
    boolean hasNext()
            throws IteratorTargetException;

    @Override
    T next()
            throws IteratorTargetException;

}
