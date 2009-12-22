package net.bodz.bas.collection.comparators;

public class ShortComparator
        implements NonNullComparator<Short> {

    private ShortComparator() {
    }

    @Override
    public int compare(Short a, Short b) {
        return a - b;
    }

    private static final ShortComparator instance = new ShortComparator();

    public static ShortComparator getInstance() {
        return instance;
    }

}
