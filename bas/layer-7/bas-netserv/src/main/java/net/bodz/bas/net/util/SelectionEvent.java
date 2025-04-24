package net.bodz.bas.net.util;

import java.util.EventObject;

public class SelectionEvent
        extends EventObject {

    Object value;
    boolean errored;
    Throwable exception;

    public SelectionEvent(Object source) {
        super(source);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isErrored() {
        return errored;
    }

    public void setErrored(boolean errored) {
        this.errored = errored;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (errored) {
            buf.append("error");
            if (exception != null)
                buf.append(": ").append(exception.getMessage());
        } else {
            buf.append("value: ").append(value);
        }
        return buf.toString();
    }

}