package net.bodz.bas.collection.comparator;

public class LongComparator
        implements NonNullComparator<Long> {

    private LongComparator() {
    }

    @Override
    public int compare(Long a, Long b) {
        return a < b ? -1 : a > b ? 1 : 0;
    }

    private static final LongComparator instance = new LongComparator();

    public static LongComparator getInstance() {
        return instance;
    }

}
