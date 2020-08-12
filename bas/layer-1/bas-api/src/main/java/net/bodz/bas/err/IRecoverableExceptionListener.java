package net.bodz.bas.err;

import com.googlecode.openbeans.ExceptionListener;

public interface IRecoverableExceptionListener
        extends ExceptionListener {

    @Override
    void exceptionThrown(Exception ex);

    void recoverException(RecoverableExceptionEvent e);

}
