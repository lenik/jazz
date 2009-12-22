package net.bodz.bas.collection.comparators;

public class IntegerComparator
        implements NonNullComparator<Integer> {

    private IntegerComparator() {
    }

    @Override
    public int compare(Integer a, Integer b) {
        return a - b;
    }

    private static final IntegerComparator instance = new IntegerComparator();

    public static IntegerComparator getInstance() {
        return instance;
    }

}
