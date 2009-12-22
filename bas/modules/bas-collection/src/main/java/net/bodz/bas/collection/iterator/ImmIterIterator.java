package net.bodz.bas.collection.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ImmIterIterator<T, X extends Throwable>
        implements Iterator<T> {

    private final ImmediateIterator<T, X> immIter;

    private static final int UNKNOWN = 0;
    private static final int ITERATED = 1;
    private static final int ENDED = 2;
    private int state = UNKNOWN;

    private T lastIteratedValue;

    public ImmIterIterator(ImmediateIterator<T, X> immIter) {
        if (immIter == null)
            throw new NullPointerException("immIter");
        this.immIter = immIter;
    }

    @Override
    public boolean hasNext() {
        switch (state) {
        case UNKNOWN:
        default:
            try {
                lastIteratedValue = immIter.next();
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            if (lastIteratedValue == null)
                if (immIter.isEnded()) {
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
    public T next() {
        switch (state) {
        case UNKNOWN:
        default:
            try {
                lastIteratedValue = immIter.next();
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            if (lastIteratedValue == null)
                if (immIter.isEnded()) {
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
