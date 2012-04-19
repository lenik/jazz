package net.bodz.bas.cli;

import java.lang.reflect.Field;

import net.bodz.bas.meta.program.OptionGroup;

public class FieldOption<T>
        extends _Option<T> {

    private final Field field;

    public FieldOption(String name, Field field, OptionGroup optgrp) {
        super(name, field, (Class<T>) field.getType(), optgrp);
        this.field = field;
        field.setAccessible(true);
    }

    @Override
    public Object get(Object instance)
            throws ReflectiveOperationException {
        return (T) field.get(instance);
    }

    @Override
    public void set(Object classobj, Object optval)
            throws ReflectiveOperationException {
        Object fieldval;
        if (multi) {
            fieldval = field.get(classobj);
            Object newfield = Util.addmulti(field.getType(), fieldval, optval);
            if (newfield == fieldval)
                return;
            fieldval = newfield;
        } else
            fieldval = optval;
        field.set(classobj, fieldval);
    }

}
