package net.bodz.bas.cli;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.script.ScriptException;

import net.bodz.bas.meta.program.OptionGroup;

public class PropertyOption<T>
        extends _Option<T> {

    private final PropertyDescriptor property;
    private final Method readf;
    private final Method writef;

    @SuppressWarnings("unchecked")
    public PropertyOption(String name, PropertyDescriptor property, OptionGroup optgrp) {
        super(name, property.getReadMethod(), // AnnotatedElement
                property.getPropertyType(), optgrp);
        this.property = property;
        this.readf = property.getReadMethod();
        this.writef = property.getWriteMethod();
        readf.setAccessible(true);
        writef.setAccessible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Object classobj)
            throws ScriptException {
        try {
            return (T) readf.invoke(classobj);
        } catch (IllegalArgumentException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new ScriptException(e.getMessage(), e);
        }
    }

    @Override
    public void set(Object classobj, T optval)
            throws ScriptException {
        Object propval;
        try {
            if (multi) {
                propval = readf.invoke(classobj);
                Object newprop = Util.addmulti(property.getPropertyType(), propval, optval);
                if (newprop == propval)
                    return;
                propval = newprop;
            } else
                propval = optval;
            writef.invoke(classobj, propval);
        } catch (IllegalArgumentException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new ScriptException(e.getMessage(), e);
        }
    }

}
