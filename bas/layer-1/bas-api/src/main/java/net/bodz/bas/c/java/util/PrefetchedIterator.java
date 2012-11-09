package net.bodz.bas.c.java.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class PrefetchedIterator<T>
        implements Iterator<T> {

    private static final int UNKNOWN = 0;
    private static final int HASNEXT = 1;
    private static final int CACHED = 2;
    private static final int END = -1;
    private int _state = UNKNOWN;

    private T cache;

    @Override
    public final boolean hasNext() {
        switch (_state) {
        case HASNEXT:
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
        case HASNEXT:
            T x = fetch();
            return x;
        case CACHED:
            T t = cache;
            // cache = fetch();
            _state = UNKNOWN;
            return t;
        case END:
            throw new NoSuchElementException();
        }
        _state = CACHED;
        cache = fetch();
        return next();
    }

    protected final T discard() {
        _state = UNKNOWN;
        return null;
    }

    protected final T discard(boolean hasNext) {
        if (hasNext)
            _state = HASNEXT;
        else
            _state = UNKNOWN;
        return null;
    }

    protected final T end() {
        _state = END;
        return null;
    }

    /**
     * Fetch the next element and return.
     * 
     * @return Ignored if ended.
     */
    protected abstract T fetch();

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        switch (_state) {
        case UNKNOWN:
            return "UNKNOWN";
        case HASNEXT:
            return "HASNEXT";
        case CACHED:
            return "CACHED(" + cache + ")";
        case END:
            return "END";
        }
        return "Bad state(" + _state + ")";
    }

}
