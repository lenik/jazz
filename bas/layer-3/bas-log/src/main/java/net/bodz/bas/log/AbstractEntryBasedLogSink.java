package net.bodz.bas.log;

import org.joda.time.DateTime;

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

    @Override
    public final void logMessage(Object message) {
        // Object source = Caller.getCaller
        LogEntry entry = new LogEntry(new DateTime(), null, message, null);
        log(entry);
    }

    @Override
    public final void logException(Object message, Throwable exception) {
        // Object source = Caller.getCaller
        LogEntry entry = new LogEntry(new DateTime(), null, message, exception);
        log(entry);
    }

}
