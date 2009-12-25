package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;

public class FloatParser extends Parser {

    @Override
    public Float parse(String text) throws ParseException {
        try {
            return Float.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
