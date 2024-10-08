package net.bodz.bas.t.order;

import java.util.Comparator;

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
    protected int compareFallback(IPriority o1, IPriority o2) {
        return compareIdentity(o1, o2);
    }

    @SuppressWarnings("unchecked")
    public static final <T> Comparator<T> getInstance() {
        return (Comparator<T>) INSTANCE;
    }

    public static final PriorityComparator INSTANCE = new PriorityComparator();

}
