package net.bodz.bas.potato.element;

import net.bodz.bas.t.event.IPropertyChangeListener;

public abstract class AbstractProperty
        extends AbstractPotatoElement
        implements IProperty {

    public AbstractProperty(Class<?> declaringType, String propertyName) {
        super(declaringType, propertyName);
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isWritable() {
        return true;
    }

    @Override
    public boolean isPropertyChangeSource() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(Object instance, IPropertyChangeListener listener) {
    }

    @Override
    public void addPropertyChangeListener(Object instance, String property, IPropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(Object instance, IPropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(Object instance, String property, IPropertyChangeListener listener) {
    }

}
