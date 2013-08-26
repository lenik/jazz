package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;

@SuppressWarnings("rawtypes")
public class ClassMdaFeatures
        extends AbstractCommonMdaFeatures<Class> {

    public ClassMdaFeatures() {
        super(Class.class);
    }

    @Override
    protected Object _query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
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
