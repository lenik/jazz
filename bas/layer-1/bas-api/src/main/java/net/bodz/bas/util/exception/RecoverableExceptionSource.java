package net.bodz.bas.util.exception;

public interface RecoverableExceptionSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
