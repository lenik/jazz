package net.bodz.bas.type.parser.primitive;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class ShortParser
        extends AbstractParser<Short> {

    @Override
    public Short parse(String text)
            throws ParseException {
        try {
            return Short.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
