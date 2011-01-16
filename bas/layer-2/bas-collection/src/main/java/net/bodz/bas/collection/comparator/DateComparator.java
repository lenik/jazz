package net.bodz.bas.collection.comparator;

import java.math.BigInteger;

public class DateComparator
        extends AbstractNonNullComparator<BigInteger> {

    @Override
    public int compareNonNull(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    private static final DateComparator instance = new DateComparator();

    public static DateComparator getInstance() {
        return instance;
    }

}
