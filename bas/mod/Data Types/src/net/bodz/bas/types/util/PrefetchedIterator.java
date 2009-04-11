package net.bodz.bas.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class PrefetchedIterator<T> implements Iterator<T> {

    private static final int UNKNOWN = 0;
    private static final int CACHED  = 1;
    private static final int END     = 2;
    private int              _state  = UNKNOWN;

    private T                cache;

    @Override
    public final boolean hasNext() {
        switch (_state) {
        case CACHED:
            return true;
        case END:
            return false;
        }
        _state = CACHED;
        cache = fetch();
        return hasNext();
    }

    @Override
    public final T next() {
        switch (_state) {
        case CACHED:
            T t = cache;
            cache = fetch();
            return t;
        case END:
            throw new NoSuchElementException();
        }
        _state = CACHED;
        cache = fetch();
        return next();
    }

    protected final T end() {
        _state = END;
        return null;
    }

    /**
     * @return ignored if ended.
     */
    protected abstract T fetch();

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        switch (_state) {
        case CACHED:
            return "CACHED(" + cache + ")";
        case END:
            return "END";
        }
        return "UNKNOWN";
    }
}
