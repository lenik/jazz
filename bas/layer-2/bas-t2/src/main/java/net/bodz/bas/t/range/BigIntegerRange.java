package net.bodz.bas.t.range;

import java.math.BigInteger;
import java.util.Comparator;

import net.bodz.bas.c.primitive.BigIntegerComparator;
import net.bodz.bas.err.ParseException;

public class BigIntegerRange
        extends Range<BigIntegerRange, BigInteger> {

    private static final long serialVersionUID = 1L;

    static final BigIntegerComparator ORDER = BigIntegerComparator.INSTANCE;

    public BigIntegerRange() {
        super(ORDER);
    }

    public BigIntegerRange(BigInteger start, BigInteger end) {
        super(ORDER, start, end);
    }

    public BigIntegerRange(boolean startInclusive, BigInteger start, boolean endInclusive, BigInteger end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public BigIntegerRange(Comparator<? super BigInteger> order) {
        super(order);
    }

    public BigIntegerRange(Comparator<? super BigInteger> order, BigInteger start, BigInteger end) {
        super(order, start, end);
    }

    public BigIntegerRange(Comparator<? super BigInteger> order, boolean startInclusive, BigInteger start,
            boolean endInclusive, BigInteger end) {
        super(order, startInclusive, start, endInclusive, end);
    }

    @Override
    public BigInteger parseValue(String s)
            throws ParseException {
        try {
            return new BigInteger(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public BigInteger preceding(BigInteger val) {
        BigInteger delta = BigInteger.ONE;
        BigInteger prec = val.subtract(delta);
        return prec;
    }

    @Override
    public BigInteger successor(BigInteger val) {
        BigInteger delta = BigInteger.ONE;
        BigInteger succ = val.add(delta);
        return succ;
    }

    @Override
    public String toString() {
        return "BigIntegerRange[" + start + "," + end + ")";
    }

}
