package net.bodz.bas.collection.comparator;

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

}
