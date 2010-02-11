package net.bodz.bas.lang.events;

import java.beans.ExceptionListener;

import net.bodz.bas.hint.EventSource;

public interface ExceptionSource
        extends EventSource {

    void addExceptionListener(ExceptionListener listener);

    void removeExceptionListener(ExceptionListener listener);

}
