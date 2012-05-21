package net.bodz.bas.cli.opt;

import java.lang.reflect.Field;

public class FieldOption
        extends AbstractOption {

    Field field;

    public FieldOption(Field field) {
        super(field.getDeclaringClass(), field.getName(), field, field.getType());
    }

}
