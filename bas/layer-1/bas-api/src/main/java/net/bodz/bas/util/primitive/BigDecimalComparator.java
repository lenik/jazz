package net.bodz.bas.util.primitive;

import java.math.BigDecimal;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class BigDecimalComparator
        extends AbstractNonNullComparator<BigDecimal> {

    @Override
    public int compareNonNull(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }

    public static final BigDecimalComparator INSTANCE = new BigDecimalComparator();

}
