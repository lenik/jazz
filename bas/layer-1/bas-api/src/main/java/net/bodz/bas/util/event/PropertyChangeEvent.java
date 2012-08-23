package net.bodz.bas.util.event;

import java.util.EventObject;

public class PropertyChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    final String propertyName;
    final Object oldValue;
    Object newValue;
    boolean vetoable;

    public PropertyChangeEvent(Object source, String propertyName, Object oldValue, Object newValue) {
        super(source);
        this.propertyName = propertyName;
        this.newValue = newValue;
        this.oldValue = oldValue;
    }

    public PropertyChangeEvent(Object source, String propertyName, Object oldValue, Object newValue, boolean vetoable) {
        this(source, propertyName, oldValue, newValue);
        this.vetoable = vetoable;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public boolean isVetoable() {
        return vetoable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("[propertyName=").append(propertyName);
        appendTo(sb);
        sb.append("; oldValue=").append(oldValue);
        sb.append("; newValue=").append(newValue);
        sb.append("; source=").append(getSource());
        sb.append("]");
        return sb.toString();
    }

    protected void appendTo(StringBuilder sb) {
    }

}
