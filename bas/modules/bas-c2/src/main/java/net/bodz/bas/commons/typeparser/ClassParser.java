package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;

public class ClassParser extends Parser {

    @Override
    public Class<?> parse(String name) throws ParseException {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
