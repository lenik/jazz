package net.bodz.bas.collection.comparator;

public class IntegerComparator
        extends AbstractNonNullComparator<Integer> {

    @Override
    public int compareNonNull(Integer a, Integer b) {
        return a - b;
    }

    private static final IntegerComparator instance = new IntegerComparator();

    public static IntegerComparator getInstance() {
        return instance;
    }

}
