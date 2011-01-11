package net.bodz.bas.collection.comparator;

public class DoubleComparator
        implements NonNullComparator<Double> {

    private DoubleComparator() {
    }

    @Override
    public int compare(Double a, Double b) {
        return a < b ? -1 : a > b ? 1 : 0;
    }

    private static final DoubleComparator instance = new DoubleComparator();

    public static DoubleComparator getInstance() {
        return instance;
    }

}
