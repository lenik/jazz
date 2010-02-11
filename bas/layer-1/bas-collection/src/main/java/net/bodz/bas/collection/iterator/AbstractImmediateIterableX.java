package net.bodz.bas.collection.iterator;

import java.util.NoSuchElementException;

public class AbstractImmediateIterableX<T, X extends Exception>
        implements ImmediateIterableX<T, X> {

    @Override
    public ImmediateIteratorX<? extends T, ? extends X> iterator(boolean allowOverlap)
            throws X {
        return null;
    }

    @Override
    public IteratorX<T, X> iterator()
            throws IteratorTargetException {
        return new ImmIterIterator<T, X>(this, true);
    }

    public T first()
            throws X {
        ImmediateIteratorX<? extends T, ? extends X> iterator = iterator(true);
        T first = iterator.next();
        if (first == null && iterator.isEnded())
            throw new NoSuchElementException();
        return first;
    }

    public T first(T fallback)
            throws X {
        ImmediateIteratorX<? extends T, ? extends X> iterator = iterator(true);
        T first = iterator.next();
        if (first == null && iterator.isEnded())
            return fallback;
        return first;
    }

}
