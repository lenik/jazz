package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.CreateException;
import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.impl.AbstractParser;

public class GetInstanceParser
        extends AbstractParser<Object> {

    @Override
    public Object parse(String className)
            throws ParseException {
        try {
            Class<?> clazz = Class.forName(className);
            return Types.getClassInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        } catch (CreateException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
