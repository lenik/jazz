package net.bodz.bas.cli.model;

import java.lang.reflect.Field;

import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.spi.reflect.ReflectProperty;

public class FieldOption
        extends TransientOption {

    private static final long serialVersionUID = 1L;

    Field field;

    public FieldOption(Field field) {
        super(field.getDeclaringClass(), field.getName(), field, field.getType());
        this.field = field;
    }

    @Override
    public IProperty property() {
        return new ReflectProperty(field);
    }

}
