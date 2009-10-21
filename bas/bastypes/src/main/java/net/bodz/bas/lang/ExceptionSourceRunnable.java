package net.bodz.bas.lang;

public interface ExceptionSourceRunnable extends Runnable {

    void addExceptionListener(RecoverableExceptionListener listener);

    void removeExceptionListener(RecoverableExceptionListener listener);

}
