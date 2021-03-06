package net.bodz.bas.c.primitive;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class DoubleComparator
        extends AbstractNonNullComparator<Double> {

    @Override
    public int compareNonNull(Double a, Double b) {
        double _a = a;
        double _b = b;
        return _a < _b ? -1 : _a > _b ? 1 : 0;
    }

    public static final DoubleComparator INSTANCE = new DoubleComparator();

}
