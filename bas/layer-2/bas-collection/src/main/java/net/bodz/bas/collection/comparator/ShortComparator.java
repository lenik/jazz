package net.bodz.bas.collection.comparator;

public class ShortComparator
        extends AbstractNonNullComparator<Short> {

    @Override
    public int compareNonNull(Short a, Short b) {
        return a - b;
    }

    private static final ShortComparator instance = new ShortComparator();

    public static ShortComparator getInstance() {
        return instance;
    }

}
