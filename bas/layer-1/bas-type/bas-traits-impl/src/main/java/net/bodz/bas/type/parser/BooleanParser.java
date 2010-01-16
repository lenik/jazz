package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.AbstractParser;

public class BooleanParser
        extends AbstractParser<Boolean> {

    @Override
    public Boolean parse(String text)
            throws ParseException {
        try {
            return Boolean.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
