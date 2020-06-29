package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.AbstractParser;
import net.bodz.bas.typer.std.IParser;

public class DefaultRangeParser<range_t extends AbstractRange<?, val_t>, val_t>
        extends AbstractParser<range_t> {

    Class<range_t> rangeType;
    Class<val_t> valType;
    IParser<val_t> valParser;

    public DefaultRangeParser(Class<range_t> rangeType, Class<val_t> valType) {
        this.rangeType = rangeType;
        this.valType = valType;
        this.valParser = Typers.getTyper(valType, IParser.class);
    }

    @Override
    public range_t parse(String text, IOptions options)
            throws ParseException {
        text = text.trim();
        if (text.isEmpty() || text.length() < 3)
            throw new ParseException("Range should be in the format of ?START, END?.");

        range_t range = newRange();

        char startType = text.charAt(0);
        switch (startType) {
        case '[':
            range.setStartInclusive(true);
            break;
        case '(':
            range.setStartInclusive(false);
            break;
        default:
            throw new ParseException("Bad start type: '" + startType + "'");
        }

        char endType = text.charAt(text.length() - 1);
        switch (endType) {
        case ']':
            range.setEndInclusive(true);
            break;
        case ')':
            range.setEndInclusive(false);
            break;
        default:
            throw new ParseException("Bad end type: '" + endType + "'");
        }

        int comma = text.indexOf(',');
        if (comma == -1)
            throw new ParseException("Range should be in the format of ?START, END?.");

        String part1 = text.substring(1, comma).trim();
        String part2 = text.substring(comma + 1, text.length() - 1).trim();
        range.start = null;
        range.end = null;
        if (!part1.isEmpty())
            range.start = parseValue(part1);
        if (!part2.isEmpty())
            range.end = parseValue(part2);
        return range;
    }

    val_t parseValue(String s)
            throws ParseException {
        if (valParser == null)
            throw new ParseException("Unparsable value type: " + valType);
        return valParser.parse(s);
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
