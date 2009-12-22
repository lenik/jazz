package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;

public class BooleanParser extends Parser {

    @Override
    public Boolean parse(String text) throws ParseException {
        try {
            return Boolean.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
