package net.bodz.bas.lang.events;

import net.bodz.bas.hint.EventSource;

public interface RecoverableExceptionSource
        extends EventSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
