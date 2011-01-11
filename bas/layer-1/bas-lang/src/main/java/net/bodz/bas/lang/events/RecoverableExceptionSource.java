package net.bodz.bas.lang.events;

public interface RecoverableExceptionSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
