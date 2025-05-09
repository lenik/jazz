package net.bodz.bas.codegen.java.member;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.provider.bean.BeanProperty;
import net.bodz.bas.potato.provider.reflect.FieldProperty;

public class PotatoPropertyMember
        implements
            IMember {

    final IProperty property;

    static final int FIELD = 0;
    static final int BEAN = 1;
    static final int OTHER = 2;
    final int kind;

    public PotatoPropertyMember(IProperty property) {
        if (property == null)
            throw new NullPointerException("property");
        this.property = property;
        if (property instanceof FieldProperty)
            kind = FIELD;
        else if (property instanceof BeanProperty)
            kind = BEAN;
        else
            kind = OTHER;
    }

    @Override
    public AnnotatedElement toAnnotatedElement() {
        return property;
    }

    @Override
    public Class<?> getType() {
        return property.getPropertyClass();
    }

    @Override
    public int getModifiers() {
        return property.getModifiers();
    }

    @Override
    public String getName() {
        return property.getName();
    }

    @Override
    public String javaGet() {
        switch (kind) {
        case FIELD:
            return property.getName();

        case BEAN:
            BeanProperty beanProperty = (BeanProperty) property;
            Method getter = beanProperty.getPropertyDescriptor().getReadMethod();
            if (getter == null)
                // throw new UnsupportedOperationException("unreadable property.");
                return null;
            return getter.getName() + "()";

        case OTHER:
        default:
            // throw new UnsupportedOperationException();
            return null;
        }
    }

    @Override
    public String javaSet(String javaContent) {
        switch (kind) {
        case FIELD:
            return property.getName() + " = " + javaContent;

        case BEAN:
            BeanProperty beanProperty = (BeanProperty) property;
            Method setter = beanProperty.getPropertyDescriptor().getWriteMethod();
            if (setter == null)
                // throw new UnsupportedOperationException("unwritable property.");
                return null;
            return setter.getName() + "(" + javaContent + ")";

        case OTHER:
        default:
            // throw new UnsupportedOperationException();
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("prop* %s::%s", getType().getName(), getName());
    }

}
