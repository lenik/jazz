package net.bodz.bas.typer.spi.extra;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractParser;

public class GetInstanceParser
        extends AbstractParser<Object> {

    @Override
    public Object parse(String className, IOptions options)
            throws ParseException {
        try {
            Class<?> clazz = Class.forName(className);
            return SingletonUtil.callGetInstance(clazz);
        } catch (Exception e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
