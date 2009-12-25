package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.exceptions.TypeConvertException;

public class LongParser
        extends Parser {

    @Override
    public Long parse(String text)
            throws ParseException {
        try {
            return Long.valueOf(text);
        } catch (TypeConvertException e) {
            throw new ParseException(e);
        }
    }

}
