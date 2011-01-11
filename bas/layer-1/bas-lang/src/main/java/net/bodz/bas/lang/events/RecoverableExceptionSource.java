package net.bodz.bas.lang.events;

import net.bodz.bas.meta.oop.EventSource;

public interface RecoverableExceptionSource
        extends EventSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
