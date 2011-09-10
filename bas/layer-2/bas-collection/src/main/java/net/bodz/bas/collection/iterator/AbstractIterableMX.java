package net.bodz.bas.collection.iterator;

import java.util.NoSuchElementException;

public class AbstractIterableMX<T, X extends Throwable>
        extends AbstractIterableX<T, X>
        implements IterableMX<T, X> {

    @Override
    public IteratorMX<? extends T, ? extends X> iterator(boolean allowOverlap) {
        return null;
    }

    @Override
    public IteratorX<T, X> iteratorX() {
        return new IteratorM2X<T, X>(this, true);
    }

    public T first()
            throws X {
        IteratorMX<? extends T, ? extends X> iterator = iterator(true);
        T first = iterator._next();
        if (first == null && iterator.isEnded())
            throw new NoSuchElementException();
        return first;
    }

    public T first(T fallback)
            throws X {
        IteratorMX<? extends T, ? extends X> iterator = iterator(true);
        T first = iterator._next();
        if (first == null && iterator.isEnded())
            return fallback;
        return first;
    }

}
