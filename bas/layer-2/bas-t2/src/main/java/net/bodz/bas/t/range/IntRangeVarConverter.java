package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class IntRangeVarConverter
        extends AbstractRangeVarConverter<IntRange, Integer> {

    public IntRangeVarConverter() {
        super(IntRange.class);
    }

    @Override
    public IntRange fromString(String in)
            throws TypeConvertException {
        IntRange range = new IntRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public IntRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        int val = in.intValue();
        return new IntRange().point(val);
    }

    @Override
    public Integer toNumber(IntRange value) {
        if (value == null)
            return null;
        return value.getPointValue();
    }

    @Override
    public boolean toBoolean(IntRange value) {
        Integer number = toNumber(value);
        if (number == null)
            return false;
        if (number.intValue() == 0)
            return false;
        return true;
    }

    public static final IntRangeVarConverter INSTANCE = new IntRangeVarConverter();

}
