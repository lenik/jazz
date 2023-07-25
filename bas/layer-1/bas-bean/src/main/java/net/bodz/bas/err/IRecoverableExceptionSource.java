package net.bodz.bas.err;

import net.bodz.bas.meta.decl.EventSource;

public interface IRecoverableExceptionSource
        extends EventSource {

    void addExceptionListener(IRecoverableExceptionListener listener);

    void removeExceptionListener(IRecoverableExceptionListener listener);

}
