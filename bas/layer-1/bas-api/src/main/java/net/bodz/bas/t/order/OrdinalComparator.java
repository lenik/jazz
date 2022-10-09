package net.bodz.bas.t.order;

/**
 * For two objects with the same ordinal, the order is undetermined. (identity hash code is used in
 * this case)
 *
 * Override {@link #compare2(Object, Object)} when necessary.
 */
public class OrdinalComparator
        extends AbstractNonNullComparator<IOrdinal> {

    @Override
    public final int compareNonNull(IOrdinal o1, IOrdinal o2) {
        int cmp = o1.getOrdinal() - o2.getOrdinal();
        if (cmp != 0)
            return cmp;
        else
            return compareFallback(o1, o2);
    }

    /**
     * The default implementation compares the two objects by their identity hash code.
     *
     * @param o1
     *            The first non-<code>null</code> object to compare.
     * @param o2
     *            The second non-<code>null</code> object to compare.
     * @return Compare result.
     */
    @Override
    protected int compareFallback(IOrdinal o1, IOrdinal o2) {
        return compareIdentity(o1, o2);
    }

    public static final OrdinalComparator INSTANCE = new OrdinalComparator();

}
