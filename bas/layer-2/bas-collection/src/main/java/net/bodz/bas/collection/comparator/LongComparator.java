package net.bodz.bas.collection.comparator;

public class LongComparator
        extends AbstractNonNullComparator<Long> {

    @Override
    public int compareNonNull(Long a, Long b) {
        return a < b ? -1 : a > b ? 1 : 0;
    }

    private static final LongComparator instance = new LongComparator();

    public static LongComparator getInstance() {
        return instance;
    }

}
