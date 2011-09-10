package net.bodz.bas.collection.iterator;

public interface IteratorX<T, X extends Throwable> {

    boolean hasNext()
            throws X;

    T next()
            throws X;

    void remove()
            throws X, UnsupportedOperationException;

}
