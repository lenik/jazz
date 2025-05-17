package net.bodz.bas.potato.ref;

import java.util.Map.Entry;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.PropertyReadException;
import net.bodz.bas.potato.element.PropertyWriteException;
import net.bodz.bas.t.ref.AbstractRef;

public class PropertyRef<T>
        extends AbstractRef<T>
        implements Entry<String, T> {

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
        @SuppressWarnings("unchecked")
        Class<? extends T> propertyType = (Class<? extends T>) property.getPropertyClass();
        return propertyType;
    }

    @Override
    public T get() {
        try {
            Object value = property.read(obj);
            return getValueType().cast(value);
        } catch (PropertyReadException e) {
            throw new RuntimeException("error reading property " + property + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void set(Object value) {
        try {
            property.write(obj, value);
        } catch (PropertyWriteException e) {
            throw new RuntimeException("error writing property " + property + ": " + e.getMessage(), e);
        }
    }

    /**
     * ⇱ Implementation Of {@link Entry}.
     */
    /* _____________________________ */static section.iface __ENTRY__;

    @Override
    public String getKey() {
        return property.getName();
    }

    @Override
    public T getValue() {
        return get();
    }

    @Override
    public T setValue(T value) {
        T old = get();
        set(value);
        return old;
    }

}
