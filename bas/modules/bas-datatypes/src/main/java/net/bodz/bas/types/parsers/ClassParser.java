package net.bodz.bas.types.parsers;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class ClassParser implements TypeParser {

    @Override
    public Class<?> parse(String name) throws ParseException {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
