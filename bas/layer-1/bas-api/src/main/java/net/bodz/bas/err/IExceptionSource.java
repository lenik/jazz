package net.bodz.bas.err;

import java.beans.ExceptionListener;

import net.bodz.bas.meta.decl.EventSource;

public interface IExceptionSource
        extends EventSource {

    void addExceptionListener(ExceptionListener listener);

    void removeExceptionListener(ExceptionListener listener);

}
