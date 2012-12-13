package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.t.ref.AbstractRef;

public class PropertyRef<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    private final Object obj;
    private final IProperty property;

    public PropertyRef(Object obj, IProperty property) {
        if (property == null)
            throw new NullPointerException("property");

        this.obj = obj;
        this.property = property;
    }

    public Object getObject() {
        return obj;
    }

    public IProperty getProperty() {
        return property;
    }

    @Override
    public Class<? extends T> getValueType() {
        Class<? extends T> propertyType = (Class<? extends T>) property.getPropertyType();
        return propertyType;
    }

    @Override
    public T get() {
        try {
            Object value = property.getValue(obj);
            return getValueType().cast(value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void set(Object value) {
        try {
            property.setValue(obj, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
