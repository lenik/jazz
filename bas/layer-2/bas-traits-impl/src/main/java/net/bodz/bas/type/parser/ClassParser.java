package net.bodz.bas.type.parser;

import net.bodz.bas.type.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class ClassParser
        extends AbstractParser<Class<?>> {

    @Override
    public Class<?> parse(String name)
            throws ParseException {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
