package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.AbstractParser;

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
