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
    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        return (T) value;
    }

    @Override
    public void ignore()
            throws MandatoryException {
        if (isImportant())
            throw new MandatoryException(this);
    }

    public boolean idEquals(String id) {
        return this.id.equals(id);
    }

    public boolean idEquals(Class<?> type) {
        return idEquals(type.getName());
    }

    public boolean checkId(Class<?> type, boolean use)
            throws MandatoryException {
        return checkId(type.getName(), use);
    }

    public boolean checkId(String id, boolean use)
            throws MandatoryException {
        if (!this.id.equals(id))
            return false;
        if (isImportant() && !use)
            throw new MandatoryException(this);
        return true;
    }

    public <T> T typeFilter(Class<T> type) {
        if (idEquals(type.getName()))
            return type.cast(value);
        else
            return null;
    }

    public <T> T typeFilter(Class<T> type, boolean use)
            throws MandatoryException {
        if (checkId(type.getName(), use))
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
