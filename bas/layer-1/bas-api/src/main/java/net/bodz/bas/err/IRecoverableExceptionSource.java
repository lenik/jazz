package net.bodz.bas.err;

import net.bodz.bas.meta.decl.EventSource;

public interface IRecoverableExceptionSource
        extends EventSource {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
