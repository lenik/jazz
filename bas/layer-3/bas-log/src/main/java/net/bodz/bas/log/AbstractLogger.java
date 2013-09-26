package net.bodz.bas.log;

import javax.annotation.Generated;

@Generated("LoggerCG")
public abstract class AbstractLogger
        extends AbstractLoggerCompat
        implements Logger {

    @Override
    public void stderr(Object message) {
        getStderrSink().p(message);
    }

    @Override
    public void stdout(Object message) {
        getStdoutSink().p(message);
    }

    @Override
    public void mesg(Object message) {
        getMesgSink().p(message);
    }

    @Override
    public void log(Object message) {
        getLogSink().p(message);
    }

    @Override
    public void trace(Object message) {
        getTraceSink().p(message);
    }

    @Override
    public void status(Object message) {
        getStatusSink().p(message);
    }

    @Override
    public void progress(Object message) {
        getProgressSink().p(message);
    }

    @Override
    public final boolean fatal(Object message, Throwable t) {
        return _fatal(0, t, message);
    }

    @Override
    public final boolean error(Object message, Throwable t) {
        return _error(0, t, message);
    }

    @Override
    public final void warn(Object message, Throwable t) {
        _warn(0, t, message);
    }

    @Override
    public final void info(Object message, Throwable t) {
        _info(0, t, message);
    }

    @Override
    public final void debug(Object message, Throwable t) {
        _debug(0, t, message);
    }

    // --- LOGGER CG GENERATED BEGIN ---

    @Override
    public boolean isStderrEnabled() {
        return LogLevel.STDERR.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isStderrEnabled(int delta) {
        return LogLevel.STDERR.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public final ILogSink getStderrSink() {
        return get(LogLevel.STDERR);
    }

    @Override
    public final ILogSink getStderrSink(int delta) {
        return get(LogLevel.STDERR, delta);
    }

    @Override
    public final void stderr(Throwable e, Object message) {
        _stderr(0, e, message);
    }

    @Override
    public final void _stderr(int delta, Object message) {
        _stderr(delta, null, message);
    }

    @Override
    public void _stderr(int delta, Throwable e, Object message) {
        getStderrSink(delta).p(e, message);
    }

    @Override
    public final void stderr(Object... messageArray) {
        _stderr(0, null, concat(messageArray));
    }

    @Override
    public final void stderr(Throwable e, Object... messageArray) {
        _stderr(0, e, concat(messageArray));
    }

    @Override
    public final void _stderr(int delta, Object... messageArray) {
        _stderr(delta, null, concat(messageArray));
    }

    @Override
    public final void _stderr(int delta, Throwable e, Object... messageArray) {
        _stderr(delta, e, concat(messageArray));
    }

    @Override
    public final void stderrf(String fmt, Object... args) {
        _stderr(0, null, format(fmt, args));
    }

    @Override
    public final void stderrf(Throwable e, String fmt, Object... args) {
        _stderr(0, e, format(fmt, args));
    }

    @Override
    public final void _stderrf(int delta, String fmt, Object... args) {
        _stderr(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _stderrf(int delta, Throwable e, String fmt, Object... args) {
        _stderr(delta, e, format(fmt, args));
    }

    @Override
    public boolean isStdoutEnabled() {
        return LogLevel.STDOUT.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isStdoutEnabled(int delta) {
        return LogLevel.STDOUT.getPriority() + delta <= getMaxPriority();
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
    public final void stdout(Throwable e, Object message) {
        _stdout(0, e, message);
    }

    @Override
    public final void _stdout(int delta, Object message) {
        _stdout(delta, null, message);
    }

    @Override
    public void _stdout(int delta, Throwable e, Object message) {
        getStdoutSink(delta).p(e, message);
    }

    @Override
    public final void stdout(Object... messageArray) {
        _stdout(0, null, concat(messageArray));
    }

    @Override
    public final void stdout(Throwable e, Object... messageArray) {
        _stdout(0, e, concat(messageArray));
    }

    @Override
    public final void _stdout(int delta, Object... messageArray) {
        _stdout(delta, null, concat(messageArray));
    }

    @Override
    public final void _stdout(int delta, Throwable e, Object... messageArray) {
        _stdout(delta, e, concat(messageArray));
    }

    @Override
    public final void stdoutf(String fmt, Object... args) {
        _stdout(0, null, format(fmt, args));
    }

    @Override
    public final void stdoutf(Throwable e, String fmt, Object... args) {
        _stdout(0, e, format(fmt, args));
    }

    @Override
    public final void _stdoutf(int delta, String fmt, Object... args) {
        _stdout(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _stdoutf(int delta, Throwable e, String fmt, Object... args) {
        _stdout(delta, e, format(fmt, args));
    }

    @Override
    public boolean isFatalEnabled() {
        return LogLevel.FATAL.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isFatalEnabled(int delta) {
        return LogLevel.FATAL.getPriority() + delta <= getMaxPriority();
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
    public final boolean fatal(Throwable e, Object message) {
        return _fatal(0, e, message);
    }

    @Override
    public final boolean _fatal(int delta, Object message) {
        return _fatal(delta, null, message);
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        getFatalSink(delta).p(e, message);
        return false;
    }

    @Override
    public final boolean fatal(Object... messageArray) {
        return _fatal(0, null, concat(messageArray));
    }

    @Override
    public final boolean fatal(Throwable e, Object... messageArray) {
        return _fatal(0, e, concat(messageArray));
    }

    @Override
    public final boolean _fatal(int delta, Object... messageArray) {
        return _fatal(delta, null, concat(messageArray));
    }

    @Override
    public final boolean _fatal(int delta, Throwable e, Object... messageArray) {
        return _fatal(delta, e, concat(messageArray));
    }

    @Override
    public final boolean fatalf(String fmt, Object... args) {
        return _fatal(0, null, format(fmt, args));
    }

    @Override
    public final boolean fatalf(Throwable e, String fmt, Object... args) {
        return _fatal(0, e, format(fmt, args));
    }

    @Override
    public final boolean _fatalf(int delta, String fmt, Object... args) {
        return _fatal(delta, fmt, format(fmt, args));
    }

    @Override
    public final boolean _fatalf(int delta, Throwable e, String fmt, Object... args) {
        return _fatal(delta, e, format(fmt, args));
    }

    @Override
    public boolean isErrorEnabled() {
        return LogLevel.ERROR.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isErrorEnabled(int delta) {
        return LogLevel.ERROR.getPriority() + delta <= getMaxPriority();
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
    public final boolean error(Throwable e, Object message) {
        return _error(0, e, message);
    }

    @Override
    public final boolean _error(int delta, Object message) {
        return _error(delta, null, message);
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        getErrorSink(delta).p(e, message);
        return false;
    }

    @Override
    public final boolean error(Object... messageArray) {
        return _error(0, null, concat(messageArray));
    }

    @Override
    public final boolean error(Throwable e, Object... messageArray) {
        return _error(0, e, concat(messageArray));
    }

    @Override
    public final boolean _error(int delta, Object... messageArray) {
        return _error(delta, null, concat(messageArray));
    }

    @Override
    public final boolean _error(int delta, Throwable e, Object... messageArray) {
        return _error(delta, e, concat(messageArray));
    }

    @Override
    public final boolean errorf(String fmt, Object... args) {
        return _error(0, null, format(fmt, args));
    }

    @Override
    public final boolean errorf(Throwable e, String fmt, Object... args) {
        return _error(0, e, format(fmt, args));
    }

    @Override
    public final boolean _errorf(int delta, String fmt, Object... args) {
        return _error(delta, fmt, format(fmt, args));
    }

    @Override
    public final boolean _errorf(int delta, Throwable e, String fmt, Object... args) {
        return _error(delta, e, format(fmt, args));
    }

    @Override
    public boolean isWarnEnabled() {
        return LogLevel.WARN.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isWarnEnabled(int delta) {
        return LogLevel.WARN.getPriority() + delta <= getMaxPriority();
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
    public final void warn(Throwable e, Object message) {
        _warn(0, e, message);
    }

    @Override
    public final void _warn(int delta, Object message) {
        _warn(delta, null, message);
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        getWarnSink(delta).p(e, message);
    }

    @Override
    public final void warn(Object... messageArray) {
        _warn(0, null, concat(messageArray));
    }

    @Override
    public final void warn(Throwable e, Object... messageArray) {
        _warn(0, e, concat(messageArray));
    }

    @Override
    public final void _warn(int delta, Object... messageArray) {
        _warn(delta, null, concat(messageArray));
    }

    @Override
    public final void _warn(int delta, Throwable e, Object... messageArray) {
        _warn(delta, e, concat(messageArray));
    }

    @Override
    public final void warnf(String fmt, Object... args) {
        _warn(0, null, format(fmt, args));
    }

    @Override
    public final void warnf(Throwable e, String fmt, Object... args) {
        _warn(0, e, format(fmt, args));
    }

    @Override
    public final void _warnf(int delta, String fmt, Object... args) {
        _warn(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _warnf(int delta, Throwable e, String fmt, Object... args) {
        _warn(delta, e, format(fmt, args));
    }

    @Override
    public boolean isMesgEnabled() {
        return LogLevel.MESG.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isMesgEnabled(int delta) {
        return LogLevel.MESG.getPriority() + delta <= getMaxPriority();
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
    public final void mesg(Throwable e, Object message) {
        _mesg(0, e, message);
    }

    @Override
    public final void _mesg(int delta, Object message) {
        _mesg(delta, null, message);
    }

    @Override
    public void _mesg(int delta, Throwable e, Object message) {
        getMesgSink(delta).p(e, message);
    }

    @Override
    public final void mesg(Object... messageArray) {
        _mesg(0, null, concat(messageArray));
    }

    @Override
    public final void mesg(Throwable e, Object... messageArray) {
        _mesg(0, e, concat(messageArray));
    }

    @Override
    public final void _mesg(int delta, Object... messageArray) {
        _mesg(delta, null, concat(messageArray));
    }

    @Override
    public final void _mesg(int delta, Throwable e, Object... messageArray) {
        _mesg(delta, e, concat(messageArray));
    }

    @Override
    public final void mesgf(String fmt, Object... args) {
        _mesg(0, null, format(fmt, args));
    }

    @Override
    public final void mesgf(Throwable e, String fmt, Object... args) {
        _mesg(0, e, format(fmt, args));
    }

    @Override
    public final void _mesgf(int delta, String fmt, Object... args) {
        _mesg(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _mesgf(int delta, Throwable e, String fmt, Object... args) {
        _mesg(delta, e, format(fmt, args));
    }

    @Override
    public boolean isInfoEnabled() {
        return LogLevel.INFO.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isInfoEnabled(int delta) {
        return LogLevel.INFO.getPriority() + delta <= getMaxPriority();
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
    public final void info(Throwable e, Object message) {
        _info(0, e, message);
    }

    @Override
    public final void _info(int delta, Object message) {
        _info(delta, null, message);
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        getInfoSink(delta).p(e, message);
    }

    @Override
    public final void info(Object... messageArray) {
        _info(0, null, concat(messageArray));
    }

    @Override
    public final void info(Throwable e, Object... messageArray) {
        _info(0, e, concat(messageArray));
    }

    @Override
    public final void _info(int delta, Object... messageArray) {
        _info(delta, null, concat(messageArray));
    }

    @Override
    public final void _info(int delta, Throwable e, Object... messageArray) {
        _info(delta, e, concat(messageArray));
    }

    @Override
    public final void infof(String fmt, Object... args) {
        _info(0, null, format(fmt, args));
    }

    @Override
    public final void infof(Throwable e, String fmt, Object... args) {
        _info(0, e, format(fmt, args));
    }

    @Override
    public final void _infof(int delta, String fmt, Object... args) {
        _info(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _infof(int delta, Throwable e, String fmt, Object... args) {
        _info(delta, e, format(fmt, args));
    }

    @Override
    public boolean isLogEnabled() {
        return LogLevel.LOG.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isLogEnabled(int delta) {
        return LogLevel.LOG.getPriority() + delta <= getMaxPriority();
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
    public final void log(Throwable e, Object message) {
        _log(0, e, message);
    }

    @Override
    public final void _log(int delta, Object message) {
        _log(delta, null, message);
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        getLogSink(delta).p(e, message);
    }

    @Override
    public final void log(Object... messageArray) {
        _log(0, null, concat(messageArray));
    }

    @Override
    public final void log(Throwable e, Object... messageArray) {
        _log(0, e, concat(messageArray));
    }

    @Override
    public final void _log(int delta, Object... messageArray) {
        _log(delta, null, concat(messageArray));
    }

    @Override
    public final void _log(int delta, Throwable e, Object... messageArray) {
        _log(delta, e, concat(messageArray));
    }

    @Override
    public final void logf(String fmt, Object... args) {
        _log(0, null, format(fmt, args));
    }

    @Override
    public final void logf(Throwable e, String fmt, Object... args) {
        _log(0, e, format(fmt, args));
    }

    @Override
    public final void _logf(int delta, String fmt, Object... args) {
        _log(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _logf(int delta, Throwable e, String fmt, Object... args) {
        _log(delta, e, format(fmt, args));
    }

    @Override
    public boolean isDebugEnabled() {
        return LogLevel.DEBUG.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isDebugEnabled(int delta) {
        return LogLevel.DEBUG.getPriority() + delta <= getMaxPriority();
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
    public final void debug(Throwable e, Object message) {
        _debug(0, e, message);
    }

    @Override
    public final void _debug(int delta, Object message) {
        _debug(delta, null, message);
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        getDebugSink(delta).p(e, message);
    }

    @Override
    public final void debug(Object... messageArray) {
        _debug(0, null, concat(messageArray));
    }

    @Override
    public final void debug(Throwable e, Object... messageArray) {
        _debug(0, e, concat(messageArray));
    }

    @Override
    public final void _debug(int delta, Object... messageArray) {
        _debug(delta, null, concat(messageArray));
    }

    @Override
    public final void _debug(int delta, Throwable e, Object... messageArray) {
        _debug(delta, e, concat(messageArray));
    }

    @Override
    public final void debugf(String fmt, Object... args) {
        _debug(0, null, format(fmt, args));
    }

    @Override
    public final void debugf(Throwable e, String fmt, Object... args) {
        _debug(0, e, format(fmt, args));
    }

    @Override
    public final void _debugf(int delta, String fmt, Object... args) {
        _debug(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _debugf(int delta, Throwable e, String fmt, Object... args) {
        _debug(delta, e, format(fmt, args));
    }

    @Override
    public boolean isTraceEnabled() {
        return LogLevel.TRACE.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isTraceEnabled(int delta) {
        return LogLevel.TRACE.getPriority() + delta <= getMaxPriority();
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
    public final void trace(Throwable e, Object message) {
        _trace(0, e, message);
    }

    @Override
    public final void _trace(int delta, Object message) {
        _trace(delta, null, message);
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        getTraceSink(delta).p(e, message);
    }

    @Override
    public final void trace(Object... messageArray) {
        _trace(0, null, concat(messageArray));
    }

    @Override
    public final void trace(Throwable e, Object... messageArray) {
        _trace(0, e, concat(messageArray));
    }

    @Override
    public final void _trace(int delta, Object... messageArray) {
        _trace(delta, null, concat(messageArray));
    }

    @Override
    public final void _trace(int delta, Throwable e, Object... messageArray) {
        _trace(delta, e, concat(messageArray));
    }

    @Override
    public final void tracef(String fmt, Object... args) {
        _trace(0, null, format(fmt, args));
    }

    @Override
    public final void tracef(Throwable e, String fmt, Object... args) {
        _trace(0, e, format(fmt, args));
    }

    @Override
    public final void _tracef(int delta, String fmt, Object... args) {
        _trace(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _tracef(int delta, Throwable e, String fmt, Object... args) {
        _trace(delta, e, format(fmt, args));
    }

    @Override
    public boolean isStatusEnabled() {
        return LogLevel.STATUS.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isStatusEnabled(int delta) {
        return LogLevel.STATUS.getPriority() + delta <= getMaxPriority();
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
    public final void status(Throwable e, Object message) {
        _status(0, e, message);
    }

    @Override
    public final void _status(int delta, Object message) {
        _status(delta, null, message);
    }

    @Override
    public void _status(int delta, Throwable e, Object message) {
        getStatusSink(delta).p(e, message);
    }

    @Override
    public final void status(Object... messageArray) {
        _status(0, null, concat(messageArray));
    }

    @Override
    public final void status(Throwable e, Object... messageArray) {
        _status(0, e, concat(messageArray));
    }

    @Override
    public final void _status(int delta, Object... messageArray) {
        _status(delta, null, concat(messageArray));
    }

    @Override
    public final void _status(int delta, Throwable e, Object... messageArray) {
        _status(delta, e, concat(messageArray));
    }

    @Override
    public final void statusf(String fmt, Object... args) {
        _status(0, null, format(fmt, args));
    }

    @Override
    public final void statusf(Throwable e, String fmt, Object... args) {
        _status(0, e, format(fmt, args));
    }

    @Override
    public final void _statusf(int delta, String fmt, Object... args) {
        _status(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _statusf(int delta, Throwable e, String fmt, Object... args) {
        _status(delta, e, format(fmt, args));
    }

    @Override
    public boolean isProgressEnabled() {
        return LogLevel.PROGRESS.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isProgressEnabled(int delta) {
        return LogLevel.PROGRESS.getPriority() + delta <= getMaxPriority();
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
    public final void progress(Throwable e, Object message) {
        _progress(0, e, message);
    }

    @Override
    public final void _progress(int delta, Object message) {
        _progress(delta, null, message);
    }

    @Override
    public void _progress(int delta, Throwable e, Object message) {
        getProgressSink(delta).p(e, message);
    }

    @Override
    public final void progress(Object... messageArray) {
        _progress(0, null, concat(messageArray));
    }

    @Override
    public final void progress(Throwable e, Object... messageArray) {
        _progress(0, e, concat(messageArray));
    }

    @Override
    public final void _progress(int delta, Object... messageArray) {
        _progress(delta, null, concat(messageArray));
    }

    @Override
    public final void _progress(int delta, Throwable e, Object... messageArray) {
        _progress(delta, e, concat(messageArray));
    }

    @Override
    public final void progressf(String fmt, Object... args) {
        _progress(0, null, format(fmt, args));
    }

    @Override
    public final void progressf(Throwable e, String fmt, Object... args) {
        _progress(0, e, format(fmt, args));
    }

    @Override
    public final void _progressf(int delta, String fmt, Object... args) {
        _progress(delta, fmt, format(fmt, args));
    }

    @Override
    public final void _progressf(int delta, Throwable e, String fmt, Object... args) {
        _progress(delta, e, format(fmt, args));
    }

    // +++ LOGGER CG GENERATED END +++

}
