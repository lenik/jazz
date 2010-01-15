package net.bodz.bas.log;

import static net.bodz.bas.log.ILogSink.LEVEL_DEFAULT;
import static net.bodz.bas.log.ILogSink.LEVEL_STEPLEN;

public abstract class AbstractLogLayer
        implements ILogLayer {

    static final int DEFAULT_ROUND = LEVEL_DEFAULT - LEVEL_STEPLEN / 2;

    protected int mixLevel(int eventType, int verboseLevel) {
        eventType += (verboseLevel - DEFAULT_ROUND) / LEVEL_STEPLEN;
        return eventType;
    }

    @Override
    public ILogSink getStdoutSink() {
        return get(STDOUT, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getErrorSink() {
        return get(ERROR, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getWarnSink() {
        return get(WARN, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getInfoSink() {
        return get(INFO, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getInfoSink(int verboseLevel) {
        return get(INFO, verboseLevel);
    }

    @Override
    public ILogSink getDebugSink() {
        return get(DEBUG, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getDebugSink(int verboseLevel) {
        return get(DEBUG, verboseLevel);
    }

    @Override
    public ILogSink getTraceSink() {
        return get(TRACE, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getTraceSink(int verboseLevel) {
        return get(TRACE, verboseLevel);
    }

    @Override
    public IStatusSink getStatusSink() {
        return (IStatusSink) get(STATUS, LEVEL_DEFAULT);
    }

    @Override
    public IStatusSink getStatusSink(int verboseLevel) {
        return (IStatusSink) get(STATUS, verboseLevel);
    }

}
