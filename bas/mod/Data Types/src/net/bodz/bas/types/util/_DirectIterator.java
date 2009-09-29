package net.bodz.bas.types.util;

import java.util.NoSuchElementException;

public abstract class _DirectIterator<T, X extends Throwable> implements DirectIterator<T, X> {

    @Override
    public boolean isOverlapped() {
        return false;
    }

    @Override
    public T getNext() throws X, NoSuchElementException {
        if (next())
            return get();
        else
            throw new NoSuchElementException();
    }

}
