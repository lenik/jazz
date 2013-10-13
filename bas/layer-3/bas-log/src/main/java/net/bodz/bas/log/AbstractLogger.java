package net.bodz.bas.log;

public abstract class AbstractLogger
        extends AbstractLogComposite
        implements ILogger {

    /** ⇱ Implementation Of {@link ILogger}. */
    /* _____________________________ */static section.iface __ILOGGER__;

    @Override
    public boolean isStderrEnabled() {
        return LogLevel.STDERR.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isStderrEnabled(int delta) {
        return LogLevel.STDERR.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public final void stderr(Object message) {
        _stderr(0, null, message);
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
    public final void stderr(Object... fragments) {
        _stderr(0, null, concat(fragments));
    }

    @Override
    public final void stderr(Throwable e, Object... fragments) {
        _stderr(0, e, concat(fragments));
    }

    @Override
    public final void _stderr(int delta, Object... fragments) {
        _stderr(delta, null, concat(fragments));
    }

    @Override
    public final void _stderr(int delta, Throwable e, Object... fragments) {
        _stderr(delta, e, concat(fragments));
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
    public final void stdout(Object message) {
        _stdout(0, null, message);
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
    public final void stdout(Object... fragments) {
        _stdout(0, null, concat(fragments));
    }

    @Override
    public final void stdout(Throwable e, Object... fragments) {
        _stdout(0, e, concat(fragments));
    }

    @Override
    public final void _stdout(int delta, Object... fragments) {
        _stdout(delta, null, concat(fragments));
    }

    @Override
    public final void _stdout(int delta, Throwable e, Object... fragments) {
        _stdout(delta, e, concat(fragments));
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
    public final boolean fatal(Object message) {
        return _fatal(0, null, message);
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
    public final boolean fatal(Object... fragments) {
        return _fatal(0, null, concat(fragments));
    }

    @Override
    public final boolean fatal(Throwable e, Object... fragments) {
        return _fatal(0, e, concat(fragments));
    }

    @Override
    public final boolean _fatal(int delta, Object... fragments) {
        return _fatal(delta, null, concat(fragments));
    }

    @Override
    public final boolean _fatal(int delta, Throwable e, Object... fragments) {
        return _fatal(delta, e, concat(fragments));
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
    public final boolean error(Object message) {
        return _error(0, null, message);
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
    public final boolean error(Object... fragments) {
        return _error(0, null, concat(fragments));
    }

    @Override
    public final boolean error(Throwable e, Object... fragments) {
        return _error(0, e, concat(fragments));
    }

    @Override
    public final boolean _error(int delta, Object... fragments) {
        return _error(delta, null, concat(fragments));
    }

    @Override
    public final boolean _error(int delta, Throwable e, Object... fragments) {
        return _error(delta, e, concat(fragments));
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
    public final void warn(Object message) {
        _warn(0, null, message);
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
    public final void warn(Object... fragments) {
        _warn(0, null, concat(fragments));
    }

    @Override
    public final void warn(Throwable e, Object... fragments) {
        _warn(0, e, concat(fragments));
    }

    @Override
    public final void _warn(int delta, Object... fragments) {
        _warn(delta, null, concat(fragments));
    }

    @Override
    public final void _warn(int delta, Throwable e, Object... fragments) {
        _warn(delta, e, concat(fragments));
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
    public final void mesg(Object message) {
        _mesg(0, null, message);
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
    public final void mesg(Object... fragments) {
        _mesg(0, null, concat(fragments));
    }

    @Override
    public final void mesg(Throwable e, Object... fragments) {
        _mesg(0, e, concat(fragments));
    }

    @Override
    public final void _mesg(int delta, Object... fragments) {
        _mesg(delta, null, concat(fragments));
    }

    @Override
    public final void _mesg(int delta, Throwable e, Object... fragments) {
        _mesg(delta, e, concat(fragments));
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
    public final void info(Object message) {
        _info(0, null, message);
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
    public final void info(Object... fragments) {
        _info(0, null, concat(fragments));
    }

    @Override
    public final void info(Throwable e, Object... fragments) {
        _info(0, e, concat(fragments));
    }

    @Override
    public final void _info(int delta, Object... fragments) {
        _info(delta, null, concat(fragments));
    }

    @Override
    public final void _info(int delta, Throwable e, Object... fragments) {
        _info(delta, e, concat(fragments));
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
    public final void log(Object message) {
        _log(0, null, message);
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
    public final void log(Object... fragments) {
        _log(0, null, concat(fragments));
    }

    @Override
    public final void log(Throwable e, Object... fragments) {
        _log(0, e, concat(fragments));
    }

    @Override
    public final void _log(int delta, Object... fragments) {
        _log(delta, null, concat(fragments));
    }

    @Override
    public final void _log(int delta, Throwable e, Object... fragments) {
        _log(delta, e, concat(fragments));
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
    public final void debug(Object message) {
        _debug(0, null, message);
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
    public final void debug(Object... fragments) {
        _debug(0, null, concat(fragments));
    }

    @Override
    public final void debug(Throwable e, Object... fragments) {
        _debug(0, e, concat(fragments));
    }

    @Override
    public final void _debug(int delta, Object... fragments) {
        _debug(delta, null, concat(fragments));
    }

    @Override
    public final void _debug(int delta, Throwable e, Object... fragments) {
        _debug(delta, e, concat(fragments));
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
    public final void trace(Object message) {
        _trace(0, null, message);
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
    public final void trace(Object... fragments) {
        _trace(0, null, concat(fragments));
    }

    @Override
    public final void trace(Throwable e, Object... fragments) {
        _trace(0, e, concat(fragments));
    }

    @Override
    public final void _trace(int delta, Object... fragments) {
        _trace(delta, null, concat(fragments));
    }

    @Override
    public final void _trace(int delta, Throwable e, Object... fragments) {
        _trace(delta, e, concat(fragments));
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
    public final void status(Object message) {
        _status(0, null, message);
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
    public final void status(Object... fragments) {
        _status(0, null, concat(fragments));
    }

    @Override
    public final void status(Throwable e, Object... fragments) {
        _status(0, e, concat(fragments));
    }

    @Override
    public final void _status(int delta, Object... fragments) {
        _status(delta, null, concat(fragments));
    }

    @Override
    public final void _status(int delta, Throwable e, Object... fragments) {
        _status(delta, e, concat(fragments));
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
    public final void progress(Object message) {
        _progress(0, null, message);
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
    public final void progress(Object... fragments) {
        _progress(0, null, concat(fragments));
    }

    @Override
    public final void progress(Throwable e, Object... fragments) {
        _progress(0, e, concat(fragments));
    }

    @Override
    public final void _progress(int delta, Object... fragments) {
        _progress(delta, null, concat(fragments));
    }

    @Override
    public final void _progress(int delta, Throwable e, Object... fragments) {
        _progress(delta, e, concat(fragments));
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

}
