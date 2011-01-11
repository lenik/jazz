package net.bodz.bas.exception;

import net.bodz.bas.lang.events.RecoverableExceptionListener;

public interface ExceptionEventSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
