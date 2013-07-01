package net.bodz.bas.c.primitive;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class FloatComparator
        extends AbstractNonNullComparator<Float> {

    @Override
    public int compareNonNull(Float a, Float b) {
        float _a = a;
        float _b = b;
        return _a < _b ? -1 : _a > _b ? 1 : 0;
    }

    public static final FloatComparator INSTANCE = new FloatComparator();

}
