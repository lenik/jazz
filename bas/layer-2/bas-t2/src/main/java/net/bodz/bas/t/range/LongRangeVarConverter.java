package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class LongRangeVarConverter
        extends AbstractRangeVarConverter<LongRange, Long> {

    public LongRangeVarConverter() {
        super(LongRange.class);
    }

    @Override
    public LongRange fromString(String in)
            throws TypeConvertException {
        LongRange range = new LongRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public LongRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long val = in.longValue();
        return new LongRange().point(val);
    }

    @Override
    public Long toNumber(LongRange value) {
        if (value == null)
            return null;
        return value.getPointValue();
    }

    @Override
    public boolean toBoolean(LongRange value) {
        Long number = toNumber(value);
        if (number == null)
            return false;
        if (number.longValue() == 0L)
            return false;
        return true;
    }

    public static final LongRangeVarConverter INSTANCE = new LongRangeVarConverter();

}
