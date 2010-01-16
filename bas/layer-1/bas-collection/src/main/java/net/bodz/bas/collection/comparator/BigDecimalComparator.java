package net.bodz.bas.collection.comparator;

import java.math.BigDecimal;

public class BigDecimalComparator
        implements NonNullComparator<BigDecimal> {

    private BigDecimalComparator() {
    }

    @Override
    public int compare(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }

    private static final BigDecimalComparator instance = new BigDecimalComparator();

    public static BigDecimalComparator getInstance() {
        return instance;
    }

}
