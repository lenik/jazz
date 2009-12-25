package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;

public class ByteParser extends Parser {

    @Override
    public Byte parse(String text) throws ParseException {
        try {
            return Byte.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
