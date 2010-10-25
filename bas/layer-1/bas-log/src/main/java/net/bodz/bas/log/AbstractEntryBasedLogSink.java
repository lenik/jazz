package net.bodz.bas.log;

/**
 * {@link ILogEntry} is used as base message object.
 */
public abstract class AbstractEntryBasedLogSink
        extends AbstractLogSink {

    public AbstractEntryBasedLogSink() {
        super();
    }

    @Override
    public abstract void log(ILogEntry entry);

    protected final void logMessage(Object message) {
        // Object source = Caller.getCaller
        LogEntry entry = new LogEntry(null, message, null);
        log(entry);
    }

    protected final void logException(Object message, Throwable exception) {
        // Object source = Caller.getCaller
        LogEntry entry = new LogEntry(null, message, exception);
        log(entry);
    }

}
