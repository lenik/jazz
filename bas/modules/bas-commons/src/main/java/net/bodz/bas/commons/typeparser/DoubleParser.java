package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;

public class DoubleParser extends Parser {

    @Override
    public Double parse(String text) throws ParseException {
        try {
            return Double.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
