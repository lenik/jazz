package net.bodz.bas.log.api;

import javax.annotation.Generated;

import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;

@Generated("LoggerCG")
public abstract class AbstractLogger
        extends AbstractLoggerCompat
        implements Logger {

    @Override
    public boolean isStderrEnabled() {
        return LogLevel.STDERR.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isStderrEnabled(int delta) {
        return LogLevel.STDERR.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public ILogSink getStderrSink() {
        return get(LogLevel.STDERR);
    }

    @Override
    public ILogSink getStderrSink(int delta) {
        return get(LogLevel.STDERR, delta);
    }

    @Override
    public void stderr(Object message) {
        getStderrSink().p(message);
    }

    @Override
    public void stderr(Throwable e, Object message) {
        getStderrSink().p(e, message);
    }

    @Override
    public void _stderr(int delta, Object message) {
        getStderrSink(delta).p(message);
    }

    @Override
    public void _stderr(int delta, Throwable e, Object message) {
        getStderrSink(delta).p(e, message);
    }

    @Override
    public void stderr(Object... messageArray) {
        getStderrSink().p(messageArray);
    }

    @Override
    public void stderr(Throwable e, Object... messageArray) {
        getStderrSink().p(e, messageArray);
    }

    @Override
    public void _stderr(int delta, Object... messageArray) {
        getStderrSink(delta).p(messageArray);
    }

    @Override
    public void _stderr(int delta, Throwable e, Object... messageArray) {
        getStderrSink(delta).p(e, messageArray);
    }

    @Override
    public void stderrFormat(String format, Object... args) {
        getStderrSink().f(format, args);
    }

    @Override
    public void stderrFormat(Throwable e, String format, Object... args) {
        getStderrSink().f(e, format, args);
    }

    @Override
    public void _stderrFormat(int delta, String format, Object... args) {
        getStderrSink(delta).f(format, args);
    }

    @Override
    public void _stderrFormat(int delta, Throwable e, String format, Object... args) {
        getStderrSink(delta).f(e, format, args);
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
    public ILogSink getStdoutSink() {
        return get(LogLevel.STDOUT);
    }

    @Override
    public ILogSink getStdoutSink(int delta) {
        return get(LogLevel.STDOUT, delta);
    }

    @Override
    public void stdout(Object message) {
        getStdoutSink().p(message);
    }

    @Override
    public void stdout(Throwable e, Object message) {
        getStdoutSink().p(e, message);
    }

    @Override
    public void _stdout(int delta, Object message) {
        getStdoutSink(delta).p(message);
    }

    @Override
    public void _stdout(int delta, Throwable e, Object message) {
        getStdoutSink(delta).p(e, message);
    }

    @Override
    public void stdout(Object... messageArray) {
        getStdoutSink().p(messageArray);
    }

    @Override
    public void stdout(Throwable e, Object... messageArray) {
        getStdoutSink().p(e, messageArray);
    }

    @Override
    public void _stdout(int delta, Object... messageArray) {
        getStdoutSink(delta).p(messageArray);
    }

    @Override
    public void _stdout(int delta, Throwable e, Object... messageArray) {
        getStdoutSink(delta).p(e, messageArray);
    }

    @Override
    public void stdoutFormat(String format, Object... args) {
        getStdoutSink().f(format, args);
    }

    @Override
    public void stdoutFormat(Throwable e, String format, Object... args) {
        getStdoutSink().f(e, format, args);
    }

    @Override
    public void _stdoutFormat(int delta, String format, Object... args) {
        getStdoutSink(delta).f(format, args);
    }

    @Override
    public void _stdoutFormat(int delta, Throwable e, String format, Object... args) {
        getStdoutSink(delta).f(e, format, args);
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
    public ILogSink getFatalSink() {
        return get(LogLevel.FATAL);
    }

    @Override
    public ILogSink getFatalSink(int delta) {
        return get(LogLevel.FATAL, delta);
    }

    @Override
    public boolean fatal(Object message) {
        getFatalSink().p(message);
        return false;
    }

    @Override
    public boolean fatal(Throwable e, Object message) {
        getFatalSink().p(e, message);
        return false;
    }

    @Override
    public boolean _fatal(int delta, Object message) {
        getFatalSink(delta).p(message);
        return false;
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        getFatalSink(delta).p(e, message);
        return false;
    }

    @Override
    public boolean fatal(Object... messageArray) {
        getFatalSink().p(messageArray);
        return false;
    }

    @Override
    public boolean fatal(Throwable e, Object... messageArray) {
        getFatalSink().p(e, messageArray);
        return false;
    }

    @Override
    public boolean _fatal(int delta, Object... messageArray) {
        getFatalSink(delta).p(messageArray);
        return false;
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object... messageArray) {
        getFatalSink(delta).p(e, messageArray);
        return false;
    }

    @Override
    public boolean fatalFormat(String format, Object... args) {
        getFatalSink().f(format, args);
        return false;
    }

    @Override
    public boolean fatalFormat(Throwable e, String format, Object... args) {
        getFatalSink().f(e, format, args);
        return false;
    }

    @Override
    public boolean _fatalFormat(int delta, String format, Object... args) {
        getFatalSink(delta).f(format, args);
        return false;
    }

    @Override
    public boolean _fatalFormat(int delta, Throwable e, String format, Object... args) {
        getFatalSink(delta).f(e, format, args);
        return false;
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
    public ILogSink getErrorSink() {
        return get(LogLevel.ERROR);
    }

    @Override
    public ILogSink getErrorSink(int delta) {
        return get(LogLevel.ERROR, delta);
    }

    @Override
    public boolean error(Object message) {
        getErrorSink().p(message);
        return false;
    }

    @Override
    public boolean error(Throwable e, Object message) {
        getErrorSink().p(e, message);
        return false;
    }

    @Override
    public boolean _error(int delta, Object message) {
        getErrorSink(delta).p(message);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        getErrorSink(delta).p(e, message);
        return false;
    }

    @Override
    public boolean error(Object... messageArray) {
        getErrorSink().p(messageArray);
        return false;
    }

    @Override
    public boolean error(Throwable e, Object... messageArray) {
        getErrorSink().p(e, messageArray);
        return false;
    }

    @Override
    public boolean _error(int delta, Object... messageArray) {
        getErrorSink(delta).p(messageArray);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable e, Object... messageArray) {
        getErrorSink(delta).p(e, messageArray);
        return false;
    }

    @Override
    public boolean errorFormat(String format, Object... args) {
        getErrorSink().f(format, args);
        return false;
    }

    @Override
    public boolean errorFormat(Throwable e, String format, Object... args) {
        getErrorSink().f(e, format, args);
        return false;
    }

    @Override
    public boolean _errorFormat(int delta, String format, Object... args) {
        getErrorSink(delta).f(format, args);
        return false;
    }

    @Override
    public boolean _errorFormat(int delta, Throwable e, String format, Object... args) {
        getErrorSink(delta).f(e, format, args);
        return false;
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
    public ILogSink getWarnSink() {
        return get(LogLevel.WARN);
    }

    @Override
    public ILogSink getWarnSink(int delta) {
        return get(LogLevel.WARN, delta);
    }

    @Override
    public void warn(Object message) {
        getWarnSink().p(message);
    }

    @Override
    public void warn(Throwable e, Object message) {
        getWarnSink().p(e, message);
    }

    @Override
    public void _warn(int delta, Object message) {
        getWarnSink(delta).p(message);
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        getWarnSink(delta).p(e, message);
    }

    @Override
    public void warn(Object... messageArray) {
        getWarnSink().p(messageArray);
    }

    @Override
    public void warn(Throwable e, Object... messageArray) {
        getWarnSink().p(e, messageArray);
    }

    @Override
    public void _warn(int delta, Object... messageArray) {
        getWarnSink(delta).p(messageArray);
    }

    @Override
    public void _warn(int delta, Throwable e, Object... messageArray) {
        getWarnSink(delta).p(e, messageArray);
    }

    @Override
    public void warnFormat(String format, Object... args) {
        getWarnSink().f(format, args);
    }

    @Override
    public void warnFormat(Throwable e, String format, Object... args) {
        getWarnSink().f(e, format, args);
    }

    @Override
    public void _warnFormat(int delta, String format, Object... args) {
        getWarnSink(delta).f(format, args);
    }

    @Override
    public void _warnFormat(int delta, Throwable e, String format, Object... args) {
        getWarnSink(delta).f(e, format, args);
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
    public ILogSink getLogSink() {
        return get(LogLevel.LOG);
    }

    @Override
    public ILogSink getLogSink(int delta) {
        return get(LogLevel.LOG, delta);
    }

    @Override
    public void log(Object message) {
        getLogSink().p(message);
    }

    @Override
    public void log(Throwable e, Object message) {
        getLogSink().p(e, message);
    }

    @Override
    public void _log(int delta, Object message) {
        getLogSink(delta).p(message);
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        getLogSink(delta).p(e, message);
    }

    @Override
    public void log(Object... messageArray) {
        getLogSink().p(messageArray);
    }

    @Override
    public void log(Throwable e, Object... messageArray) {
        getLogSink().p(e, messageArray);
    }

    @Override
    public void _log(int delta, Object... messageArray) {
        getLogSink(delta).p(messageArray);
    }

    @Override
    public void _log(int delta, Throwable e, Object... messageArray) {
        getLogSink(delta).p(e, messageArray);
    }

    @Override
    public void logFormat(String format, Object... args) {
        getLogSink().f(format, args);
    }

    @Override
    public void logFormat(Throwable e, String format, Object... args) {
        getLogSink().f(e, format, args);
    }

    @Override
    public void _logFormat(int delta, String format, Object... args) {
        getLogSink(delta).f(format, args);
    }

    @Override
    public void _logFormat(int delta, Throwable e, String format, Object... args) {
        getLogSink(delta).f(e, format, args);
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
    public ILogSink getInfoSink() {
        return get(LogLevel.INFO);
    }

    @Override
    public ILogSink getInfoSink(int delta) {
        return get(LogLevel.INFO, delta);
    }

    @Override
    public void info(Object message) {
        getInfoSink().p(message);
    }

    @Override
    public void info(Throwable e, Object message) {
        getInfoSink().p(e, message);
    }

    @Override
    public void _info(int delta, Object message) {
        getInfoSink(delta).p(message);
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        getInfoSink(delta).p(e, message);
    }

    @Override
    public void info(Object... messageArray) {
        getInfoSink().p(messageArray);
    }

    @Override
    public void info(Throwable e, Object... messageArray) {
        getInfoSink().p(e, messageArray);
    }

    @Override
    public void _info(int delta, Object... messageArray) {
        getInfoSink(delta).p(messageArray);
    }

    @Override
    public void _info(int delta, Throwable e, Object... messageArray) {
        getInfoSink(delta).p(e, messageArray);
    }

    @Override
    public void infoFormat(String format, Object... args) {
        getInfoSink().f(format, args);
    }

    @Override
    public void infoFormat(Throwable e, String format, Object... args) {
        getInfoSink().f(e, format, args);
    }

    @Override
    public void _infoFormat(int delta, String format, Object... args) {
        getInfoSink(delta).f(format, args);
    }

    @Override
    public void _infoFormat(int delta, Throwable e, String format, Object... args) {
        getInfoSink(delta).f(e, format, args);
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
    public ILogSink getDebugSink() {
        return get(LogLevel.DEBUG);
    }

    @Override
    public ILogSink getDebugSink(int delta) {
        return get(LogLevel.DEBUG, delta);
    }

    @Override
    public void debug(Object message) {
        getDebugSink().p(message);
    }

    @Override
    public void debug(Throwable e, Object message) {
        getDebugSink().p(e, message);
    }

    @Override
    public void _debug(int delta, Object message) {
        getDebugSink(delta).p(message);
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        getDebugSink(delta).p(e, message);
    }

    @Override
    public void debug(Object... messageArray) {
        getDebugSink().p(messageArray);
    }

    @Override
    public void debug(Throwable e, Object... messageArray) {
        getDebugSink().p(e, messageArray);
    }

    @Override
    public void _debug(int delta, Object... messageArray) {
        getDebugSink(delta).p(messageArray);
    }

    @Override
    public void _debug(int delta, Throwable e, Object... messageArray) {
        getDebugSink(delta).p(e, messageArray);
    }

    @Override
    public void debugFormat(String format, Object... args) {
        getDebugSink().f(format, args);
    }

    @Override
    public void debugFormat(Throwable e, String format, Object... args) {
        getDebugSink().f(e, format, args);
    }

    @Override
    public void _debugFormat(int delta, String format, Object... args) {
        getDebugSink(delta).f(format, args);
    }

    @Override
    public void _debugFormat(int delta, Throwable e, String format, Object... args) {
        getDebugSink(delta).f(e, format, args);
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
    public ILogSink getTraceSink() {
        return get(LogLevel.TRACE);
    }

    @Override
    public ILogSink getTraceSink(int delta) {
        return get(LogLevel.TRACE, delta);
    }

    @Override
    public void trace(Object message) {
        getTraceSink().p(message);
    }

    @Override
    public void trace(Throwable e, Object message) {
        getTraceSink().p(e, message);
    }

    @Override
    public void _trace(int delta, Object message) {
        getTraceSink(delta).p(message);
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        getTraceSink(delta).p(e, message);
    }

    @Override
    public void trace(Object... messageArray) {
        getTraceSink().p(messageArray);
    }

    @Override
    public void trace(Throwable e, Object... messageArray) {
        getTraceSink().p(e, messageArray);
    }

    @Override
    public void _trace(int delta, Object... messageArray) {
        getTraceSink(delta).p(messageArray);
    }

    @Override
    public void _trace(int delta, Throwable e, Object... messageArray) {
        getTraceSink(delta).p(e, messageArray);
    }

    @Override
    public void traceFormat(String format, Object... args) {
        getTraceSink().f(format, args);
    }

    @Override
    public void traceFormat(Throwable e, String format, Object... args) {
        getTraceSink().f(e, format, args);
    }

    @Override
    public void _traceFormat(int delta, String format, Object... args) {
        getTraceSink(delta).f(format, args);
    }

    @Override
    public void _traceFormat(int delta, Throwable e, String format, Object... args) {
        getTraceSink(delta).f(e, format, args);
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
    public ILogSink getStatusSink() {
        return get(LogLevel.STATUS);
    }

    @Override
    public ILogSink getStatusSink(int delta) {
        return get(LogLevel.STATUS, delta);
    }

    @Override
    public void status(Object message) {
        getStatusSink().p(message);
    }

    @Override
    public void status(Throwable e, Object message) {
        getStatusSink().p(e, message);
    }

    @Override
    public void _status(int delta, Object message) {
        getStatusSink(delta).p(message);
    }

    @Override
    public void _status(int delta, Throwable e, Object message) {
        getStatusSink(delta).p(e, message);
    }

    @Override
    public void status(Object... messageArray) {
        getStatusSink().p(messageArray);
    }

    @Override
    public void status(Throwable e, Object... messageArray) {
        getStatusSink().p(e, messageArray);
    }

    @Override
    public void _status(int delta, Object... messageArray) {
        getStatusSink(delta).p(messageArray);
    }

    @Override
    public void _status(int delta, Throwable e, Object... messageArray) {
        getStatusSink(delta).p(e, messageArray);
    }

    @Override
    public void statusFormat(String format, Object... args) {
        getStatusSink().f(format, args);
    }

    @Override
    public void statusFormat(Throwable e, String format, Object... args) {
        getStatusSink().f(e, format, args);
    }

    @Override
    public void _statusFormat(int delta, String format, Object... args) {
        getStatusSink(delta).f(format, args);
    }

    @Override
    public void _statusFormat(int delta, Throwable e, String format, Object... args) {
        getStatusSink(delta).f(e, format, args);
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
    public ILogSink getProgressSink() {
        return get(LogLevel.PROGRESS);
    }

    @Override
    public ILogSink getProgressSink(int delta) {
        return get(LogLevel.PROGRESS, delta);
    }

    @Override
    public void progress(Object message) {
        getProgressSink().p(message);
    }

    @Override
    public void progress(Throwable e, Object message) {
        getProgressSink().p(e, message);
    }

    @Override
    public void _progress(int delta, Object message) {
        getProgressSink(delta).p(message);
    }

    @Override
    public void _progress(int delta, Throwable e, Object message) {
        getProgressSink(delta).p(e, message);
    }

    @Override
    public void progress(Object... messageArray) {
        getProgressSink().p(messageArray);
    }

    @Override
    public void progress(Throwable e, Object... messageArray) {
        getProgressSink().p(e, messageArray);
    }

    @Override
    public void _progress(int delta, Object... messageArray) {
        getProgressSink(delta).p(messageArray);
    }

    @Override
    public void _progress(int delta, Throwable e, Object... messageArray) {
        getProgressSink(delta).p(e, messageArray);
    }

    @Override
    public void progressFormat(String format, Object... args) {
        getProgressSink().f(format, args);
    }

    @Override
    public void progressFormat(Throwable e, String format, Object... args) {
        getProgressSink().f(e, format, args);
    }

    @Override
    public void _progressFormat(int delta, String format, Object... args) {
        getProgressSink(delta).f(format, args);
    }

    @Override
    public void _progressFormat(int delta, Throwable e, String format, Object... args) {
        getProgressSink(delta).f(e, format, args);
    }

}
