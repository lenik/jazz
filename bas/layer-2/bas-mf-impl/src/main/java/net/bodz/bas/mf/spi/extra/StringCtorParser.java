package net.bodz.bas.mf.spi.extra;

import java.lang.reflect.Constructor;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractParser;
import net.bodz.bas.rtx.IOptions;

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
    public Object parse(String text, IOptions options)
            throws ParseException {
        try {
            return ctor.newInstance(text);
        } catch (ReflectiveOperationException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
