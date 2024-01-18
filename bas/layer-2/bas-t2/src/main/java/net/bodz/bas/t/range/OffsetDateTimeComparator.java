package net.bodz.bas.t.range;

import java.time.OffsetDateTime;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class OffsetDateTimeComparator
        extends AbstractNonNullComparator<OffsetDateTime> {

    @Override
    public int compareNonNull(OffsetDateTime o1, OffsetDateTime o2) {
        return o1.compareTo(o2);
    }

    public static final OffsetDateTimeComparator INSTANCE = new OffsetDateTimeComparator();

}
