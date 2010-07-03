package net.bodz.bas.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.objects.ArrayJoinMessage;
import net.bodz.bas.log.objects.ILogEntry;
import net.bodz.bas.log.objects.IMessage;
import net.bodz.bas.log.objects.LogEntry;
import net.bodz.bas.log.objects.StringFormatMessage;
import net.bodz.bas.sio.AbstractIndentedCharOut;

public abstract class AbstractLogSink
        extends AbstractIndentedCharOut
        implements ILogSink {

// private Object source;

    private int verboseLevel;
    protected boolean enabled;

    private List<Object> prependMessageBuffer;
    private List<Throwable> exceptionBuffer;

    public AbstractLogSink() {
        prependMessageBuffer = new ArrayList<Object>();
        exceptionBuffer = new ArrayList<Throwable>();
        setVerboseLevel(LEVEL_DEFAULT);
    }

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

    @Override
    public void setVerboseLevel(int level) {
        this.verboseLevel = level;
        int configuredLevel = getConfiguredVerboseLevel();
        enabled = verboseLevel >= configuredLevel;
    }

    protected abstract int getConfiguredVerboseLevel();

    /**
     * Invoked only if verbose-level is greater than configured level.
     */
    public abstract void put(ILogEntry event);

    protected void put(Object message) {
        // Object source = Caller.getCaller
        LogEntry entry = new LogEntry(null, message, null);
        put(entry);
    }

    protected void put(Object message, Throwable exception) {
        // Object source = Caller.getCaller
        LogEntry entry = new LogEntry(null, message, exception);
        put(entry);
    }

    @Override
    public void p(Object message) {
        if (enabled) {
            if (!prependMessageBuffer.isEmpty()) {
                prependMessageBuffer.add(message);
                Object[] all = prependMessageBuffer.toArray();
                prependMessageBuffer.clear();
                message = new ArrayJoinMessage(all);
            }
            put(message);
        } else
            prependMessageBuffer.clear();
    }

    @Override
    public void p(ILogEntry event) {
        flush();
        put(event);
    }

    @Override
    public void p(Object... messagePieces) {
        IMessage message = new ArrayJoinMessage(messagePieces);
        p(message);
    }

    @Override
    public void p(Throwable exception, Object message) {
        flush();
        if (enabled)
            put(message, exception);
    }

    @Override
    public void p_(Object message) {
        prependMessageBuffer.add(message);
    }

    @Override
    public void p_(Object... messagePieces) {
        IMessage message = new ArrayJoinMessage(messagePieces);
        p_(message);
    }

    @Override
    public void p_(Throwable exception) {
        put(null, exception);
    }

    @Override
    public void p_(Throwable exception, Object message) {
    }

    @Override
    public void p_(Throwable exception, Object... messagePieces) {
        IMessage message = new ArrayJoinMessage(messagePieces);
        put(message, exception);
    }

    @Override
    public void f(String format, Object... args) {
        IMessage message = new StringFormatMessage(format, args);
        p(message);
    }

    @Override
    public void f(Throwable exception, String format, Object... args) {
        IMessage message = new StringFormatMessage(format, args);
        p(exception, message);
    }

    @Override
    protected void _flush(boolean strict)
            throws IOException {
        if (enabled) {
            if (!prependMessageBuffer.isEmpty()) {
                Object[] array = prependMessageBuffer.toArray();
                prependMessageBuffer.clear();
                IMessage message = new ArrayJoinMessage(array);
                put(message);
            }
        } else
            prependMessageBuffer.clear();
    }

}
