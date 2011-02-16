package net.bodz.bas.cli;

import java.lang.reflect.Field;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.meta.program.OptionGroup;

public class FieldOption<T>
        extends _Option<T> {

    private final Field field;

    @SuppressWarnings("unchecked")
    public FieldOption(String name, Field field, OptionGroup optgrp) {
        super(name, field, (Class<T>) field.getType(), optgrp);
        this.field = field;
        field.setAccessible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object get(Object instance)
            throws ReflectiveOperationException {
        return (T) Jdk7Reflect.get(field, instance);
    }

    @Override
    public void set(Object classobj, Object optval)
            throws ReflectiveOperationException {
        Object fieldval;
        if (multi) {
            fieldval = Jdk7Reflect.get(field, classobj);
            Object newfield = Util.addmulti(field.getType(), fieldval, optval);
            if (newfield == fieldval)
                return;
            fieldval = newfield;
        } else
            fieldval = optval;
        Jdk7Reflect.set(field, classobj, fieldval);
    }

}
