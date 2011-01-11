package net.bodz.bas.collection.iterator;

import java.util.NoSuchElementException;

public class ImmIterIterator<T, X extends Exception>
        implements IteratorX<T, X> {

    private final ImmediateIteratorX<? extends T, ? extends X> immIter;

    private static final int UNKNOWN = 0;
    private static final int ITERATED = 1;
    private static final int ENDED = 2;
    private int state = UNKNOWN;

    private T lastIteratedValue;

    public ImmIterIterator(ImmediateIterableX<T, X> iterable, boolean allowOverlap)
            throws IteratorTargetException {
        if (iterable == null)
            throw new NullPointerException("iterable");
        try {
            this.immIter = iterable.iterator(allowOverlap);
        } catch (Exception e) {
            throw new IteratorTargetException(e.getMessage(), e);
        }
    }

    public ImmIterIterator(ImmediateIteratorX<? extends T, ? extends X> immIter) {
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
            } catch (Exception e) {
                throw new IteratorTargetException(e.getMessage(), e);
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
            } catch (Exception e) {
                throw new IteratorTargetException(e.getMessage(), e);
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
