package net.bodz.bas.t.order;

import java.util.Comparator;

public abstract class RefinedReorderComparator<TargetType, OrderType>
        extends ReorderComparator<TargetType, OrderType> {

    private final Comparator<TargetType> targetComparator;

    public RefinedReorderComparator(Comparator<OrderType> orderComparator, Comparator<TargetType> targetComparator) {
        super(orderComparator);
        if (targetComparator == null)
            throw new NullPointerException("targetComparator");
        this.targetComparator = targetComparator;
    }

    @Override
    public int compareNonNull(TargetType o1, TargetType o2) {
        int cmp = super.compare(o1, o2);
        if (cmp != 0)
            return cmp;
        return targetComparator.compare(o1, o2);
    }

}
