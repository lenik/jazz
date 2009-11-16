package net.bodz.bas.types.parsers;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.util.Types;

public class GetInstanceParser implements TypeParser {

    @Override
    public Object parse(String className) throws ParseException {
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
