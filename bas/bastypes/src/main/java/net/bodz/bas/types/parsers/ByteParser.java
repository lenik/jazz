package net.bodz.bas.types.parsers;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.VarCast;

public class ByteParser implements TypeParser {

    @Override
    public Byte parse(String text) throws ParseException {
        try {
            return VarCast.toByte(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
