package net.bodz.bas.http.thread;

public interface IAsyncCallback {

    void onReturn(Object retval);

    void onException(Throwable exception);

    void onInterrupt(InterruptedException e);

}
