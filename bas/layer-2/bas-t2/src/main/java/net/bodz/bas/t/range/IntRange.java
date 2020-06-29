package net.bodz.bas.t.range;

import java.util.Comparator;

import net.bodz.bas.c.primitive.IntegerComparator;
import net.bodz.bas.err.ParseException;

public class IntRange
        extends Range<IntRange, Integer> {

    private static final long serialVersionUID = 1L;

    static final IntegerComparator ORDER = IntegerComparator.INSTANCE;

    public IntRange() {
        super(ORDER);
    }

    public IntRange(Integer start, Integer end) {
        super(ORDER, start, end);
    }

    public IntRange(boolean startInclusive, Integer start, boolean endInclusive, Integer end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public IntRange(Comparator<? super Integer> order) {
        super(order);
    }

    public IntRange(Comparator<? super Integer> order, Integer start, Integer end) {
        super(order, start, end);
    }

    public IntRange(Comparator<? super Integer> order, boolean startInclusive, Integer start, boolean endInclusive,
            Integer end) {
        super(order, startInclusive, start, endInclusive, end);
    }

    @Override
    public Integer parseValue(String s)
            throws ParseException {
        try {
            return new Integer(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Integer preceding(Integer val) {
        int prec = val.intValue();
        if (prec == Integer.MIN_VALUE)
            return null;
        prec--;
        return prec;
    }

    @Override
    public Integer successor(Integer val) {
        int succ = val.intValue();
        if (succ == Integer.MAX_VALUE)
            return null;
        succ++;
        return succ;
    }

    @Override
    public String toString() {
        return "IntRange[" + start + "," + end + ")";
    }

}
