package net.bodz.bas.t.specmap;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class DomainComparator
        extends AbstractNonNullComparator<String> {

    @Override
    public int compareNonNull(String o1, String o2) {
        return o1.compareTo(o2);
    }

    public static final DomainComparator INSTANCE = new DomainComparator();

}
