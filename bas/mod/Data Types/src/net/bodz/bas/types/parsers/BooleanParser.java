package net.bodz.bas.types.parsers;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.AutoType;
import net.bodz.bas.types.TypeParser;

public class BooleanParser implements TypeParser {

    @Override
    public Boolean parse(String text) throws ParseException {
        try {
            return AutoType.toBoolean(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
