package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

@SuppressWarnings("rawtypes")
public class ClassTypers
        extends AbstractCommonTypers<Class> {

    public ClassTypers() {
        super(Class.class);
    }

    @Override
    protected Object _query(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
            return this;
        }
        return null;
    }

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
