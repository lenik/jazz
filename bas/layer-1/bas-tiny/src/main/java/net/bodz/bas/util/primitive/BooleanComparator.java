package net.bodz.bas.util.primitive;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class BooleanComparator
        extends AbstractNonNullComparator<Boolean> {

    @Override
    public int compareNonNull(Boolean a, Boolean b) {
        if (a)
            return b ? 0 : 1;
        else
            return b ? -1 : 0;
    }

    public static final BooleanComparator INSTANCE = new BooleanComparator();

}
