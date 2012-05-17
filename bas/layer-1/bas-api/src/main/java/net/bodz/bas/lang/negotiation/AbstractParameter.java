package net.bodz.bas.lang.negotiation;

import java.io.Serializable;

import net.bodz.bas.lang.negotiation.INegotiation.IParameter;

public abstract class AbstractParameter
        implements IParameter, Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final Object value;

    public AbstractParameter(String id, Object value) {
        if (id == null)
            throw new NullPointerException("id");
        this.id = id;
        this.value = value;
    }

    public AbstractParameter(Class<?> type, Object value) {
        this(type.getName(), value);
    }

    public AbstractParameter(Object typedValue) {
        this(typedValue.getClass(), typedValue);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @SuppressWarnings("unchecked")
    public <T> T value() {
        return (T) value;
    }

    @Override
    public boolean is(String id) {
        return this.id.equals(id);
    }

    @Override
    public boolean is(Class<?> type) {
        return is(type.getName());
    }

    @Override
    public boolean is(Class<?> type, boolean use)
            throws MandatoryException {
        return is(type.getName(), use);
    }

    @Override
    public boolean is(String id, boolean use)
            throws MandatoryException {
        if (!this.id.equals(id))
            return false;
        if (isImportant() && !use)
            throw new MandatoryException(this);
        return true;
    }

    @Override
    public void ignore()
            throws MandatoryException {
        if (isImportant())
            throw new MandatoryException(this);
    }

    @Override
    public <T> T cast(Class<T> type) {
        if (is(type.getName()))
            return type.cast(value);
        else
            return null;
    }

    @Override
    public <T> T cast(Class<T> type, boolean use)
            throws MandatoryException {
        if (is(type.getName(), use))
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
        if (!(obj instanceof AbstractParameter))
            return false;
        AbstractParameter that = (AbstractParameter) obj;
        if (!id.equals(that.id))
            return false;
        if (value != that.value)
            if (value == null || that.value == null || !value.equals(that.value))
                return false;
        return true;
    }

    @Override
    public String toString() {
        return (isImportant() ? '+' : '-') + id + "=" + value;
    }

}
