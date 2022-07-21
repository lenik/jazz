package net.bodz.bas.t.range;

import java.math.BigInteger;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class BigIntegerRangeVarConverter
        extends AbstractRangeVarConverter<BigIntegerRange, BigInteger> {

    public BigIntegerRangeVarConverter() {
        super(BigIntegerRange.class);
    }

    @Override
    public BigIntegerRange fromString(String in)
            throws TypeConvertException {
        BigIntegerRange range = new BigIntegerRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public BigIntegerRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        String s = in.toString();
        BigInteger bigInteger = new BigInteger(s);
        return new BigIntegerRange().point(bigInteger);
    }

    /**
     * To <code>long</code> type.
     */
    @Override
    public Number toNumber(BigIntegerRange value) {
        if (value == null)
            return null;
        BigInteger pt = value.getPointValue();
        if (pt == null)
            return null;
        return pt.longValue();
    }

    /**
     * <code>true</code> for non-zero values.
     */
    @Override
    public boolean toBoolean(BigIntegerRange value) {
        if (value == null)
            return false;
        BigInteger val = value.getPointValue();
        if (BigInteger.ZERO.equals(val))
            return false;
        return true;
    }

    public static final BigIntegerRangeVarConverter INSTANCE = new BigIntegerRangeVarConverter();

}
