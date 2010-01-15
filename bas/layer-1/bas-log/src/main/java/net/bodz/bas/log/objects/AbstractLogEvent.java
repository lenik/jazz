package net.bodz.bas.log.objects;

import net.bodz.bas.log.ILogSink;

public abstract class AbstractLogEvent
        implements ILogEvent {

    @Override
    public int getVerboseLevel() {
        return ILogSink.LEVEL_DEFAULT;
    }

    @Override
    public Object getSource() {
        return null;
    }

    @Override
    public Object getMessage() {
        return this;
    }

    @Override
    public Throwable getException() {
        return null;
    }

    @Override
    public abstract String toString();

}
