package net.bodz.bas.collection.comparator;

import java.util.Comparator;

public abstract class ReorderComparator<TargetType, OrderType>
        implements NonNullComparator<TargetType> {

    private final Comparator<OrderType> orderComparator;

    public ReorderComparator(Comparator<OrderType> orderComparator) {
        if (orderComparator == null)
            throw new NullPointerException("orderComparator");
        this.orderComparator = orderComparator;
    }

    public abstract OrderType getOrder(TargetType object);

    @Override
    public int compare(TargetType o1, TargetType o2) {
        OrderType p1 = getOrder(o1);
        OrderType p2 = getOrder(o2);
        return orderComparator.compare(p1, p2);
    }

}
