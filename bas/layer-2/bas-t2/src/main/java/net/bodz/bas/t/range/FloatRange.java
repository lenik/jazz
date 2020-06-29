package net.bodz.bas.t.range;

import java.util.Comparator;

import net.bodz.bas.c.primitive.FloatComparator;
import net.bodz.bas.err.ParseException;

public class FloatRange
        extends Range<FloatRange, Float> {

    private static final long serialVersionUID = 1L;

    static final FloatComparator ORDER = FloatComparator.INSTANCE;

    public FloatRange() {
        super(ORDER);
    }

    public FloatRange(Float start, Float end) {
        super(ORDER, start, end);
    }

    public FloatRange(boolean startInclusive, Float start, boolean endInclusive, Float end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public FloatRange(Comparator<? super Float> order) {
        super(order);
    }

    public FloatRange(Comparator<? super Float> order, Float start, Float end) {
        super(order, start, end);
    }

    public FloatRange(Comparator<? super Float> order, boolean startInclusive, Float start, boolean endInclusive,
            Float end) {
        super(order, startInclusive, start, endInclusive, end);
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
