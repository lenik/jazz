package net.bodz.bas.log;

import org.joda.time.DateTime;

public interface ILogEntry {

    DateTime getTime();

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
