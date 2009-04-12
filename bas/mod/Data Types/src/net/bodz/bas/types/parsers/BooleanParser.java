package net.bodz.bas.types.parsers;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.VarCast;

public class BooleanParser implements TypeParser {

    @Override
    public Boolean parse(String text) throws ParseException {
        try {
            return VarCast.toBoolean(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
