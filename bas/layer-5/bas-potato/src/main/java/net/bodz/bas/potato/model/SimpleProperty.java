package net.bodz.bas.potato.model;

import net.bodz.bas.t.event.IPropertyChangeListener;

public abstract class SimpleProperty
        extends SimplePotatoElement
        implements IProperty {

    private static final long serialVersionUID = 1L;

    Class<?> propertyType;
    boolean readable;
    boolean writable;
    boolean propertyChangeSource;

    @Override
    public Class<?> getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Class<?> propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    @Override
    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    @Override
    public boolean isPropertyChangeSource() {
        return propertyChangeSource;
    }

    public void setPropertyChangeSource(boolean propertyChangeSource) {
        this.propertyChangeSource = propertyChangeSource;
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

}
