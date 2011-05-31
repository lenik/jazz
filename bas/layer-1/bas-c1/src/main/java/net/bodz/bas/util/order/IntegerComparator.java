package net.bodz.bas.util.order;

public class IntegerComparator
        extends AbstractNonNullComparator<Integer> {

    @Override
    public int compareNonNull(Integer a, Integer b) {
        return a - b;
    }

    public static final IntegerComparator INSTANCE = new IntegerComparator();

}
