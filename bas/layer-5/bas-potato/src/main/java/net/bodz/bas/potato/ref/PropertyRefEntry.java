package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.traits.IProperty;

public class PropertyRefEntry<T>
        extends AbstractRefEntry<T> {

    private static final long serialVersionUID = 1L;

    Object instance;

    public PropertyRefEntry(Object instance, IProperty property) {
        super(property.getName(), property);
        this.instance = instance;
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
    public void set(T value) {
        try {
            property.setValue(instance, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
