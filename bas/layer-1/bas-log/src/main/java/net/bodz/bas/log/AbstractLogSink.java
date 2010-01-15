package net.bodz.bas.log;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.objects.DelayedConcat;
import net.bodz.bas.log.objects.DelayedFormat;
import net.bodz.bas.log.objects.ILogEvent;
import net.bodz.bas.log.objects.WithThrown;

public abstract class AbstractLogSink
        implements ILogSink {

    private int verboseLevel;
    protected boolean enabled;

    private List<Object> buffer;

    public AbstractLogSink() {
        buffer = new ArrayList<Object>();
        setVerboseLevel(0);
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
        if (exception == null)
            put(message);
        else
            put(message, exception);
    }

    /**
     * Invoked only if verbose-level is greater than configured level.
     */
    public abstract void put(Object message);

    /**
     * Invoked only if verbose-level is greater than configured level.
     */
    public void put(Object message, Throwable t) {
        put(new WithThrown(message, t).toString());
    }

    @Override
    public void p(Object message) {
        if (enabled) {
            if (!buffer.isEmpty()) {
                buffer.add(message);
                Object[] array = buffer.toArray();
                buffer.clear();
                DelayedConcat concat = new DelayedConcat(array);
                message = concat;
            }
            put(message);
        } else
            buffer.clear();
    }

    @Override
    public void p(ILogEvent event) {
        p_();
        put(event);
    }

    @Override
    public void p_() {
        if (enabled) {
            if (!buffer.isEmpty()) {
                Object[] array = buffer.toArray();
                buffer.clear();
                DelayedConcat concat = new DelayedConcat(array);
                put(concat);
            }
        } else
            buffer.clear();
    }

    @Override
    public void p(Object... concatMessages) {
        DelayedConcat concat = new DelayedConcat(concatMessages);
        p(concat);
    }

    @Override
    public void p(Object message, Throwable t) {
        p_();
        if (enabled)
            put(message, t);
    }

    @Override
    public void p_(Object message) {
        buffer.add(message);
    }

    @Override
    public void p_(Object... concatMessages) {
        DelayedConcat concat = new DelayedConcat(concatMessages);
        p_(concat);
    }

    @Override
    public void f(String format, Object... args) {
        DelayedFormat delayedFormat = new DelayedFormat(format, args);
        p(delayedFormat);
    }

}
