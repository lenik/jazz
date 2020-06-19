package net.bodz.bas.log;

public class LogRecord
        implements ILogEntry {

    public long time = System.currentTimeMillis();
    public LogLevel level = LogLevel.INFO;
    public int delta;
    public Object source;
    public Object message;
    public Throwable exception;

    public LogRecord(Object message) {
        this.message = message;
    }

    public LogRecord(Object message, Throwable exception) {
        this.message = message;
        this.exception = exception;
    }

    public LogRecord(LogLevel level, int delta, Object message) {
        this.level = level;
        this.delta = delta;
        this.message = message;
    }

    public LogRecord(LogLevel level, int delta, Object message, Throwable exception) {
        this.level = level;
        this.delta = delta;
        this.message = message;
        this.exception = exception;
    }

    @Override
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    @Override
    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        String messageText = null;
        StringBuilder buf;
        if (message != null) {
            messageText = message.toString();
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
