package net.bodz.bas.log.objects;

import net.bodz.bas.log.ILogSink;

public class LogEntry
        implements ILogEntry {

    private final Object source;
    private final Object messageObject;
    private final Throwable exception;

    public LogEntry(Object source, Object messageObject) {
        this(source, messageObject, null);
    }

    public LogEntry(Object source, Object messageObject, Throwable exception) {
        this.source = source;
        this.messageObject = messageObject;
        this.exception = exception;
    }

    @Override
    public int getVerboseLevel() {
        return ILogSink.LEVEL_DEFAULT;
    }

    @Override
    public Object getSource() {
        return source;
    }

    @Override
    public Object getMessage() {
        return messageObject;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public String toString() {
        StringBuffer buf;
        if (messageObject != null) {
            String messageText = messageObject.toString();
            buf = new StringBuffer(messageText.length() + 100);
            buf.append(messageText);
        } else
            buf = new StringBuffer(100);
        if (exception != null) {
            buf.append(" (Exception ");
            buf.append(exception.getClass().getName());
            buf.append(": ");
            buf.append(exception.getLocalizedMessage());
            buf.append(")");
        }
        return buf.toString();
    }

}
