package net.bodz.bas.commons.exception;

public interface ExceptionEventSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
