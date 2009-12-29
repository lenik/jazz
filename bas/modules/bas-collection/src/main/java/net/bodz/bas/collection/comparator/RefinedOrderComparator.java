package net.bodz.bas.collection.comparator;

import java.util.Comparator;

public abstract class RefinedOrderComparator<TargetType, OrderType extends Comparable<OrderType>>
        extends OrderComparator<TargetType, OrderType> {

    private final Comparator<TargetType> refineComparator;

    public RefinedOrderComparator(Comparator<TargetType> secondComparator) {
        if (secondComparator == null)
            throw new NullPointerException("comparator");
        this.refineComparator = secondComparator;
    }

    @Override
    public int compare(TargetType o1, TargetType o2) {
        int cmp = super.compare(o1, o2);
        if (cmp != 0)
            return cmp;
        else
            return refineComparator.compare(o1, o2);
    }

}
