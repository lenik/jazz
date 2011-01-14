package net.bodz.bas.type.parser;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;
import net.bodz.bas.util.exception.TypeConvertException;

public class LongParser
        extends AbstractParser<Long> {

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
