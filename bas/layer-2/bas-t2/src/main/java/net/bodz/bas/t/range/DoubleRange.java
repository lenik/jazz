package net.bodz.bas.t.range;

import java.util.Comparator;

import net.bodz.bas.c.primitive.DoubleComparator;
import net.bodz.bas.err.ParseException;

public class DoubleRange
        extends Range<DoubleRange, Double> {

    private static final long serialVersionUID = 1L;

    static final DoubleComparator ORDER = DoubleComparator.INSTANCE;

    public DoubleRange() {
        super(ORDER);
    }

    public DoubleRange(Double start, Double end) {
        super(ORDER, start, end);
    }

    public DoubleRange(boolean startInclusive, Double start, boolean endInclusive, Double end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public DoubleRange(Comparator<? super Double> order) {
        super(order);
    }

    public DoubleRange(Comparator<? super Double> order, Double start, Double end) {
        super(order, start, end);
    }

    public DoubleRange(Comparator<? super Double> order, boolean startInclusive, Double start, boolean endInclusive,
            Double end) {
        super(order, startInclusive, start, endInclusive, end);
    }

    @Override
    public Double parseValue(String s)
            throws ParseException {
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Double preceding(Double val) {
        double prec = val.doubleValue();
        if (prec == Double.NEGATIVE_INFINITY)
            return null;
        if (prec == Double.NaN)
            return Double.NaN;
        prec -= Double.MIN_VALUE;
        return prec;
    }

    @Override
    public Double successor(Double val) {
        double succ = val.doubleValue();
        if (succ == Double.POSITIVE_INFINITY)
            return null;
        if (succ == Double.NaN)
            return Double.NaN;
        succ += Double.MIN_VALUE;
        return succ;
    }

    @Override
    public String toString() {
        return "DoubleRange[" + start + "," + end + ")";
    }

}
