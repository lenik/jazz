package net.bodz.bas.t.range;

import net.bodz.bas.c.primitive.ShortComparator;
import net.bodz.bas.err.ParseException;

public class ShortRange
        extends AbstractRange<ShortRange, Short> {

    private static final long serialVersionUID = 1L;

    static final ShortComparator ORDER = ShortComparator.INSTANCE;

    public ShortRange() {
        super(ORDER);
    }

    public ShortRange(Short start, Short end) {
        super(ORDER, start, end);
    }

    @Override
    public ShortRange create(Short start, Short end) {
        return new ShortRange(start, end);
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
