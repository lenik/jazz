package net.bodz.bas.t.range;

import java.math.BigDecimal;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class BigDecimalRangeVarConverter
        extends AbstractRangeVarConverter<BigDecimalRange, BigDecimal> {

    public BigDecimalRangeVarConverter() {
        super(BigDecimalRange.class);
    }

    @Override
    public BigDecimalRange fromString(String in)
            throws TypeConvertException {
        BigDecimalRange range = new BigDecimalRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public BigDecimalRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        String s = in.toString();
        BigDecimal bigDecimal = new BigDecimal(s);
        return new BigDecimalRange().point(bigDecimal);
    }

    /**
     * To <code>double</code> type.
     */
    @Override
    public Number toNumber(BigDecimalRange value) {
        if (value == null)
            return null;
        BigDecimal pt = value.getPointValue();
        if (pt == null)
            return null;
        return pt.doubleValue();
    }

    /**
     * <code>true</code> for non-zero values.
     */
    @Override
    public boolean toBoolean(BigDecimalRange value) {
        if (value == null)
            return false;
        BigDecimal val = value.getPointValue();
        if (BigDecimal.ZERO.equals(val))
            return false;
        return true;
    }

    public static final BigDecimalRangeVarConverter INSTANCE = new BigDecimalRangeVarConverter();

}
