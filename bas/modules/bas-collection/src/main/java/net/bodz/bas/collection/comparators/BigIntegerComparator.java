package net.bodz.bas.collection.comparators;

import java.math.BigInteger;

public class BigIntegerComparator
        implements NonNullComparator<BigInteger> {

    private BigIntegerComparator() {
    }

    @Override
    public int compare(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    private static final BigIntegerComparator instance = new BigIntegerComparator();

    public static BigIntegerComparator getInstance() {
        return instance;
    }

}
