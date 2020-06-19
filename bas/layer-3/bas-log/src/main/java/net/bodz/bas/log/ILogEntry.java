package net.bodz.bas.log;

public interface ILogEntry {

    LogLevel getLevel();

    int getDelta();

    long getTime();

    /**
     * The object on which the Event initially occurred.
     *
     * @return <code>null</code> If source isn't mentioned.
     */
    Object getSource();

    /**
     * The message object
     *
     * @return Can be <code>null</code> if no message is given.
     */
    Object getMessage();

    /**
     * Get exception if there is any.
     *
     * @return <code>null</code> if no exception is available.
     */
    Throwable getException();

}
