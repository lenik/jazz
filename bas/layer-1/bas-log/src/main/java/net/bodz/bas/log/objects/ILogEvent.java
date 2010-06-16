package net.bodz.bas.log.objects;

import java.util.Collection;

public interface ILogEvent {

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
     * @return non-<code>null</code> value.
     */
    Object getMessage();

    /**
     * @return number of exceptions.
     */
    int getExceptionCount();

    /**
     * Get the first exception in this event.
     * 
     * @return <code>null</code> if no exception is available. If there is more than one exception,
     *         only the first is returned.
     */
    Throwable getException();

    /**
     * Get all logged exceptions in this event.
     * 
     * @return Empty collection if no exception is available. Or an unmodifiable collection contains
     *         all the exceptions.
     */
    Collection<Throwable> getExceptions();

}
