package net.bodz.bas.t.range;

import java.time.ZonedDateTime;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ZonedDateTimeComparator
        extends AbstractNonNullComparator<ZonedDateTime> {

    @Override
    public int compareNonNull(ZonedDateTime o1, ZonedDateTime o2) {
        return o1.compareTo(o2);
    }

    public static final ZonedDateTimeComparator INSTANCE = new ZonedDateTimeComparator();

}
