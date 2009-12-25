package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;

public class IntegerParser
        extends Parser {

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
