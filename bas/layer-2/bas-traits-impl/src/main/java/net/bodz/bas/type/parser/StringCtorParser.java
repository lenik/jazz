package net.bodz.bas.type.parser;

import java.lang.reflect.Constructor;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.AbstractParser;

public class StringCtorParser
        extends AbstractParser<Object> {

    private final Constructor<?> ctor;

    public StringCtorParser(Constructor<?> constructor) {
        this.ctor = constructor;
    }

    public StringCtorParser(Class<?> type)
            throws NoSuchMethodException, SecurityException {
        ctor = type.getConstructor(String.class);
    }

    @Override
    public Object parse(String text)
            throws ParseException {
        try {
            return ctor.newInstance(text);
        } catch (ReflectiveOperationException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
