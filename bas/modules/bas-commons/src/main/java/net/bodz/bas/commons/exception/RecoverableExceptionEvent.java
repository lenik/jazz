package net.bodz.bas.commons.exception;

import java.util.EventObject;

public class RecoverableExceptionEvent extends EventObject {

    private static final long serialVersionUID = 6558508128150210701L;

    private boolean recovered;
    private Exception exception;

    public RecoverableExceptionEvent(Object source, Exception exception) {
        super(source);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean isRecovered() {
        return recovered;
    }

    public void setRecovered(boolean recovered) {
        this.recovered = recovered;
    }

}
