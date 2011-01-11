package net.bodz.bas.commons.exception;

import net.bodz.bas.meta.lang.events.RecoverableExceptionListener;

public interface ExceptionEventSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
