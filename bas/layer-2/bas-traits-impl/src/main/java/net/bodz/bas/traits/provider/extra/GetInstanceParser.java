package net.bodz.bas.traits.provider.extra;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.type.SingletonUtil;

public class GetInstanceParser
        extends AbstractParser<Object> {

    @Override
    public Object parse(String className)
            throws ParseException {
        try {
            Class<?> clazz = Class.forName(className);
            return SingletonUtil.getClassInstance(clazz);
        } catch (ReflectiveOperationException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
