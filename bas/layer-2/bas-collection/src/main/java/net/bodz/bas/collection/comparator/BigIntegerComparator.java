package net.bodz.bas.collection.comparator;

import java.math.BigInteger;

public class BigIntegerComparator
        extends AbstractNonNullComparator<BigInteger> {

    @Override
    public int compareNonNull(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    private static final BigIntegerComparator instance = new BigIntegerComparator();

    public static BigIntegerComparator getInstance() {
        return instance;
    }

}
