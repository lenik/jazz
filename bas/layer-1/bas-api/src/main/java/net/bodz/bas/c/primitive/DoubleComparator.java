package net.bodz.bas.c.primitive;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class DoubleComparator
        extends AbstractNonNullComparator<Double> {

    @Override
    public int compareNonNull(Double a, Double b) {
        return a < b ? -1 : a > b ? 1 : 0;
    }

    public static final DoubleComparator INSTANCE = new DoubleComparator();

}
