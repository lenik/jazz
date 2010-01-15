package net.bodz.bas.log.objects;

public interface ILogEvent {

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
     * @return <code>null</code> If nothing is thrown.
     */
    Throwable getException();

}
