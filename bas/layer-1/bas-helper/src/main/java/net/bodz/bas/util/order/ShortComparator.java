package net.bodz.bas.util.order;

public class ShortComparator
        extends AbstractNonNullComparator<Short> {

    @Override
    public int compareNonNull(Short a, Short b) {
        return a - b;
    }

    public static final ShortComparator INSTANCE = new ShortComparator();

}
