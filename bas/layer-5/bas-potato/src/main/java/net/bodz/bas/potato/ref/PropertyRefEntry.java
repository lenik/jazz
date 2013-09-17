package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.rtx.QueryException;

public class PropertyRefEntry<T>
        extends AbstractRefEntry<T>
        implements IValueChangeSource {

    private static final long serialVersionUID = 1L;

    Object instance;
    IProperty property;

    public PropertyRefEntry(Object instance, IProperty property) {
        super(property);
        this.instance = instance;
        this.property = property;
    }

    @Override
    public Class<? extends T> getValueType() {
        return (Class<? extends T>) property.getPropertyType();
    }

    @Override
    public T get() {
        try {
            T value = (T) property.getValue(instance);
            return value;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void set(Object value) {
        try {
            property.setValue(instance, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        if (specificationType == IValueChangeSource.class) {
            if (property.isPropertyChangeSource())
                return specificationType.cast(this);
            else
                return null;
        }
        return super.query(specificationType);
    }

    @Override
    public void addValueChangeListener(IValueChangeListener listener) {
        ValueOfPropertyChangeListener _listener = new ValueOfPropertyChangeListener(listener);
        property.addPropertyChangeListener(instance, property.getName(), _listener);
    }

    @Override
    public void removeValueChangeListener(IValueChangeListener listener) {
        ValueOfPropertyChangeListener _listener = new ValueOfPropertyChangeListener(listener);
        property.removePropertyChangeListener(listener, property.getName(), _listener);
    }

}
