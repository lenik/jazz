package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class DoubleRangeVarConverter
        extends AbstractRangeVarConverter<DoubleRange, Double> {

    public DoubleRangeVarConverter() {
        super(DoubleRange.class);
    }

    @Override
    public DoubleRange fromString(String in)
            throws TypeConvertException {
        DoubleRange range = new DoubleRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public DoubleRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        double val = in.doubleValue();
        return new DoubleRange().point(val);
    }

    @Override
    public Double toNumber(DoubleRange value) {
        if (value == null)
            return null;
        return value.getPointValue();
    }

    @Override
    public boolean toBoolean(DoubleRange value) {
        Double number = toNumber(value);
        if (number == null)
            return false;
        if (number.isNaN())
            return false;
        if (number.doubleValue() == 0.0)
            return false;
        return true;
    }

    public static final DoubleRangeVarConverter INSTANCE = new DoubleRangeVarConverter();

}
