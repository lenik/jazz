package net.bodz.bas.util.order;

public class LongComparator
        extends AbstractNonNullComparator<Long> {

    @Override
    public int compareNonNull(Long a, Long b) {
        return a < b ? -1 : a > b ? 1 : 0;
    }

    public static final LongComparator INSTANCE = new LongComparator();

}
