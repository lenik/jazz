package net.bodz.bas.t.order;

/**
 * For comparators who always expect non-<code>null</code> values to compare.
 */
public abstract class AbstractNonNullComparator<T>
        implements NonNullComparator<T> {

    public int compare(T o1, T o2) {
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return getNullOrder();
        if (o2 == null)
            return -getNullOrder();
        return compareNonNull(o1, o2);
    }

    /**
     * The default null order is null-first.
     */
    @Override
    public int getNullOrder() {
        return -1;
    }

    @Override
    public abstract int compareNonNull(T o1, T o2);

    /**
     * Fallback compare.
     * 
     * @param o1
     *            Non-<code>null</code> object to compare.
     * @param o2
     *            Non-<code>null</code> object to compare.
     */
    protected int compareFallback(T o1, T o2) {
        return compareIdentity(o1, o2);
    }

    protected static final int compareIdentity(Object o1, Object o2) {
        assert o1 != o2;
        int id1 = System.identityHashCode(o1);
        int id2 = System.identityHashCode(o2);
        return id1 - id2;
    }

}
