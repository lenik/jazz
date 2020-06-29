package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractParser;

public class RangeParser<range_t extends Range<?, val_t>, val_t>
        extends AbstractParser<range_t> {

    Class<range_t> rangeType;

    public RangeParser(Class<range_t> rangeType) {
        this.rangeType = rangeType;
    }

    @Override
    public range_t parse(String text, IOptions options)
            throws ParseException {
        range_t range = newRange();
        range.parse(text);
        return range;
    }

    range_t newRange()
            throws ParseException {
        try {
            return rangeType.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new ParseException("Can't instantiate " + rangeType, e);
        }
    }

}
