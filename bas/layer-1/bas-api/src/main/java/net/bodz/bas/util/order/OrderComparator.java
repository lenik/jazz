package net.bodz.bas.util.order;

public abstract class OrderComparator<T, OrderType extends Comparable<OrderType>>
        extends AbstractNonNullComparator<T> {

    /**
     * @param object
     *            Non-<code>null</code> object in target type. maybe <code>null</code>
     * @return Nullable value in order type.
     */
    public abstract OrderType getOrder(T object);

    @Override
    public int compareNonNull(T o1, T o2) {
        OrderType c1 = getOrder(o1);
        OrderType c2 = getOrder(o2);
        if (c1 == c2)
            return 0;
        if (c1 == null)
            return getNullOrder();
        if (c2 == null)
            return -getNullOrder();
        return c1.compareTo(c2);
    }

}
