package net.bodz.bas.collection.comparators;

public class ByteComparator
        implements NonNullComparator<Byte> {

    private ByteComparator() {
    }

    @Override
    public int compare(Byte a, Byte b) {
        return a - b;
    }

    private static final ByteComparator instance = new ByteComparator();

    public static ByteComparator getInstance() {
        return instance;
    }

}
