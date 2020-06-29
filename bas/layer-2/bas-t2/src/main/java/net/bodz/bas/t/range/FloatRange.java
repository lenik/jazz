package net.bodz.bas.t.range;

import net.bodz.bas.c.primitive.FloatComparator;
import net.bodz.bas.err.ParseException;

public class FloatRange
        extends AbstractRange<FloatRange, Float> {

    private static final long serialVersionUID = 1L;

    static final FloatComparator ORDER = FloatComparator.INSTANCE;

    public FloatRange() {
        super(ORDER);
    }

    public FloatRange(Float start, Float end) {
        super(ORDER, start, end);
    }

    @Override
    public FloatRange create(Float start, Float end) {
        return new FloatRange(start, end);
    }

    @Override
    public Float parseValue(String s)
            throws ParseException {
        try {
            return new Float(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Float preceding(Float val) {
        float prec = val.floatValue();
        if (prec == Float.NEGATIVE_INFINITY)
            return null;
        if (prec == Float.NaN)
            return Float.NaN;
        prec -= Float.MIN_VALUE;
        return prec;
    }

    @Override
    public Float successor(Float val) {
        float succ = val.floatValue();
        if (succ == Float.POSITIVE_INFINITY)
            return null;
        if (succ == Float.NaN)
            return Float.NaN;
        succ += Float.MIN_VALUE;
        return succ;
    }

    @Override
    public String toString() {
        return "FloatRange[" + start + "," + end + ")";
    }

}
