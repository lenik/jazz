package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class ShortRangeVarConverter
        extends AbstractRangeVarConverter<ShortRange, Short> {

    public ShortRangeVarConverter() {
        super(ShortRange.class);
    }

    @Override
    public ShortRange fromString(String in)
            throws TypeConvertException {
        ShortRange range = new ShortRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public ShortRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        short val = in.shortValue();
        return new ShortRange().point(val);
    }

    @Override
    public Short toNumber(ShortRange value) {
        if (value == null)
            return null;
        return value.getPointValue();
    }

    @Override
    public boolean toBoolean(ShortRange value) {
        Short number = toNumber(value);
        if (number == null)
            return false;
        if (number.shortValue() == 0)
            return false;
        return true;
    }

    public static final ShortRangeVarConverter INSTANCE = new ShortRangeVarConverter();

}
