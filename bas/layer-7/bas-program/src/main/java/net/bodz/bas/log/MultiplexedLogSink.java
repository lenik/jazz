package net.bodz.bas.log;

import java.util.ArrayList;
import java.util.List;

public class MultiplexedLogSink
        extends AbstractLogSink {

    private List<ILogSink> sinks;

    public MultiplexedLogSink(ILogSink... sinks) {
        this.sinks = new ArrayList<ILogSink>(sinks.length);
        for (ILogSink sink : sinks)
            this.sinks.add(sink);
    }

    public boolean add(ILogSink sink) {
        return sinks.add(sink);
    }

    public boolean remove(ILogSink sink) {
        return sinks.remove(sink);
    }

    @Override
    public void log(ILogEntry entry) {
        for (ILogSink sink : sinks)
            sink.log(entry);
    }

    @Override
    public void logMessage(Object message) {
        for (ILogSink sink : sinks)
            sink.logMessage(message);
    }

    @Override
    public void logException(Object message, Throwable exception) {
        for (ILogSink sink : sinks)
            sink.logException(message, exception);
    }

}
