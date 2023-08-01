package net.bodz.bas.t.range;

import org.joda.time.LocalDate;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class LocalDateComparator
        extends AbstractNonNullComparator<LocalDate> {

    @Override
    public int compareNonNull(LocalDate o1, LocalDate o2) {
        return o1.compareTo(o2);
    }

    public static final LocalDateComparator INSTANCE = new LocalDateComparator();

}
