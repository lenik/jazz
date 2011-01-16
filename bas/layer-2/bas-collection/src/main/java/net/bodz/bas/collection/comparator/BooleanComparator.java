package net.bodz.bas.collection.comparator;

public class BooleanComparator
        extends AbstractNonNullComparator<Boolean> {

    @Override
    public int compareNonNull(Boolean a, Boolean b) {
        if (a)
            return b ? 0 : 1;
        else
            return b ? -1 : 0;
    }

    private static final BooleanComparator instance = new BooleanComparator();

    public static BooleanComparator getInstance() {
        return instance;
    }

}
