package net.bodz.bas.err;

import java.beans.ExceptionListener;

public interface RecoverableExceptionListener
        extends ExceptionListener {

    @Override
    void exceptionThrown(Exception ex);

    void recoverException(RecoverableExceptionEvent e);

}
