package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.AbstractParser;

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
