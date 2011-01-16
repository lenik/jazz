package net.bodz.bas.collection.comparator;

import java.math.BigDecimal;

public class BigDecimalComparator
        extends AbstractNonNullComparator<BigDecimal> {

    @Override
    public int compareNonNull(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }

    private static final BigDecimalComparator instance = new BigDecimalComparator();

    public static BigDecimalComparator getInstance() {
        return instance;
    }

}
