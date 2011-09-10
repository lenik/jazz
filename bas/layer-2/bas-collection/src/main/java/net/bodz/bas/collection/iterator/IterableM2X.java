package net.bodz.bas.collection.iterator;

public class IterableM2X<T, X extends Exception>
        implements IterableX<T, X> {

    private final IterableMX<T, X> mx;
    private final boolean allowOverlap;

    /**
     * Same as {@link #ImmIterIterable(IterableMX, boolean)} with <code>allowOverlap</code> set to
     * <code>false</code>.
     */
    public IterableM2X(IterableMX<T, X> mx) {
        this(mx, false);
    }

    public IterableM2X(IterableMX<T, X> mx, boolean allowOverlap) {
        if (mx == null)
            throw new NullPointerException("mx");
        this.mx = mx;
        this.allowOverlap = allowOverlap;
    }

    @Override
    public IteratorX<T, X> iteratorX() {
        IteratorMX<? extends T, ? extends X> immIterator;
        try {
            immIterator = mx.iterator(allowOverlap);
        } catch (Exception e) {
            throw new IteratorTargetException(e.getMessage(), e);
        }
        return new IteratorM2X<T, X>(immIterator);
    }

}
