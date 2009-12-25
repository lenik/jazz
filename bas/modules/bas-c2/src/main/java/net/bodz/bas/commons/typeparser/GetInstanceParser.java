package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.commons.util.Types;

public class GetInstanceParser extends Parser {

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
