package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.exceptions.TypeConvertException;
import net.bodz.bas.type.traits.impl.AbstractParser;

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
