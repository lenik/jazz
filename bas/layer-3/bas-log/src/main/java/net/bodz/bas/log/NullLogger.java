package net.bodz.bas.log;

class NullLogger
        implements Logger {

    @Override
    public boolean fatal(Object message, Throwable t) {
        return false;
    }

    @Override
    public boolean error(Object message, Throwable t) {
        return false;
    }

    @Override
    public void warn(Object message, Throwable t) {
    }

    @Override
    public void info(Object message, Throwable t) {
    }

    @Override
    public void debug(Object message, Throwable t) {
    }

    @Override
    public boolean isStderrEnabled() {
        return false;
    }

    @Override
    public boolean isStderrEnabled(int delta) {
        return false;
    }

    @Override
    public void stderr(Object message) {
    }

    @Override
    public void stderr(Throwable e, Object message) {
    }

    @Override
    public void _stderr(int delta, Object message) {
    }

    @Override
    public void _stderr(int delta, Throwable e, Object message) {
    }

    @Override
    public void stderr(Object... messageArray) {
    }

    @Override
    public void stderr(Throwable e, Object... messageArray) {
    }

    @Override
    public void _stderr(int delta, Object... messageArray) {
    }

    @Override
    public void _stderr(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void stderrf(String format, Object... args) {
    }

    @Override
    public void stderrf(Throwable e, String format, Object... args) {
    }

    @Override
    public void _stderrf(int delta, String format, Object... args) {
    }

    @Override
    public void _stderrf(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isStdoutEnabled() {
        return false;
    }

    @Override
    public boolean isStdoutEnabled(int delta) {
        return false;
    }

    @Override
    public void stdout(Object message) {
    }

    @Override
    public void stdout(Throwable e, Object message) {
    }

    @Override
    public void _stdout(int delta, Object message) {
    }

    @Override
    public void _stdout(int delta, Throwable e, Object message) {
    }

    @Override
    public void stdout(Object... messageArray) {
    }

    @Override
    public void stdout(Throwable e, Object... messageArray) {
    }

    @Override
    public void _stdout(int delta, Object... messageArray) {
    }

    @Override
    public void _stdout(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void stdoutf(String format, Object... args) {
    }

    @Override
    public void stdoutf(Throwable e, String format, Object... args) {
    }

    @Override
    public void _stdoutf(int delta, String format, Object... args) {
    }

    @Override
    public void _stdoutf(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isFatalEnabled() {
        return false;
    }

    @Override
    public boolean isFatalEnabled(int delta) {
        return false;
    }

    @Override
    public boolean fatal(Object message) {
        return false;
    }

    @Override
    public boolean fatal(Throwable e, Object message) {
        return false;
    }

    @Override
    public boolean _fatal(int delta, Object message) {
        return false;
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        return false;
    }

    @Override
    public boolean fatal(Object... messageArray) {
        return false;
    }

    @Override
    public boolean fatal(Throwable e, Object... messageArray) {
        return false;
    }

    @Override
    public boolean _fatal(int delta, Object... messageArray) {
        return false;
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object... messageArray) {
        return false;
    }

    @Override
    public boolean fatalf(String format, Object... args) {
        return false;
    }

    @Override
    public boolean fatalf(Throwable e, String format, Object... args) {
        return false;
    }

    @Override
    public boolean _fatalf(int delta, String format, Object... args) {
        return false;
    }

    @Override
    public boolean _fatalf(int delta, Throwable e, String format, Object... args) {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled(int delta) {
        return false;
    }

    @Override
    public boolean error(Object message) {
        return false;
    }

    @Override
    public boolean error(Throwable e, Object message) {
        return false;
    }

    @Override
    public boolean _error(int delta, Object message) {
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        return false;
    }

    @Override
    public boolean error(Object... messageArray) {
        return false;
    }

    @Override
    public boolean error(Throwable e, Object... messageArray) {
        return false;
    }

    @Override
    public boolean _error(int delta, Object... messageArray) {
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable e, Object... messageArray) {
        return false;
    }

    @Override
    public boolean errorf(String format, Object... args) {
        return false;
    }

    @Override
    public boolean errorf(Throwable e, String format, Object... args) {
        return false;
    }

    @Override
    public boolean _errorf(int delta, String format, Object... args) {
        return false;
    }

    @Override
    public boolean _errorf(int delta, Throwable e, String format, Object... args) {
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public boolean isWarnEnabled(int delta) {
        return false;
    }

    @Override
    public void warn(Object message) {
    }

    @Override
    public void warn(Throwable e, Object message) {
    }

    @Override
    public void _warn(int delta, Object message) {
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
    }

    @Override
    public void warn(Object... messageArray) {
    }

    @Override
    public void warn(Throwable e, Object... messageArray) {
    }

    @Override
    public void _warn(int delta, Object... messageArray) {
    }

    @Override
    public void _warn(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void warnf(String format, Object... args) {
    }

    @Override
    public void warnf(Throwable e, String format, Object... args) {
    }

    @Override
    public void _warnf(int delta, String format, Object... args) {
    }

    @Override
    public void _warnf(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isMesgEnabled() {
        return false;
    }

    @Override
    public boolean isMesgEnabled(int delta) {
        return false;
    }

    @Override
    public void mesg(Object message) {
    }

    @Override
    public void mesg(Throwable e, Object message) {
    }

    @Override
    public void _mesg(int delta, Object message) {
    }

    @Override
    public void _mesg(int delta, Throwable e, Object message) {
    }

    @Override
    public void mesg(Object... messageArray) {
    }

    @Override
    public void mesg(Throwable e, Object... messageArray) {
    }

    @Override
    public void _mesg(int delta, Object... messageArray) {
    }

    @Override
    public void _mesg(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void mesgf(String format, Object... args) {
    }

    @Override
    public void mesgf(Throwable e, String format, Object... args) {
    }

    @Override
    public void _mesgf(int delta, String format, Object... args) {
    }

    @Override
    public void _mesgf(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled(int delta) {
        return false;
    }

    @Override
    public void info(Object message) {
    }

    @Override
    public void info(Throwable e, Object message) {
    }

    @Override
    public void _info(int delta, Object message) {
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
    }

    @Override
    public void info(Object... messageArray) {
    }

    @Override
    public void info(Throwable e, Object... messageArray) {
    }

    @Override
    public void _info(int delta, Object... messageArray) {
    }

    @Override
    public void _info(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void infof(String format, Object... args) {
    }

    @Override
    public void infof(Throwable e, String format, Object... args) {
    }

    @Override
    public void _infof(int delta, String format, Object... args) {
    }

    @Override
    public void _infof(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isLogEnabled() {
        return false;
    }

    @Override
    public boolean isLogEnabled(int delta) {
        return false;
    }

    @Override
    public void log(Object message) {
    }

    @Override
    public void log(Throwable e, Object message) {
    }

    @Override
    public void _log(int delta, Object message) {
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
    }

    @Override
    public void log(Object... messageArray) {
    }

    @Override
    public void log(Throwable e, Object... messageArray) {
    }

    @Override
    public void _log(int delta, Object... messageArray) {
    }

    @Override
    public void _log(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void logf(String format, Object... args) {
    }

    @Override
    public void logf(Throwable e, String format, Object... args) {
    }

    @Override
    public void _logf(int delta, String format, Object... args) {
    }

    @Override
    public void _logf(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isDebugEnabled(int delta) {
        return false;
    }

    @Override
    public void debug(Object message) {
    }

    @Override
    public void debug(Throwable e, Object message) {
    }

    @Override
    public void _debug(int delta, Object message) {
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
    }

    @Override
    public void debug(Object... messageArray) {
    }

    @Override
    public void debug(Throwable e, Object... messageArray) {
    }

    @Override
    public void _debug(int delta, Object... messageArray) {
    }

    @Override
    public void _debug(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void debugf(String format, Object... args) {
    }

    @Override
    public void debugf(Throwable e, String format, Object... args) {
    }

    @Override
    public void _debugf(int delta, String format, Object... args) {
    }

    @Override
    public void _debugf(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled(int delta) {
        return false;
    }

    @Override
    public void trace(Object message) {
    }

    @Override
    public void trace(Throwable e, Object message) {
    }

    @Override
    public void _trace(int delta, Object message) {
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
    }

    @Override
    public void trace(Object... messageArray) {
    }

    @Override
    public void trace(Throwable e, Object... messageArray) {
    }

    @Override
    public void _trace(int delta, Object... messageArray) {
    }

    @Override
    public void _trace(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void tracef(String format, Object... args) {
    }

    @Override
    public void tracef(Throwable e, String format, Object... args) {
    }

    @Override
    public void _tracef(int delta, String format, Object... args) {
    }

    @Override
    public void _tracef(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isStatusEnabled() {
        return false;
    }

    @Override
    public boolean isStatusEnabled(int delta) {
        return false;
    }

    @Override
    public void status(Object message) {
    }

    @Override
    public void status(Throwable e, Object message) {
    }

    @Override
    public void _status(int delta, Object message) {
    }

    @Override
    public void _status(int delta, Throwable e, Object message) {
    }

    @Override
    public void status(Object... messageArray) {
    }

    @Override
    public void status(Throwable e, Object... messageArray) {
    }

    @Override
    public void _status(int delta, Object... messageArray) {
    }

    @Override
    public void _status(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void statusf(String format, Object... args) {
    }

    @Override
    public void statusf(Throwable e, String format, Object... args) {
    }

    @Override
    public void _statusf(int delta, String format, Object... args) {
    }

    @Override
    public void _statusf(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public boolean isProgressEnabled() {
        return false;
    }

    @Override
    public boolean isProgressEnabled(int delta) {
        return false;
    }

    @Override
    public void progress(Object message) {
    }

    @Override
    public void progress(Throwable e, Object message) {
    }

    @Override
    public void _progress(int delta, Object message) {
    }

    @Override
    public void _progress(int delta, Throwable e, Object message) {
    }

    @Override
    public void progress(Object... messageArray) {
    }

    @Override
    public void progress(Throwable e, Object... messageArray) {
    }

    @Override
    public void _progress(int delta, Object... messageArray) {
    }

    @Override
    public void _progress(int delta, Throwable e, Object... messageArray) {
    }

    @Override
    public void progressf(String format, Object... args) {
    }

    @Override
    public void progressf(Throwable e, String format, Object... args) {
    }

    @Override
    public void _progressf(int delta, String format, Object... args) {
    }

    @Override
    public void _progressf(int delta, Throwable e, String format, Object... args) {
    }

    @Override
    public LogLevel getLevel() {
        return LogLevel.FATAL;
    }

    @Override
    public void setLevel(LogLevel level) {
    }

    @Override
    public int getDelta() {
        return 0;
    }

    @Override
    public void setDelta(int delta) {
    }

    @Override
    public void setLevel(LogLevel logLevel, int delta) {
    }

    @Override
    public ILogSink get(LogLevel level) {
        return null;
    }

    @Override
    public ILogSink get(LogLevel level, int delta) {
        return null;
    }

    @Override
    public boolean isEnabled(LogLevel level) {
        return false;
    }

    @Override
    public boolean isEnabled(LogLevel level, int delta) {
        return false;
    }

    @Override
    public ILogSink getStderrSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getStderrSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getStdoutSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getStdoutSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getFatalSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getFatalSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getErrorSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getErrorSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getWarnSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getWarnSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getMesgSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getMesgSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getInfoSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getInfoSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getLogSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getLogSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getDebugSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getDebugSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getTraceSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getTraceSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getStatusSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getStatusSink(int delta) {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getProgressSink() {
        return ILogSink.NULL;
    }

    @Override
    public ILogSink getProgressSink(int delta) {
        return ILogSink.NULL;
    }

}