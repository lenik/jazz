package net.bodz.bas.bean.api;

public interface IExceptionListener {

    /*
     * * This method is called when a recoverable exception has been caught.
     *
     * @param e The exception that was caught.
     *
     */
    void exceptionThrown(Exception e);

}