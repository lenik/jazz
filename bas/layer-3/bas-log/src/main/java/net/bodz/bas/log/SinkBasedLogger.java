package net.bodz.bas.log;

import javax.annotation.Generated;

@Generated("LoggerCG")
public abstract class SinkBasedLogger
        extends AbstractLogger
        implements Logger {

    /** ⇱ Implementation Of {@link ILog4jCompat}. */
    /* _____________________________ */static section.iface __LOG4J__;

    @Override
    public boolean fatal(Object message, Throwable t) {
        get(LogLevel.FATAL).p(t, message);
        return false;
    }

    @Override
    public boolean error(Object message, Throwable t) {
        get(LogLevel.ERROR).p(t, message);
        return false;
    }

    @Override
    public void warn(Object message, Throwable t) {
        get(LogLevel.WARN).p(t, message);
    }

    @Override
    public void info(Object message, Throwable t) {
        get(LogLevel.INFO).p(t, message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        get(LogLevel.DEBUG).p(t, message);
    }

    /** ⇱ Implementation Of {@link Logger}. */
    /* _____________________________ */static section.iface __LOGGER__;

    @Override
    public final ILogSink getStderrSink() {
        return get(LogLevel.STDERR);
    }

    @Override
    public final ILogSink getStderrSink(int delta) {
        return get(LogLevel.STDERR, delta);
    }

    @Override
    public void _stderr(int delta, Throwable e, Object message) {
        getStderrSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getStdoutSink() {
        return get(LogLevel.STDOUT);
    }

    @Override
    public final ILogSink getStdoutSink(int delta) {
        return get(LogLevel.STDOUT, delta);
    }

    @Override
    public void _stdout(int delta, Throwable e, Object message) {
        getStdoutSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getFatalSink() {
        return get(LogLevel.FATAL);
    }

    @Override
    public final ILogSink getFatalSink(int delta) {
        return get(LogLevel.FATAL, delta);
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        getFatalSink(delta).p(e, message);
        return false;
    }

    @Override
    public final ILogSink getErrorSink() {
        return get(LogLevel.ERROR);
    }

    @Override
    public final ILogSink getErrorSink(int delta) {
        return get(LogLevel.ERROR, delta);
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        getErrorSink(delta).p(e, message);
        return false;
    }

    @Override
    public final ILogSink getWarnSink() {
        return get(LogLevel.WARN);
    }

    @Override
    public final ILogSink getWarnSink(int delta) {
        return get(LogLevel.WARN, delta);
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        getWarnSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getMesgSink() {
        return get(LogLevel.MESG);
    }

    @Override
    public final ILogSink getMesgSink(int delta) {
        return get(LogLevel.MESG, delta);
    }

    @Override
    public void _mesg(int delta, Throwable e, Object message) {
        getMesgSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getInfoSink() {
        return get(LogLevel.INFO);
    }

    @Override
    public final ILogSink getInfoSink(int delta) {
        return get(LogLevel.INFO, delta);
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        getInfoSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getLogSink() {
        return get(LogLevel.LOG);
    }

    @Override
    public final ILogSink getLogSink(int delta) {
        return get(LogLevel.LOG, delta);
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        getLogSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getDebugSink() {
        return get(LogLevel.DEBUG);
    }

    @Override
    public final ILogSink getDebugSink(int delta) {
        return get(LogLevel.DEBUG, delta);
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        getDebugSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getTraceSink() {
        return get(LogLevel.TRACE);
    }

    @Override
    public final ILogSink getTraceSink(int delta) {
        return get(LogLevel.TRACE, delta);
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        getTraceSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getStatusSink() {
        return get(LogLevel.STATUS);
    }

    @Override
    public final ILogSink getStatusSink(int delta) {
        return get(LogLevel.STATUS, delta);
    }

    @Override
    public void _status(int delta, Throwable e, Object message) {
        getStatusSink(delta).p(e, message);
    }

    @Override
    public final ILogSink getProgressSink() {
        return get(LogLevel.PROGRESS);
    }

    @Override
    public final ILogSink getProgressSink(int delta) {
        return get(LogLevel.PROGRESS, delta);
    }

    @Override
    public void _progress(int delta, Throwable e, Object message) {
        getProgressSink(delta).p(e, message);
    }

}
