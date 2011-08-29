package net.bodz.bas.traits.provider.extra;

import java.lang.reflect.Constructor;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.NoSuchMethodException;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.traits.AbstractParser;

public class StringCtorParser
        extends AbstractParser<Object> {

    private final Constructor<?> ctor;

    public StringCtorParser(Constructor<?> constructor) {
        this.ctor = constructor;
    }

    public StringCtorParser(Class<?> type)
            throws NoSuchMethodException, SecurityException {
        ctor = Jdk7Reflect.getConstructor(type, String.class);
    }

    @Override
    public Object parse(String text)
            throws ParseException {
        try {
            return Jdk7Reflect.newInstance(ctor, text);
        } catch (ReflectiveOperationException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
