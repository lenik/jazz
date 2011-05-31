package net.bodz.bas.util.order;

import java.math.BigDecimal;

public class BigDecimalComparator
        extends AbstractNonNullComparator<BigDecimal> {

    @Override
    public int compareNonNull(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }

    public static final BigDecimalComparator INSTANCE = new BigDecimalComparator();

}
