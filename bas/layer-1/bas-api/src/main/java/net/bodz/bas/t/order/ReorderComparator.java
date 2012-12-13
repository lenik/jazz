package net.bodz.bas.t.order;

import java.util.Comparator;

public abstract class ReorderComparator<TargetType, OrderType>
        extends AbstractNonNullComparator<TargetType> {

    private final Comparator<OrderType> orderComparator;

    public ReorderComparator(Comparator<OrderType> orderComparator) {
        if (orderComparator == null)
            throw new NullPointerException("orderComparator");
        this.orderComparator = orderComparator;
    }

    public abstract OrderType getOrder(TargetType object);

    @Override
    public int compareNonNull(TargetType o1, TargetType o2) {
        OrderType p1 = getOrder(o1);
        OrderType p2 = getOrder(o2);
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return getNullOrder();
        if (o2 == null)
            return -getNullOrder();
        return orderComparator.compare(p1, p2);
    }

}
