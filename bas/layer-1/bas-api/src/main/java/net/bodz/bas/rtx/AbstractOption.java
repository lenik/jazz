package net.bodz.bas.rtx;

import java.io.Serializable;

public abstract class AbstractOption
        implements IOption, Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final Object value;

    public AbstractOption(String id, Object value) {
        if (id == null)
            throw new NullPointerException("id");
        this.id = id;
        this.value = value;
    }

    public AbstractOption(Class<?> clazz, Object value) {
        this(clazz.getName(), value);
    }

    public AbstractOption(Object typedValue) {
        this(typedValue.getClass(), typedValue);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        return (T) value;
    }

    public boolean idEquals(String id) {
        return this.id.equals(id);
    }

    public boolean idEquals(Class<?> type) {
        return idEquals(type.getName());
    }

    public <T> T typeFilter(Class<T> type) {
        if (idEquals(type.getName()))
            return type.cast(value);
        else
            return null;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int hash = id.hashCode();
        hash = hash * prime + (value == null ? 0 : value.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractOption))
            return false;
        AbstractOption that = (AbstractOption) obj;
        if (!id.equals(that.id))
            return false;
        if (value != that.value)
            if (value == null || that.value == null || !value.equals(that.value))
                return false;
        return true;
    }

    @Override
    public String toString() {
        return id + "=" + value;
    }

}
