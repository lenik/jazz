package net.bodz.bas.cli;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.meta.program.OptionGroup;

public class PropertyOption<T>
        extends _Option<T> {

    private final PropertyDescriptor property;
    private final Method readf;
    private final Method writef;

    public PropertyOption(String name, PropertyDescriptor property, OptionGroup optgrp) {
        super(name, property.getReadMethod(), // AnnotatedElement
                property.getPropertyType(), optgrp);
        this.property = property;
        this.readf = property.getReadMethod();
        this.writef = property.getWriteMethod();
        readf.setAccessible(true);
        writef.setAccessible(true);
    }

    @Override
    public T get(Object classobj)
            throws ReflectiveOperationException {
        return (T) readf.invoke(classobj);
    }

    @Override
    public void set(Object classobj, Object optval)
            throws ReflectiveOperationException {
        Object propval;
        if (multi) {
            propval = readf.invoke(classobj);
            Object newprop = Util.addmulti(property.getPropertyType(), propval, optval);
            if (newprop == propval)
                return;
            propval = newprop;
        } else
            propval = optval;
        writef.invoke(classobj, propval);
    }

}
