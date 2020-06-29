package net.bodz.bas.t.range;

import java.util.Comparator;

import net.bodz.bas.c.primitive.ShortComparator;
import net.bodz.bas.err.ParseException;

public class ShortRange
        extends Range<ShortRange, Short> {

    private static final long serialVersionUID = 1L;

    static final ShortComparator ORDER = ShortComparator.INSTANCE;

    public ShortRange() {
        super(ORDER);
    }

    public ShortRange(Short start, Short end) {
        super(ORDER, start, end);
    }

    public ShortRange(boolean startInclusive, Short start, boolean endInclusive, Short end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public ShortRange(Comparator<? super Short> order) {
        super(order);
    }

    public ShortRange(Comparator<? super Short> order, Short start, Short end) {
        super(order, start, end);
    }

    public ShortRange(Comparator<? super Short> order, boolean startInclusive, Short start, boolean endInclusive,
            Short end) {
        super(order, startInclusive, start, endInclusive, end);
    }

    @Override
    public Short parseValue(String s)
            throws ParseException {
        try {
            return new Short(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Short preceding(Short val) {
        short prec = val.shortValue();
        if (prec == Short.MIN_VALUE)
            return null;
        prec--;
        return prec;
    }

    @Override
    public Short successor(Short val) {
        short succ = val.shortValue();
        if (succ == Short.MAX_VALUE)
            return null;
        succ++;
        return succ;
    }

    @Override
    public String toString() {
        return "ShortRange[" + start + "," + end + ")";
    }

}
