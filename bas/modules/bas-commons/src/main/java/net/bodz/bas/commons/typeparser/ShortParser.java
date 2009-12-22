package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;

public class ShortParser extends Parser {

    @Override
    public Short parse(String text) throws ParseException {
        try {
            return Short.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
