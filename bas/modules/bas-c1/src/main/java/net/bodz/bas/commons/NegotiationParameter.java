package net.bodz.bas.commons;

import java.io.Serializable;

import net.bodz.bas.commons.exceptions.UnsupportedNegotiationException;

public final class NegotiationParameter
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final Object value;
    private final boolean mandatory;

    public NegotiationParameter(String id, Object value, boolean mandatory) {
        this.id = id;
        this.value = value;
        this.mandatory = mandatory;
    }

    public NegotiationParameter(String id, Object value) {
        this(id, value, false);
    }

    public NegotiationParameter(Class<?> type, Object value, boolean mandatory) {
        this(type.getName(), value, mandatory);
    }

    public NegotiationParameter(Class<?> type, Object value) {
        this(type, value, false);
    }

    public NegotiationParameter(Object typedValue, boolean mandatory) {
        this(typedValue.getClass(), typedValue, mandatory);
    }

    public NegotiationParameter(Object typedValue) {
        this(typedValue.getClass(), typedValue, false);
    }

    public String getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public <T> T getValue(Class<T> type) {
        return type.cast(value);
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public boolean isWanted(Class<?> type)
            throws UnsupportedNegotiationException {
        return isWanted(type.getName(), true);
    }

    public boolean isWanted(String id)
            throws UnsupportedNegotiationException {
        return isWanted(id, true);
    }

    public boolean isWanted(Class<?> type, boolean isSupported)
            throws UnsupportedNegotiationException {
        return isWanted(type.getName(), isSupported);
    }

    public boolean isWanted(String id, boolean isSupported)
            throws UnsupportedNegotiationException {
        if (!this.id.equals(id))
            return false;
        if (isSupported)
            return true;
        if (isMandatory())
            throw new UnsupportedNegotiationException(this);
        else
            return false;
    }

    public <T> T utilize(Class<T> type)
            throws UnsupportedNegotiationException {
        return utilize(type, true);
    }

    public <T> T utilize(Class<T> type, boolean isSupported)
            throws UnsupportedNegotiationException {
        if (isWanted(type.getName(), isSupported))
            return type.cast(value);
        else
            return null;
    }

    @Override
    public int hashCode() {
        int hash = id.hashCode();
        if (value != null)
            hash += value.hashCode();
        if (mandatory)
            hash += 0x8d4f66e9;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NegotiationParameter)
            return false;
        NegotiationParameter that = (NegotiationParameter) obj;
        if (mandatory != that.mandatory)
            return false;
        if (!id.equals(that.id))
            return false;
        if (value != that.value)
            if (value == null || that.value == null || !value.equals(that.value))
                return false;
        return true;
    }

    @Override
    public String toString() {
        return (mandatory ? "*" : "") + id + "=" + value;
    }

}
