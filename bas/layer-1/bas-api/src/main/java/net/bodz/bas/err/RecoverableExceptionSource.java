package net.bodz.bas.err;

public interface RecoverableExceptionSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
