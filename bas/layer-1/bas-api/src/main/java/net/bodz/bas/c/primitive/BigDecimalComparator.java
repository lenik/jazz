package net.bodz.bas.c.primitive;

import java.math.BigDecimal;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class BigDecimalComparator
        extends AbstractNonNullComparator<BigDecimal> {

    @Override
    public int compareNonNull(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }

    public static final BigDecimalComparator INSTANCE = new BigDecimalComparator();

}
