package net.bodz.bas.type.java.lang;

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
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
