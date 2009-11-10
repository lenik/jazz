package net.bodz.bas.cli;

import java.lang.reflect.Field;

import net.bodz.bas.cli.a.OptionGroup;
import net.bodz.bas.lang.script.ScriptException;

public class FieldOption<T> extends _Option<T> {

    private final Field field;

    @SuppressWarnings("unchecked")
    public FieldOption(String name, Field field, OptionGroup optgrp) {
        super(name, field, (Class<T>) field.getType(), optgrp);
        this.field = field;
        field.setAccessible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Object classobj) throws ScriptException {
        try {
            return (T) field.get(classobj);
        } catch (IllegalArgumentException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ScriptException(e.getMessage(), e);
        }
    }

    @Override
    public void set(Object classobj, T optval) throws ScriptException {
        Object fieldval;
        try {
            if (multi) {
                fieldval = field.get(classobj);
                Object newfield = Util.addmulti(field.getType(), fieldval, optval);
                if (newfield == fieldval)
                    return;
                fieldval = newfield;
            } else
                fieldval = optval;
            field.set(classobj, fieldval);
        } catch (IllegalArgumentException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ScriptException(e.getMessage(), e);
        }
    }

}
