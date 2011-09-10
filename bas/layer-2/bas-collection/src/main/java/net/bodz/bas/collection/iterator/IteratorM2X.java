package net.bodz.bas.collection.iterator;

import java.util.NoSuchElementException;

import net.bodz.bas.util.exception.Err;

public class IteratorM2X<T, X extends Throwable>
        implements IteratorX<T, X> {

    private final IteratorMX<? extends T, ? extends X> mx;

    private static final int UNKNOWN = 0;
    private static final int ITERATED = 1;
    private static final int ENDED = 2;
    private int state = UNKNOWN;

    private T lastIteratedValue;

    public IteratorM2X(IterableMX<T, X> mx, boolean allowOverlap) {
        if (mx == null)
            throw new NullPointerException("mx");
        this.mx = mx.iterator(allowOverlap);
    }

    public IteratorM2X(IteratorMX<? extends T, ? extends X> mx) {
        if (mx == null)
            throw new NullPointerException("mx");
        this.mx = mx;
    }

    @Override
    public boolean hasNext()
            throws X {
        switch (state) {
        case UNKNOWN:
        default:
            try {
                lastIteratedValue = mx._next();
            } catch (Throwable e) {
                throw Err.throwOrWrap(IteratorTargetException.class, e);
            }
            if (lastIteratedValue == null)
                if (mx.isEnded()) {
                    state = ENDED;
                    return false;
                }
            state = ITERATED;
        case ITERATED:
            return true;
        case ENDED:
            return false;
        }
    }

    @Override
    public T next()
            throws X {
        switch (state) {
        case UNKNOWN:
        default:
            lastIteratedValue = mx._next();
            if (lastIteratedValue == null)
                if (mx.isEnded()) {
                    state = ENDED;
                    throw new NoSuchElementException();
                }
            return lastIteratedValue;
        case ITERATED:
            state = UNKNOWN;
            return lastIteratedValue;
        case ENDED:
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
