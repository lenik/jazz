package net.bodz.bas.c.event;

import java.util.EventObject;

public class ValidationEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    Object target;

    public ValidationEvent(Object source, Object target) {
        super(source);
        this.target = target;
    }

    /**
     * The target object to be validated.
     */
    public Object getTarget() {
        return target;
    }

}
