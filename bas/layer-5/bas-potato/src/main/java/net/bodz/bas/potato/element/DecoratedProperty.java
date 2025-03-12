package net.bodz.bas.potato.element;

import java.lang.reflect.Type;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.event.IPropertyChangeListener;

public class DecoratedProperty
        extends DecoratedPotatoElement
        implements
            IProperty {

    private static final long serialVersionUID = 1L;

    public DecoratedProperty(IProperty _orig) {
        super(_orig);
    }

    @NotNull
    @Override
    public IProperty getWrapped() {
        return (IProperty) _orig; // super.getWrapped();
    }

    @Override
    public IProperty getSuperProperty() {
        return getWrapped().getSuperProperty();
    }

    @Override
    public Class<?> getPropertyClass() {
        return getWrapped().getPropertyClass();
    }

    @Override
    public Type getPropertyGenericType() {
        return getWrapped().getPropertyGenericType();
    }

    @Override
    public IType getPropertyType() {
        return getWrapped().getPropertyType();
    }

    @Override
    public boolean isReadable() {
        return getWrapped().isReadable();
    }

    @Override
    public boolean isWritable() {
        return getWrapped().isWritable();
    }

    @Override
    public Object getValue(Object instance)
            throws ReflectiveOperationException {
        return getWrapped().getValue(instance);
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        getWrapped().setValue(instance, value);
    }

    @Override
    public boolean isPropertyChangeSource() {
        return getWrapped().isPropertyChangeSource();
    }

    @Override
    public void addPropertyChangeListener(Object instance, IPropertyChangeListener listener) {
        getWrapped().addPropertyChangeListener(instance, listener);
    }

    @Override
    public void addPropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener) {
        getWrapped().addPropertyChangeListener(instance, propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(Object instance, IPropertyChangeListener listener) {
        getWrapped().removePropertyChangeListener(instance, listener);
    }

    @Override
    public void removePropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener) {
        getWrapped().removePropertyChangeListener(instance, propertyName, listener);
    }

}
