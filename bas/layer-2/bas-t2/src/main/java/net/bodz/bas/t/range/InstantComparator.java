package net.bodz.bas.t.range;

import java.time.Instant;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class InstantComparator
        extends AbstractNonNullComparator<Instant> {

    @Override
    public int compareNonNull(Instant o1, Instant o2) {
        return o1.compareTo(o2);
    }

    public static final InstantComparator INSTANCE = new InstantComparator();

}
