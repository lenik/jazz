package net.bodz.bas.collection.comparator;

public class BooleanComparator
        implements NonNullComparator<Boolean> {

    private BooleanComparator() {
    }

    @Override
    public int compare(Boolean a, Boolean b) {
        if (a)
            return b ? 0 : 1;
        else
            return b ? -1 : 0;
        // int _a = a ? 1 : 0;
        // int _b = b ? 1 : 0;
        // return _a - _b;
    }

    private static final BooleanComparator instance = new BooleanComparator();

    public static BooleanComparator getInstance() {
        return instance;
    }

}
