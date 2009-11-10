package net.bodz.bas.util;

import java.util.EventObject;

public class StatusChangeEvent extends EventObject {

    private static final long serialVersionUID = -4086144384777447184L;

    private Object status;

    public StatusChangeEvent(Object source, Object status) {
        super(source);
        if (status == null)
            throw new NullPointerException("status"); //$NON-NLS-1$
        this.status = status;
    }

    public Object getStatus() {
        return status;
    }

}
