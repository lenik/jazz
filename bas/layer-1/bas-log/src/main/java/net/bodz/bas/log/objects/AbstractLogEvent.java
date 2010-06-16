package net.bodz.bas.log.objects;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.log.ILogSink;

public abstract class AbstractLogEvent
        implements ILogEvent {

    Object source;
    IMessage message;
    IExceptionBuffer exceptionBuffer;

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
    public Collection<Throwable> getExceptions() {
        return Collections.emptyList();
    }

    @Override
    public abstract String toString();

}
