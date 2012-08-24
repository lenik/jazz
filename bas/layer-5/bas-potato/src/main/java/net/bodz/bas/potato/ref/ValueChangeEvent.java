package net.bodz.bas.potato.ref;

import java.util.EventObject;

public class ValueChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    private final Object oldValue;
    private Object newValue;
    private boolean vetoable;

    public ValueChangeEvent(Object source, Object oldValue, Object newValue) {
        super(source);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public ValueChangeEvent(Object source, Object oldValue, Object newValue, boolean vetoable) {
        this(source, oldValue, newValue);
        this.vetoable = vetoable;
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

    public void setVetoable(boolean vetoable) {
        this.vetoable = vetoable;
    }

}
