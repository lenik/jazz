package net.bodz.bas.err;

import net.bodz.bas.meta.decl.EventSource;

import com.googlecode.openbeans.ExceptionListener;

public interface IExceptionSource
        extends EventSource {

    void addExceptionListener(ExceptionListener listener);

    void removeExceptionListener(ExceptionListener listener);

}
