package net.bodz.bas.type.parser;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class IntegerParser
        extends AbstractParser<Integer> {

    @Override
    public Integer parse(String text)
            throws ParseException {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e);
        }
    }

}
