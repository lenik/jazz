package net.bodz.bas.util.order;

import java.math.BigInteger;

public class BigIntegerComparator
        extends AbstractNonNullComparator<BigInteger> {

    @Override
    public int compareNonNull(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    public static final BigIntegerComparator INSTANCE = new BigIntegerComparator();

}
