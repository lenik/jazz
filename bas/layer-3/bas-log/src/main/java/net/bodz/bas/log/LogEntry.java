package net.bodz.bas.log;

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
        String messageText = null;
        StringBuilder buf;
        if (messageObject != null) {
            messageText = messageObject.toString();
            assert messageText != null;
            if (exception == null)
                return messageText;
            buf = new StringBuilder(messageText.length() + 100);
            buf.append(messageText);
        } else
            buf = new StringBuilder(100);

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