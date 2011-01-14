package net.bodz.bas.type.parser.primitive;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

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
