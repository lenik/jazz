package net.bodz.bas.potato.element;

import java.lang.reflect.Type;

import net.bodz.bas.t.event.IPropertyChangeListener;

public abstract class AbstractMutableProperty
        extends MutablePotatoElement
        implements
            IProperty {

    private Class<?> propertyType = Object.class;
    private Type propertyGenericType = Object.class;
    private boolean readable = true;
    private boolean writable = true;
    private boolean propertyChangeSource;

    /** ⇱ Implementation Of {@link IProperty}. */
    /* _____________________________ */static section.iface __PROPERTY__;

    @Override
    public Class<?> getPropertyClass() {
        return propertyType;
    }

    @Override
    public Type getPropertyGenericType() {
        return propertyGenericType;
    }

    @Override
    public boolean isReadable() {
        return readable;
    }

    @Override
    public boolean isWritable() {
        return writable;
    }

    @Override
    public boolean isPropertyChangeSource() {
        return propertyChangeSource;
    }

    @Override
    public void addPropertyChangeListener(Object instance, IPropertyChangeListener listener) {
    }

    @Override
    public void addPropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(Object instance, IPropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener) {
    }

    /** ⇱ Implementation Of {@link IMutableProperty}. */
    /* _____________________________ */static section.iface __MUTABLE__;

    public void setPropertyType(Class<?> propertyType) {
        if (propertyType == null)
            throw new NullPointerException("propertyType");
        this.propertyType = propertyType;
    }

    public void setPropertyGenericType(Type propertyGenericType) {
        if (propertyGenericType == null)
            throw new NullPointerException("propertyGenericType");
        this.propertyGenericType = propertyGenericType;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public void setPropertyChangeSource(boolean propertyChangeSource) {
        this.propertyChangeSource = propertyChangeSource;
    }

}
