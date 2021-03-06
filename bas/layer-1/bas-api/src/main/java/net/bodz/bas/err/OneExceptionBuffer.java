package net.bodz.bas.err;

public class OneExceptionBuffer
        implements IRecoverableExceptionListener {

    private Exception first;

    @Override
    public void exceptionThrown(Exception ex) {
        if (first == null)
            first = ex;
    }

    @Override
    public void recoverException(RecoverableExceptionEvent e) {
        if (first == null)
            first = e.getException();
    }

    public Exception getFirstException() {
        return first;
    }

    public boolean hasCaughtException() {
        return first != null;
    }

}
