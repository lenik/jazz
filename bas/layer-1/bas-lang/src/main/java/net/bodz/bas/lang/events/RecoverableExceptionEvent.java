package net.bodz.bas.lang.events;

import java.util.EventObject;

import net.bodz.bas.lang.Nullables;

public class RecoverableExceptionEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RecoverableExceptionEvent))
            return false;
        RecoverableExceptionEvent o = (RecoverableExceptionEvent) obj;
        if (!Nullables.equals(exception, o.exception))
            return false;
        if (recovered != o.recovered)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        assert exception != null;
        int hash = 0x32942828;
        if (recovered)
            hash += 0xe25013c4;
        hash += exception.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return exception.toString();
    }

}
