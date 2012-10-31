package net.bodz.bas.log.event;

import net.bodz.bas.log.ILogSink;

public interface ILogEventSource {

    int getConfiguredVerboseLevel();

    void setConfiguredVerboseLevel(int configuredVerboseLevel);

    void addLogSink(int eventType, ILogSink logSink);

    void removeLogSink(int eventType, ILogSink logSink);

}
