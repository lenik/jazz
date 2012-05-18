package net.bodz.bas.util.order;

/**
 * For two objects with the same priority, the order is undetermined. (identity hash code is used in
 * this case)
 * 
 * Override {@link #compare2(Object, Object)} when necessary.
 */
public class PriorityComparator
        extends AbstractNonNullComparator<IPriority> {

    @Override
    public final int compareNonNull(IPriority o1, IPriority o2) {
        int cmp = o1.getPriority() - o2.getPriority();
        if (cmp != 0)
            return cmp;
        return compare2(o1, o2);
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
    protected int compare2(Object o1, Object o2) {
        return compareIdentity(o1, o2);
    }

    public static final PriorityComparator INSTANCE = new PriorityComparator();

}
