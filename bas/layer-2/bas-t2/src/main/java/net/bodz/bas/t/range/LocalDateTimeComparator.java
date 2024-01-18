package net.bodz.bas.t.range;

import java.time.LocalDateTime;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class LocalDateTimeComparator
        extends AbstractNonNullComparator<LocalDateTime> {

    @Override
    public int compareNonNull(LocalDateTime o1, LocalDateTime o2) {
        return o1.compareTo(o2);
    }

    public static final LocalDateTimeComparator INSTANCE = new LocalDateTimeComparator();

}
