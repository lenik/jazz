package net.bodz.bas.cli;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
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

    @SuppressWarnings("unchecked")
    @Override
    public T get(Object classobj)
            throws ReflectiveOperationException {
        return (T) Jdk7Reflect.invoke(readf, classobj);
    }

    @Override
    public void set(Object classobj, Object optval)
            throws ReflectiveOperationException {
        Object propval;
        if (multi) {
            propval = Jdk7Reflect.invoke(readf, classobj);
            Object newprop = Util.addmulti(property.getPropertyType(), propval, optval);
            if (newprop == propval)
                return;
            propval = newprop;
        } else
            propval = optval;
        Jdk7Reflect.invoke(writef, classobj, propval);
    }

}
