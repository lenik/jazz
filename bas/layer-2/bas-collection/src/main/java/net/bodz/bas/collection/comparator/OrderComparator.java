package net.bodz.bas.collection.comparator;

public abstract class OrderComparator<TargetType, OrderType extends Comparable<OrderType>>
        extends AbstractNonNullComparator<TargetType> {

    /**
     * @param object
     *            Non-<code>null</code> object in target type. maybe <code>null</code>
     * @return Nullable value in order type.
     */
    public abstract OrderType getOrder(TargetType object);

    @Override
    public int compareNonNull(TargetType o1, TargetType o2) {
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
