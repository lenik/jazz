package net.bodz.bas.util.order;

import java.math.BigInteger;

public class DateComparator
        extends AbstractNonNullComparator<BigInteger> {

    @Override
    public int compareNonNull(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    public static final DateComparator INSTANCE = new DateComparator();

}
