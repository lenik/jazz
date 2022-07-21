package net.bodz.bas.t.range;

import java.math.BigDecimal;
import java.util.Comparator;

import net.bodz.bas.c.primitive.BigDecimalComparator;
import net.bodz.bas.err.ParseException;

public class BigDecimalRange
        extends Range<BigDecimalRange, BigDecimal> {

    private static final long serialVersionUID = 1L;

    static final BigDecimalComparator ORDER = BigDecimalComparator.INSTANCE;

    public BigDecimalRange() {
        super(ORDER);
    }

    public BigDecimalRange(BigDecimal start, BigDecimal end) {
        super(ORDER, start, end);
    }

    public BigDecimalRange(boolean startInclusive, BigDecimal start, boolean endInclusive, BigDecimal end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public BigDecimalRange(Comparator<? super BigDecimal> order) {
        super(order);
    }

    public BigDecimalRange(Comparator<? super BigDecimal> order, BigDecimal start, BigDecimal end) {
        super(order, start, end);
    }

    public BigDecimalRange(Comparator<? super BigDecimal> order, boolean startInclusive, BigDecimal start,
            boolean endInclusive, BigDecimal end) {
        super(order, startInclusive, start, endInclusive, end);
    }

    @Override
    public BigDecimal parseValue(String s)
            throws ParseException {
        try {
            return new BigDecimal(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public BigDecimal preceding(BigDecimal val) {
        BigDecimal delta = BigDecimal.ONE;
        BigDecimal prec = val.subtract(delta);
        return prec;
    }

    @Override
    public BigDecimal successor(BigDecimal val) {
        BigDecimal delta = BigDecimal.ONE;
        BigDecimal succ = val.add(delta);
        return succ;
    }

    @Override
    public String toString() {
        return "BigDecimalRange[" + start + "," + end + ")";
    }

}
