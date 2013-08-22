package net.bodz.bas.mf.spi.extra;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractParser;
import net.bodz.bas.rtx.IOptions;

public class GetInstanceParser
        extends AbstractParser<Object> {

    @Override
    public Object parse(String className, IOptions options)
            throws ParseException {
        try {
            Class<?> clazz = Class.forName(className);
            return SingletonUtil.getClassInstance(clazz);
        } catch (ReflectiveOperationException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
