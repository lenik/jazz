package net.bodz.bas.lang;

import java.beans.ExceptionListener;

public interface RecoverableExceptionListener extends ExceptionListener {

    @Override
    void exceptionThrown(Exception ex);

    void recoverException(RecoverableExceptionEvent e);

}
