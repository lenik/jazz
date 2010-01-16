package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.AbstractParser;

public class ByteParser
        extends AbstractParser<Byte> {

    @Override
    public Byte parse(String text)
            throws ParseException {
        try {
            return Byte.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
