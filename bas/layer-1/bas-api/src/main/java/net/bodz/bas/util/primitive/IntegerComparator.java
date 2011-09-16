package net.bodz.bas.util.primitive;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class IntegerComparator
        extends AbstractNonNullComparator<Integer> {

    @Override
    public int compareNonNull(Integer a, Integer b) {
        return a - b;
    }

    public static final IntegerComparator INSTANCE = new IntegerComparator();

}
