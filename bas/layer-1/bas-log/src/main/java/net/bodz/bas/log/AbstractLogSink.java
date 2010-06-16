package net.bodz.bas.log;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.objects.ArrayJoinMessage;
import net.bodz.bas.log.objects.ILogEvent;
import net.bodz.bas.log.objects.IMessage;
import net.bodz.bas.log.objects.StringFormatMessage;
import net.bodz.bas.sio.AbstractIndentedCharOut;

public abstract class AbstractLogSink
        extends AbstractIndentedCharOut
        implements ILogSink {

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
    public void put(ILogEvent event) {
        Object message = event.getMessage();
        Throwable exception = event.getException();
        switch (event.getExceptionCount()) {
        case 0:
            put(message);
        case 1:
            put(message, event.getException());
        default:
            put(message, event.getExceptions());
        }
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
    public void p(ILogEvent event) {
        p_();
        put(event);
    }

    @Override
    public void p(Object... messagePieces) {
        IMessage message = new ArrayJoinMessage(messagePieces);
        p(message);
    }

    @Override
    public void p(Throwable exception, Object message) {
        p_();
        if (enabled)
            put(message, exception);
    }

    @Override
    public void flush() {
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
    }

    @Override
    public void p_(Throwable exception, Object message) {
    }

    @Override
    public void p_(Throwable exception, Object... messagePieces) {
    }

    @Override
    public void f(String format, Object... args) {
        IMessage message = new StringFormatMessage(format, args);
        p(message);
    }

    @Override
    public void f(Throwable exception, String format, Object... args) {
    }

}
