package net.bodz.bas.t.range;

import net.bodz.bas.c.primitive.LongComparator;
import net.bodz.bas.err.ParseException;

public class LongRange
        extends AbstractRange<LongRange, Long> {

    private static final long serialVersionUID = 1L;

    static final LongComparator ORDER = LongComparator.INSTANCE;

    public LongRange() {
        super(ORDER);
    }

    public LongRange(Long start, Long end) {
        super(ORDER, start, end);
    }

    @Override
    public LongRange create(Long start, Long end) {
        return new LongRange(start, end);
    }

    @Override
    public Long parseValue(String s)
            throws ParseException {
        try {
            return new Long(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Long preceding(Long val) {
        long prec = val.longValue();
        if (prec == Long.MIN_VALUE)
            return null;
        prec--;
        return prec;
    }

    @Override
    public Long successor(Long val) {
        long succ = val.longValue();
        if (succ == Long.MAX_VALUE)
            return null;
        succ++;
        return val;
    }

    @Override
    public String toString() {
        return "LongRange[" + start + "," + end + ")";
    }

}
