package net.bodz.bas.collection.iterator;

public class ImmIterIterable<T, X extends Exception>
        implements IterableX<T, X> {

    private final ImmediateIterableX<T, X> immIterable;
    private final boolean allowOverlap;

    /**
     * Same as {@link #ImmIterIterable(ImmediateIterableX, boolean)} with <code>allowOverlap</code>
     * set to <code>false</code>.
     */
    public ImmIterIterable(ImmediateIterableX<T, X> immIterable) {
        this(immIterable, false);
    }

    public ImmIterIterable(ImmediateIterableX<T, X> immIterable, boolean allowOverlap) {
        if (immIterable == null)
            throw new NullPointerException("immIterable");
        this.immIterable = immIterable;
        this.allowOverlap = allowOverlap;
    }

    @Override
    public IteratorX<T, X> iterator() {
        ImmediateIteratorX<? extends T, ? extends X> immIterator;
        try {
            immIterator = immIterable.iterator(allowOverlap);
        } catch (Exception e) {
            throw new IteratorTargetException(e.getMessage(), e);
        }
        return new ImmIterIterator<T, X>(immIterator);
    }

}
