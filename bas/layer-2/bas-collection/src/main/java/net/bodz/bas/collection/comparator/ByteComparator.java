package net.bodz.bas.collection.comparator;

public class ByteComparator
        extends AbstractNonNullComparator<Byte> {

    @Override
    public int compareNonNull(Byte a, Byte b) {
        return a - b;
    }

    private static final ByteComparator instance = new ByteComparator();

    public static ByteComparator getInstance() {
        return instance;
    }

}
