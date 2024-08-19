package net.bodz.bas.log;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedLogger
        extends AbstractDecorator<Logger>
        implements
            ILogger {

    private static final long serialVersionUID = 1L;

    public DecoratedLogger(Logger _orig) {
        super(_orig);
    }

    @Override
    public boolean isStderrEnabled() {
        return getWrapped().isStderrEnabled();
    }

    @Override
    public void stderr(Object message) {
        getWrapped().stderr(message);
    }

    @Override
    public boolean isStderrEnabled(int delta) {
        return getWrapped().isStderrEnabled(delta);
    }

    @Override
    public void stderr(Throwable e, Object message) {
        getWrapped().stderr(e, message);
    }

    @Override
    public void _stderr(int delta, Throwable e, Object message) {
        getWrapped()._stderr(delta, e, message);
    }

    @Override
    public void _stderr(int delta, Object message) {
        getWrapped()._stderr(delta, message);
    }

    @Override
    public boolean isStdoutEnabled() {
        return getWrapped().isStdoutEnabled();
    }

    @Override
    public void stderr(Object... messageArray) {
        getWrapped().stderr(messageArray);
    }

    @Override
    public boolean isStdoutEnabled(int delta) {
        return getWrapped().isStdoutEnabled(delta);
    }

    @Override
    public void stderr(Throwable e, Object... messageArray) {
        getWrapped().stderr(e, messageArray);
    }

    @Override
    public void _stdout(int delta, Throwable e, Object message) {
        getWrapped()._stdout(delta, e, message);
    }

    @Override
    public void _stderr(int delta, Object... messageArray) {
        getWrapped()._stderr(delta, messageArray);
    }

    @Override
    public boolean isFatalEnabled() {
        return getWrapped().isFatalEnabled();
    }

    @Override
    public boolean isFatalEnabled(int delta) {
        return getWrapped().isFatalEnabled(delta);
    }

    @Override
    public void _stderr(int delta, Throwable e, Object... messageArray) {
        getWrapped()._stderr(delta, e, messageArray);
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        return getWrapped()._fatal(delta, e, message);
    }

    @Override
    public void stderrf(String format, Object... args) {
        getWrapped().stderrf(format, args);
    }

    @Override
    public boolean isErrorEnabled() {
        return getWrapped().isErrorEnabled();
    }

    @Override
    public boolean isErrorEnabled(int delta) {
        return getWrapped().isErrorEnabled(delta);
    }

    @Override
    public void stderrf(Throwable e, String format, Object... args) {
        getWrapped().stderrf(e, format, args);
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        return getWrapped()._error(delta, e, message);
    }

    @Override
    public void _stderrf(int delta, String format, Object... args) {
        getWrapped()._stderrf(delta, format, args);
    }

    @Override
    public boolean isWarnEnabled() {
        return getWrapped().isWarnEnabled();
    }

    @Override
    public void _stderrf(int delta, Throwable e, String format, Object... args) {
        getWrapped()._stderrf(delta, e, format, args);
    }

    @Override
    public boolean isWarnEnabled(int delta) {
        return getWrapped().isWarnEnabled(delta);
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        getWrapped()._warn(delta, e, message);
    }

    @Override
    public void stdout(Object message) {
        getWrapped().stdout(message);
    }

    @Override
    public boolean isMesgEnabled() {
        return getWrapped().isMesgEnabled();
    }

    @Override
    public void stdout(Throwable e, Object message) {
        getWrapped().stdout(e, message);
    }

    @Override
    public boolean isMesgEnabled(int delta) {
        return getWrapped().isMesgEnabled(delta);
    }

    @Override
    public void _stdout(int delta, Object message) {
        getWrapped()._stdout(delta, message);
    }

    @Override
    public void _mesg(int delta, Throwable e, Object message) {
        getWrapped()._mesg(delta, e, message);
    }

    @Override
    public void stdout(Object... messageArray) {
        getWrapped().stdout(messageArray);
    }

    @Override
    public boolean isInfoEnabled() {
        return getWrapped().isInfoEnabled();
    }

    @Override
    public boolean isInfoEnabled(int delta) {
        return getWrapped().isInfoEnabled(delta);
    }

    @Override
    public void stdout(Throwable e, Object... messageArray) {
        getWrapped().stdout(e, messageArray);
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        getWrapped()._info(delta, e, message);
    }

    @Override
    public void _stdout(int delta, Object... messageArray) {
        getWrapped()._stdout(delta, messageArray);
    }

    @Override
    public boolean isLogEnabled() {
        return getWrapped().isLogEnabled();
    }

    @Override
    public boolean isLogEnabled(int delta) {
        return getWrapped().isLogEnabled(delta);
    }

    @Override
    public void _stdout(int delta, Throwable e, Object... messageArray) {
        getWrapped()._stdout(delta, e, messageArray);
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        getWrapped()._log(delta, e, message);
    }

    @Override
    public void stdoutf(String format, Object... args) {
        getWrapped().stdoutf(format, args);
    }

    @Override
    public boolean isDebugEnabled() {
        return getWrapped().isDebugEnabled();
    }

    @Override
    public boolean isDebugEnabled(int delta) {
        return getWrapped().isDebugEnabled(delta);
    }

    @Override
    public void stdoutf(Throwable e, String format, Object... args) {
        getWrapped().stdoutf(e, format, args);
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        getWrapped()._debug(delta, e, message);
    }

    @Override
    public void _stdoutf(int delta, String format, Object... args) {
        getWrapped()._stdoutf(delta, format, args);
    }

    @Override
    public boolean isTraceEnabled() {
        return getWrapped().isTraceEnabled();
    }

    @Override
    public boolean isTraceEnabled(int delta) {
        return getWrapped().isTraceEnabled(delta);
    }

    @Override
    public void _stdoutf(int delta, Throwable e, String format, Object... args) {
        getWrapped()._stdoutf(delta, e, format, args);
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        getWrapped()._trace(delta, e, message);
    }

    @Override
    public boolean isStatusEnabled() {
        return getWrapped().isStatusEnabled();
    }

    @Override
    public boolean isStatusEnabled(int delta) {
        return getWrapped().isStatusEnabled(delta);
    }

    @Override
    public boolean fatal(Object message) {
        return getWrapped().fatal(message);
    }

    @Override
    public void _status(int delta, Throwable e, Object message) {
        getWrapped()._status(delta, e, message);
    }

    @Override
    public boolean fatal(Throwable e, Object message) {
        return getWrapped().fatal(e, message);
    }

    @Override
    public boolean isProgressEnabled() {
        return getWrapped().isProgressEnabled();
    }

    @Override
    public boolean isProgressEnabled(int delta) {
        return getWrapped().isProgressEnabled(delta);
    }

    @Override
    public boolean _fatal(int delta, Object message) {
        return getWrapped()._fatal(delta, message);
    }

    @Override
    public void _progress(int delta, Throwable e, Object message) {
        getWrapped()._progress(delta, e, message);
    }

    @Override
    public boolean fatal(Object... messageArray) {
        return getWrapped().fatal(messageArray);
    }

    @Override
    public boolean fatal(Throwable e, Object... messageArray) {
        return getWrapped().fatal(e, messageArray);
    }

    @Override
    public boolean _fatal(int delta, Object... messageArray) {
        return getWrapped()._fatal(delta, messageArray);
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object... messageArray) {
        return getWrapped()._fatal(delta, e, messageArray);
    }

    @Override
    public boolean fatalf(String format, Object... args) {
        return getWrapped().fatalf(format, args);
    }

    @Override
    public boolean fatalf(Throwable e, String format, Object... args) {
        return getWrapped().fatalf(e, format, args);
    }

    @Override
    public boolean _fatalf(int delta, String format, Object... args) {
        return getWrapped()._fatalf(delta, format, args);
    }

    @Override
    public boolean _fatalf(int delta, Throwable e, String format, Object... args) {
        return getWrapped()._fatalf(delta, e, format, args);
    }

    @Override
    public boolean error(Object message) {
        return getWrapped().error(message);
    }

    @Override
    public boolean error(Throwable e, Object message) {
        return getWrapped().error(e, message);
    }

    @Override
    public boolean _error(int delta, Object message) {
        return getWrapped()._error(delta, message);
    }

    @Override
    public boolean error(Object... messageArray) {
        return getWrapped().error(messageArray);
    }

    @Override
    public boolean error(Throwable e, Object... messageArray) {
        return getWrapped().error(e, messageArray);
    }

    @Override
    public boolean _error(int delta, Object... messageArray) {
        return getWrapped()._error(delta, messageArray);
    }

    @Override
    public boolean _error(int delta, Throwable e, Object... messageArray) {
        return getWrapped()._error(delta, e, messageArray);
    }

    @Override
    public boolean errorf(String format, Object... args) {
        return getWrapped().errorf(format, args);
    }

    @Override
    public boolean errorf(Throwable e, String format, Object... args) {
        return getWrapped().errorf(e, format, args);
    }

    @Override
    public boolean _errorf(int delta, String format, Object... args) {
        return getWrapped()._errorf(delta, format, args);
    }

    @Override
    public boolean _errorf(int delta, Throwable e, String format, Object... args) {
        return getWrapped()._errorf(delta, e, format, args);
    }

    @Override
    public void warn(Object message) {
        getWrapped().warn(message);
    }

    @Override
    public void warn(Throwable e, Object message) {
        getWrapped().warn(e, message);
    }

    @Override
    public void _warn(int delta, Object message) {
        getWrapped()._warn(delta, message);
    }

    @Override
    public void warn(Object... messageArray) {
        getWrapped().warn(messageArray);
    }

    @Override
    public void warn(Throwable e, Object... messageArray) {
        getWrapped().warn(e, messageArray);
    }

    @Override
    public void _warn(int delta, Object... messageArray) {
        getWrapped()._warn(delta, messageArray);
    }

    @Override
    public void _warn(int delta, Throwable e, Object... messageArray) {
        getWrapped()._warn(delta, e, messageArray);
    }

    @Override
    public void warnf(String format, Object... args) {
        getWrapped().warnf(format, args);
    }

    @Override
    public void warnf(Throwable e, String format, Object... args) {
        getWrapped().warnf(e, format, args);
    }

    @Override
    public void _warnf(int delta, String format, Object... args) {
        getWrapped()._warnf(delta, format, args);
    }

    @Override
    public void _warnf(int delta, Throwable e, String format, Object... args) {
        getWrapped()._warnf(delta, e, format, args);
    }

    @Override
    public void mesg(Object message) {
        getWrapped().mesg(message);
    }

    @Override
    public void mesg(Throwable e, Object message) {
        getWrapped().mesg(e, message);
    }

    @Override
    public void _mesg(int delta, Object message) {
        getWrapped()._mesg(delta, message);
    }

    @Override
    public void mesg(Object... messageArray) {
        getWrapped().mesg(messageArray);
    }

    @Override
    public void mesg(Throwable e, Object... messageArray) {
        getWrapped().mesg(e, messageArray);
    }

    @Override
    public void _mesg(int delta, Object... messageArray) {
        getWrapped()._mesg(delta, messageArray);
    }

    @Override
    public void _mesg(int delta, Throwable e, Object... messageArray) {
        getWrapped()._mesg(delta, e, messageArray);
    }

    @Override
    public void mesgf(String format, Object... args) {
        getWrapped().mesgf(format, args);
    }

    @Override
    public void mesgf(Throwable e, String format, Object... args) {
        getWrapped().mesgf(e, format, args);
    }

    @Override
    public void _mesgf(int delta, String format, Object... args) {
        getWrapped()._mesgf(delta, format, args);
    }

    @Override
    public void _mesgf(int delta, Throwable e, String format, Object... args) {
        getWrapped()._mesgf(delta, e, format, args);
    }

    @Override
    public void info(Object message) {
        getWrapped().info(message);
    }

    @Override
    public void info(Throwable e, Object message) {
        getWrapped().info(e, message);
    }

    @Override
    public void _info(int delta, Object message) {
        getWrapped()._info(delta, message);
    }

    @Override
    public void info(Object... messageArray) {
        getWrapped().info(messageArray);
    }

    @Override
    public void info(Throwable e, Object... messageArray) {
        getWrapped().info(e, messageArray);
    }

    @Override
    public void _info(int delta, Object... messageArray) {
        getWrapped()._info(delta, messageArray);
    }

    @Override
    public void _info(int delta, Throwable e, Object... messageArray) {
        getWrapped()._info(delta, e, messageArray);
    }

    @Override
    public void infof(String format, Object... args) {
        getWrapped().infof(format, args);
    }

    @Override
    public void infof(Throwable e, String format, Object... args) {
        getWrapped().infof(e, format, args);
    }

    @Override
    public void _infof(int delta, String format, Object... args) {
        getWrapped()._infof(delta, format, args);
    }

    @Override
    public void _infof(int delta, Throwable e, String format, Object... args) {
        getWrapped()._infof(delta, e, format, args);
    }

    @Override
    public void log(Object message) {
        getWrapped().log(message);
    }

    @Override
    public void log(Throwable e, Object message) {
        getWrapped().log(e, message);
    }

    @Override
    public void _log(int delta, Object message) {
        getWrapped()._log(delta, message);
    }

    @Override
    public void log(Object... messageArray) {
        getWrapped().log(messageArray);
    }

    @Override
    public void log(Throwable e, Object... messageArray) {
        getWrapped().log(e, messageArray);
    }

    @Override
    public void _log(int delta, Object... messageArray) {
        getWrapped()._log(delta, messageArray);
    }

    @Override
    public void _log(int delta, Throwable e, Object... messageArray) {
        getWrapped()._log(delta, e, messageArray);
    }

    @Override
    public void logf(String format, Object... args) {
        getWrapped().logf(format, args);
    }

    @Override
    public void logf(Throwable e, String format, Object... args) {
        getWrapped().logf(e, format, args);
    }

    @Override
    public void _logf(int delta, String format, Object... args) {
        getWrapped()._logf(delta, format, args);
    }

    @Override
    public void _logf(int delta, Throwable e, String format, Object... args) {
        getWrapped()._logf(delta, e, format, args);
    }

    @Override
    public void debug(Object message) {
        getWrapped().debug(message);
    }

    @Override
    public void debug(Throwable e, Object message) {
        getWrapped().debug(e, message);
    }

    @Override
    public void _debug(int delta, Object message) {
        getWrapped()._debug(delta, message);
    }

    @Override
    public void debug(Object... messageArray) {
        getWrapped().debug(messageArray);
    }

    @Override
    public void debug(Throwable e, Object... messageArray) {
        getWrapped().debug(e, messageArray);
    }

    @Override
    public void _debug(int delta, Object... messageArray) {
        getWrapped()._debug(delta, messageArray);
    }

    @Override
    public void _debug(int delta, Throwable e, Object... messageArray) {
        getWrapped()._debug(delta, e, messageArray);
    }

    @Override
    public void debugf(String format, Object... args) {
        getWrapped().debugf(format, args);
    }

    @Override
    public void debugf(Throwable e, String format, Object... args) {
        getWrapped().debugf(e, format, args);
    }

    @Override
    public void _debugf(int delta, String format, Object... args) {
        getWrapped()._debugf(delta, format, args);
    }

    @Override
    public void _debugf(int delta, Throwable e, String format, Object... args) {
        getWrapped()._debugf(delta, e, format, args);
    }

    @Override
    public void trace(Object message) {
        getWrapped().trace(message);
    }

    @Override
    public void trace(Throwable e, Object message) {
        getWrapped().trace(e, message);
    }

    @Override
    public void _trace(int delta, Object message) {
        getWrapped()._trace(delta, message);
    }

    @Override
    public void trace(Object... messageArray) {
        getWrapped().trace(messageArray);
    }

    @Override
    public void trace(Throwable e, Object... messageArray) {
        getWrapped().trace(e, messageArray);
    }

    @Override
    public void _trace(int delta, Object... messageArray) {
        getWrapped()._trace(delta, messageArray);
    }

    @Override
    public void _trace(int delta, Throwable e, Object... messageArray) {
        getWrapped()._trace(delta, e, messageArray);
    }

    @Override
    public void tracef(String format, Object... args) {
        getWrapped().tracef(format, args);
    }

    @Override
    public void tracef(Throwable e, String format, Object... args) {
        getWrapped().tracef(e, format, args);
    }

    @Override
    public void _tracef(int delta, String format, Object... args) {
        getWrapped()._tracef(delta, format, args);
    }

    @Override
    public void _tracef(int delta, Throwable e, String format, Object... args) {
        getWrapped()._tracef(delta, e, format, args);
    }

    @Override
    public void status(Object message) {
        getWrapped().status(message);
    }

    @Override
    public void status(Throwable e, Object message) {
        getWrapped().status(e, message);
    }

    @Override
    public void _status(int delta, Object message) {
        getWrapped()._status(delta, message);
    }

    @Override
    public void status(Object... messageArray) {
        getWrapped().status(messageArray);
    }

    @Override
    public void status(Throwable e, Object... messageArray) {
        getWrapped().status(e, messageArray);
    }

    @Override
    public void _status(int delta, Object... messageArray) {
        getWrapped()._status(delta, messageArray);
    }

    @Override
    public void _status(int delta, Throwable e, Object... messageArray) {
        getWrapped()._status(delta, e, messageArray);
    }

    @Override
    public void statusf(String format, Object... args) {
        getWrapped().statusf(format, args);
    }

    @Override
    public void statusf(Throwable e, String format, Object... args) {
        getWrapped().statusf(e, format, args);
    }

    @Override
    public void _statusf(int delta, String format, Object... args) {
        getWrapped()._statusf(delta, format, args);
    }

    @Override
    public void _statusf(int delta, Throwable e, String format, Object... args) {
        getWrapped()._statusf(delta, e, format, args);
    }

    @Override
    public void progress(Object message) {
        getWrapped().progress(message);
    }

    @Override
    public void progress(Throwable e, Object message) {
        getWrapped().progress(e, message);
    }

    @Override
    public void _progress(int delta, Object message) {
        getWrapped()._progress(delta, message);
    }

    @Override
    public void progress(Object... messageArray) {
        getWrapped().progress(messageArray);
    }

    @Override
    public void progress(Throwable e, Object... messageArray) {
        getWrapped().progress(e, messageArray);
    }

    @Override
    public void _progress(int delta, Object... messageArray) {
        getWrapped()._progress(delta, messageArray);
    }

    @Override
    public void _progress(int delta, Throwable e, Object... messageArray) {
        getWrapped()._progress(delta, e, messageArray);
    }

    @Override
    public void progressf(String format, Object... args) {
        getWrapped().progressf(format, args);
    }

    @Override
    public void progressf(Throwable e, String format, Object... args) {
        getWrapped().progressf(e, format, args);
    }

    @Override
    public void _progressf(int delta, String format, Object... args) {
        getWrapped()._progressf(delta, format, args);
    }

    @Override
    public void _progressf(int delta, Throwable e, String format, Object... args) {
        getWrapped()._progressf(delta, e, format, args);
    }

}
