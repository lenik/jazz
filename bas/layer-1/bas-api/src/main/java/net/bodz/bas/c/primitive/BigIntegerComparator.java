package net.bodz.bas.c.primitive;

import java.math.BigInteger;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class BigIntegerComparator
        extends AbstractNonNullComparator<BigInteger> {

    @Override
    public int compareNonNull(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    public static final BigIntegerComparator INSTANCE = new BigIntegerComparator();

}
