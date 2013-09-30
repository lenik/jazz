package net.bodz.bas.err;

public interface RecoverableExceptionSource {

    void addExceptionListener(IRecoverableExceptionListener listener);

    void removeExceptionListener(IRecoverableExceptionListener listener);

}
