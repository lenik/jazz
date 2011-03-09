package net.bodz.bas.lang.negotiation;

import java.io.Serializable;

public class NegotiationParameter
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

    @SuppressWarnings("unchecked")
    public <T> T value() {
        return (T) value;
    }

    /**
     * A mandatory parameter must be processed by the callee..
     * 
     * @return <code>true</code> If this parameter is mandatory.
     */
    public boolean isMandatory() {
        return mandatory;
    }

    public boolean accept(Class<?> forType)
            throws MandatoryException {
        return accept(forType.getName());
    }

    public boolean accept(String forId)
            throws MandatoryException {
        return this.id.equals(forId);
    }

    /**
     * The consumer iterates over this parameter, checked or not.
     * 
     * @param forType
     *            The specific parameter class, may equal or not to this parameter.
     * @param isChecked
     *            <code>true</code> if the consumer wants to use the parameter, <code>false</code>
     *            if the consumer wants to ignore the parameter.
     * @return <code>true</code> If <code>forType</code> matches this parameter.
     * @throws MandatoryException
     *             If the consumer wants to ignore this parameter, but this parameter is mandatory.
     */
    public boolean accept(Class<?> forType, boolean isChecked)
            throws MandatoryException {
        return accept(forType.getName(), isChecked);
    }

    /**
     * The consumer iterates over this parameter, checked or not.
     * 
     * @param forId
     *            The specific parameter id, may equal or not to this parameter.
     * @param isChecked
     *            <code>true</code> if the consumer wants to use the parameter, <code>false</code>
     *            if the consumer wants to ignore the parameter.
     * @return <code>true</code> If <code>forId</code> matches this parameter.
     * @throws MandatoryException
     *             If the consumer wants to ignore this parameter, but this parameter is mandatory.
     */
    public boolean accept(String forId, boolean isChecked)
            throws MandatoryException {
        if (!this.id.equals(forId))
            return false;
        if (isMandatory() && !isChecked)
            throw new MandatoryException(this);
        return true;
    }

    /**
     * The consumer want to ignore this parameter.
     */
    public void bypass()
            throws MandatoryException {
        if (isMandatory())
            throw new MandatoryException(this);
    }

    public <T> T utilize(Class<T> type)
            throws MandatoryException {
        return utilize(type, true);
    }

    public <T> T utilize(Class<T> type, boolean isChecked)
            throws MandatoryException {
        if (accept(type.getName(), isChecked))
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
