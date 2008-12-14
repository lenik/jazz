package net.bodz.bas.types.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.bodz.bas.cli.CLIError;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class StringCtorParser implements TypeParser {

    private final Constructor<?> ctor;

    public StringCtorParser(Constructor<?> constructor) throws CLIError {
        this.ctor = constructor;
    }

    public StringCtorParser(Class<?> type) throws CLIError {
        try {
            ctor = type.getConstructor(String.class);
        } catch (SecurityException e) {
            throw new CLIError(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new CLIError(e.getMessage(), e);
        }
    }

    @Override
    public Object parse(String text) throws ParseException {
        try {
            return ctor.newInstance(text);
        } catch (IllegalArgumentException e) {
            throw new CLIError(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new CLIError(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new CLIError(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new CLIError(e.getMessage(), e);
        }
    }

}
