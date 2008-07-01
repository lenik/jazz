package net.bodz.bas.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class PrefetchedIterator<E> implements Iterator<E> {

    private Object prefetched = UNDEFINED;

    @Override
    public final boolean hasNext() {
        if (prefetched == UNDEFINED)
            prefetched = fetch();
        return prefetched != END;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final E next() {
        if (prefetched == UNDEFINED) {
            Object e = fetch();
            if (e != END)
                return (E) e;
            prefetched = END;
            throw new NoSuchElementException();
        }
        if (prefetched == END)
            return null;
        E t = (E) prefetched;
        prefetched = UNDEFINED;
        return t;
    }

    protected abstract Object fetch();

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private final static Object   UNDEFINED = new Object();
    protected final static Object END       = new Object();

}
