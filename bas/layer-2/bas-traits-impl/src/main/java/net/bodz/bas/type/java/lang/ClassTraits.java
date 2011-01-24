package net.bodz.bas.type.java.lang;

import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.util.exception.ParseException;

@SuppressWarnings("rawtypes")
public class ClassTraits
        extends AbstractCommonTraits<Class> {

    public ClassTraits() {
        super(Class.class);
    }

    @Override
    protected Object query(int traitsIndex) {
        switch (traitsIndex) {
        case IParser.traitsIndex:
            return this;
        }
        return null;
    }

    @Override
    public Class<?> parse(String name)
            throws ParseException {
        try {
            return Jdk7Reflect.forName(name);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
