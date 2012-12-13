package net.bodz.bas.t.order;

public abstract class AbstractOrderComparator<T, order_t extends Comparable<order_t>>
        extends AbstractNonNullComparator<T> {

    /**
     * @param object
     *            Non-<code>null</code> object in target type. maybe <code>null</code>
     * @return Nullable value in order type.
     */
    public abstract order_t getOrder(T object);

    @Override
    public int compareNonNull(T o1, T o2) {
        order_t c1 = getOrder(o1);
        order_t c2 = getOrder(o2);
        if (c1 == c2)
            return 0;
        if (c1 == null)
            return getNullOrder();
        if (c2 == null)
            return -getNullOrder();
        int cmp = c1.compareTo(c2);
        if (cmp != 0)
            return cmp;
        else
            return compareFallback(o1, o2);
    }

}
