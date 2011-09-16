package net.bodz.bas.util.primitive;

import java.math.BigInteger;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class BigIntegerComparator
        extends AbstractNonNullComparator<BigInteger> {

    @Override
    public int compareNonNull(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    public static final BigIntegerComparator INSTANCE = new BigIntegerComparator();

}
