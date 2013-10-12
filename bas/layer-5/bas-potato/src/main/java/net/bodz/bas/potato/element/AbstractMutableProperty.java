package net.bodz.bas.potato.element;

import net.bodz.bas.t.event.IPropertyChangeListener;

public abstract class AbstractMutableProperty
        extends MutablePotatoElement
        implements IProperty {

    private static final long serialVersionUID = 1L;

    private Class<?> propertyType;
    private boolean readable;
    private boolean writable;
    private boolean propertyChangeSource;

    /** ⇱ Implementation Of {@link IProperty}. */
    ;

    @Override
    public Class<?> getPropertyType() {
        return propertyType;
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
    ;

    public void setPropertyType(Class<?> propertyType) {
        this.propertyType = propertyType;
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
