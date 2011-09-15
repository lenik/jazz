package net.bodz.bas.util.primitive;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class FloatComparator
        extends AbstractNonNullComparator<Float> {

    @Override
    public int compareNonNull(Float a, Float b) {
        return a < b ? -1 : a > b ? 1 : 0;
    }

    public static final FloatComparator INSTANCE = new FloatComparator();

}
