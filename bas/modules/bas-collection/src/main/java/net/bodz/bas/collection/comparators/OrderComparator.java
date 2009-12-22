package net.bodz.bas.collection.comparators;

public abstract class OrderComparator<TargetType, OrderType extends Comparable<OrderType>>
        implements NonNullComparator<TargetType> {

    /**
     * @param object
     *            maybe <code>null</code>
     * @return non-<code>null</code> value.
     */
    public abstract OrderType getOrder(TargetType object);

    @Override
    public int compare(TargetType o1, TargetType o2) {
        OrderType c1 = getOrder(o1);
        OrderType c2 = getOrder(o2);
        return c1.compareTo(c2);
    }

}
