package net.bodz.bas.commons.exception;

import java.beans.ExceptionListener;

public interface RecoverableExceptionListener extends ExceptionListener {

    @Override
    void exceptionThrown(Exception ex);

    void recoverException(RecoverableExceptionEvent e);

}
