package net.bodz.bas.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.nls.TypesNLS;

public abstract class PrefetchedIterator<T> implements Iterator<T> {

    private static final int UNKNOWN = 0;
    private static final int HASNEXT = 1;
    private static final int CACHED  = 2;
    private static final int END     = -1;
    private int              _state  = UNKNOWN;

    private T                cache;

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
            cache = fetch();
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
        case UNKNOWN:
            return TypesNLS.getString("PrefetchedIterator.UNKNOWN"); //$NON-NLS-1$
        case HASNEXT:
            return TypesNLS.getString("PrefetchedIterator.HASNEXT"); //$NON-NLS-1$
        case CACHED:
            return TypesNLS.getString("PrefetchedIterator.CACHED") + cache + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        case END:
            return TypesNLS.getString("PrefetchedIterator.END"); //$NON-NLS-1$
        }
        return TypesNLS.getString("PrefetchedIterator.badState") + _state + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }

}
