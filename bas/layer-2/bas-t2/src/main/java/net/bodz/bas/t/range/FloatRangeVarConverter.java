package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class FloatRangeVarConverter
        extends AbstractRangeVarConverter<FloatRange, Float> {

    public FloatRangeVarConverter() {
        super(FloatRange.class);
    }

    @Override
    public FloatRange fromString(String in)
            throws TypeConvertException {
        FloatRange range = new FloatRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public FloatRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        float val = in.floatValue();
        return new FloatRange().point(val);
    }

    @Override
    public Float toNumber(FloatRange value) {
        if (value == null)
            return null;
        return value.getPointValue();
    }

    @Override
    public boolean toBoolean(FloatRange value) {
        Float number = toNumber(value);
        if (number == null)
            return false;
        if (number.isNaN())
            return false;
        if (number.floatValue() == 0.0f)
            return false;
        return true;
    }

    public static final FloatRangeVarConverter INSTANCE = new FloatRangeVarConverter();

}
