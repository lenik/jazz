package net.bodz.bas.collection.comparator;

public class FloatComparator
        extends AbstractNonNullComparator<Float> {

    @Override
    public int compareNonNull(Float a, Float b) {
        return a < b ? -1 : a > b ? 1 : 0;
    }

    private static final FloatComparator instance = new FloatComparator();

    public static FloatComparator getInstance() {
        return instance;
    }

}
