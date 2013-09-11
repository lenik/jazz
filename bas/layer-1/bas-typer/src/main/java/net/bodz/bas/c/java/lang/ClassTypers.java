package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

@SuppressWarnings("rawtypes")
public class ClassTypers
        extends AbstractCommonTypers<Class> {

    public ClassTypers() {
        super(Class.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public Class<?> parse(String name, IOptions options)
            throws ParseException {
        ClassLoader classLoader = options.get(ClassLoader.class, null);
        try {
            if (classLoader == null)
                return Class.forName(name);
            else
                return Class.forName(name, false, classLoader);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
