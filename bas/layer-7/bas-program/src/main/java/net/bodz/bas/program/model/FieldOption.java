package net.bodz.bas.program.model;

import java.lang.reflect.Field;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.provider.reflect.ReflectProperty;
import net.bodz.mda.xjdoc.model.FieldDoc;

public class FieldOption
        extends TransientOption {

    private final Field field;

    public FieldOption(Field field, FieldDoc fieldDoc) {
        super(field.getName(), field.getType(), field, fieldDoc);
        this.field = field;
    }

    @Override
    public IProperty property() {
        return new ReflectProperty(field, getXjdoc());
    }

}
