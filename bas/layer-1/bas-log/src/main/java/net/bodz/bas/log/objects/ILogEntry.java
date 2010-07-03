package net.bodz.bas.log.objects;

public interface ILogEntry {

    /**
     * The verbose level of this this log event.
     */
    int getVerboseLevel();

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
