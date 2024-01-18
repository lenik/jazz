package net.bodz.bas.t.range;

import java.time.LocalTime;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class LocalTimeComparator
        extends AbstractNonNullComparator<LocalTime> {

    @Override
    public int compareNonNull(LocalTime o1, LocalTime o2) {
        return o1.compareTo(o2);
    }

    public static final LocalTimeComparator INSTANCE = new LocalTimeComparator();

}
