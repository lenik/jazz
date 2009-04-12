package net.bodz.bas.types.parsers;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.VarCast;

public class FloatParser implements TypeParser {

    @Override
    public Float parse(String text) throws ParseException {
        try {
            return VarCast.toFloat(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
