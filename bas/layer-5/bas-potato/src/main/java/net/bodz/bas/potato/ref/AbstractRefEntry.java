package net.bodz.bas.potato.ref;

import java.util.Map;

import net.bodz.bas.lang.ref.AbstractRef;
import net.bodz.bas.potato.traits.IProperty;

public abstract class AbstractRefEntry<T>
        extends AbstractRef<T>
        implements IRefEntry<T>, Map.Entry<String, T> {

    private static final long serialVersionUID = 1L;

    private String name;
    protected final IProperty property;

    public AbstractRefEntry(String name, IProperty property) {
        this.name = name;
        this.property = property;
    }

    @Override
    public Class<? extends T> getValueType() {
        Class<? extends T> propertyType = (Class<? extends T>) property.getPropertyType();
        return propertyType;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IProperty getProperty() {
        return property;
    }

    // -o Map.Entry

    @Override
    public final String getKey() {
        return getName();
    }

    @Override
    public final T getValue() {
        return get();
    }

    @Override
    public final T setValue(T value) {
        T oldValue = get();
        set(value);
        return oldValue;
    }

}
