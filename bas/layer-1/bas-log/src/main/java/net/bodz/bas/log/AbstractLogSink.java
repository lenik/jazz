package net.bodz.bas.log;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.objects.DelayedConcat;
import net.bodz.bas.log.objects.DelayedFormat;
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
    public abstract void put(Object obj);

    public void put(Object obj, Throwable t) {
        put(new WithThrown(obj, t));
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
    public void p(Object obj) {
        if (enabled) {
            if (!buffer.isEmpty()) {
                buffer.add(obj);
                Object[] array = buffer.toArray();
                buffer.clear();
                DelayedConcat concat = new DelayedConcat(array);
                obj = concat;
            }
            put(obj);
        } else
            buffer.clear();
    }

    @Override
    public void p(Object... concatObjs) {
        DelayedConcat concat = new DelayedConcat(concatObjs);
        p(concat);
    }

    @Override
    public void p(Object obj, Throwable t) {
        p_();
        if (enabled)
            put(obj, t);
    }

    @Override
    public void p_(Object obj) {
        buffer.add(obj);
    }

    @Override
    public void p_(Object... concatObjs) {
        DelayedConcat concat = new DelayedConcat(concatObjs);
        p_(concat);
    }

    @Override
    public void f(String format, Object... args) {
        DelayedFormat delayedFormat = new DelayedFormat(format, args);
        p(delayedFormat);
    }

}
