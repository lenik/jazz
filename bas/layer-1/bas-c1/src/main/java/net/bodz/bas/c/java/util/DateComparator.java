package net.bodz.bas.c.java.util;

import java.math.BigInteger;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class DateComparator
        extends AbstractNonNullComparator<BigInteger> {

    @Override
    public int compareNonNull(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    public static final DateComparator INSTANCE = new DateComparator();

}
