package net.bodz.bas.collection.comparator;

import java.math.BigInteger;

public class DateComparator
        implements NonNullComparator<BigInteger> {

    private DateComparator() {
    }

    @Override
    public int compare(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }

    private static final DateComparator instance = new DateComparator();

    public static DateComparator getInstance() {
        return instance;
    }

}
