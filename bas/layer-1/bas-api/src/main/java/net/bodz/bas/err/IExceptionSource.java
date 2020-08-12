package net.bodz.bas.err;

import com.googlecode.openbeans.ExceptionListener;

import net.bodz.bas.meta.decl.EventSource;

public interface IExceptionSource
        extends EventSource {

    void addExceptionListener(ExceptionListener listener);

    void removeExceptionListener(ExceptionListener listener);

}
