package net.bodz.bas.t.range;

import net.bodz.bas.c.primitive.IntegerComparator;
import net.bodz.bas.err.ParseException;

public class IntRange
        extends AbstractRange<IntRange, Integer> {

    private static final long serialVersionUID = 1L;

    static final IntegerComparator ORDER = IntegerComparator.INSTANCE;

    public IntRange() {
        super(ORDER);
    }

    public IntRange(Integer start, Integer end) {
        super(ORDER, start, end);
    }

    @Override
    public IntRange create(Integer start, Integer end) {
        return new IntRange(start, end);
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
        return val - 1;
    }

    @Override
    public Integer successor(Integer val) {
        return val + 1;
    }

    @Override
    public String toString() {
        return "IntRange[" + start + "," + end + ")";
    }

}
