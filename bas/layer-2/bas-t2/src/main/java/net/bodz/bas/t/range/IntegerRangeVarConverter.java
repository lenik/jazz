package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class IntegerRangeVarConverter
        extends AbstractRangeVarConverter<IntegerRange, Integer> {

    public IntegerRangeVarConverter() {
        super(IntegerRange.class);
    }

    @Override
    public IntegerRange fromString(String in)
            throws TypeConvertException {
        IntegerRange range = new IntegerRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public IntegerRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        int val = in.intValue();
        return new IntegerRange().point(val);
    }

    @Override
    public Integer toNumber(IntegerRange value) {
        if (value == null)
            return null;
        return value.getPointValue();
    }

    @Override
    public boolean toBoolean(IntegerRange value) {
        Integer number = toNumber(value);
        if (number == null)
            return false;
        if (number.intValue() == 0)
            return false;
        return true;
    }

    public static final IntegerRangeVarConverter INSTANCE = new IntegerRangeVarConverter();

}
