package net.bodz.uni.shelj.codegen.java.member;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.err.UnexpectedException;

public class BeanPropertyMember
        implements
            IMember {

    IPropertyDescriptor propertyDescriptor;

    public BeanPropertyMember(IPropertyDescriptor propertyDescriptor) {
        if (propertyDescriptor == null)
            throw new NullPointerException("propertyDescriptor");
        this.propertyDescriptor = propertyDescriptor;
    }

    Method getAnyAccessor() {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter != null)
            return getter;
        Method setter = propertyDescriptor.getWriteMethod();
        if (setter != null)
            return setter;
        throw new UnexpectedException();
    }

    @Override
    public AnnotatedElement toAnnotatedElement() {
        return getAnyAccessor();
    }

    @Override
    public Class<?> getType() {
        return propertyDescriptor.getPropertyType();
    }

    @Override
    public int getModifiers() {
        Method accessor = getAnyAccessor();
        return accessor.getModifiers();
    }

    @Override
    public String getName() {
        return propertyDescriptor.getName();
    }

    @Override
    public String javaGet() {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter == null)
            // throw new UnsupportedOperationException("unreadable property.");
            return null;
        return getter.getName() + "()";
    }

    @Override
    public String javaSet(String javaContent) {
        Method setter = propertyDescriptor.getWriteMethod();
        if (setter == null)
            // throw new UnsupportedOperationException("unwritable property.");
            return null;
        return setter.getName() + "(" + javaContent + ")";
    }

    @Override
    public String toString() {
        return String.format("property %s::%s", getType().getName(), getName());
    }

}
