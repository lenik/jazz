package net.bodz.bas.err;

import net.bodz.bas.bean.api.IExceptionListener;

public interface IRecoverableExceptionListener
        extends IExceptionListener {

    @Override
    void exceptionThrown(Exception ex);

    void recoverException(RecoverableExceptionEvent e);

}
