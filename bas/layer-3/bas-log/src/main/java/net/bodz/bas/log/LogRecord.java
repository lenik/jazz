package net.bodz.bas.log;

public class LogRecord {

    public long time;
    public LogLevel level;
    public int delta;
    public Object message;
    public Throwable exception;

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

}
