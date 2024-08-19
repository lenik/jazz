package net.bodz.bas.log;

import java.util.ArrayList;
import java.util.List;

public class LoggerArray
        extends AbstractLogger {

    List<ILogger> loggers = new ArrayList<>();

    public LoggerArray() {
    }

    public LoggerArray(ILogger sysLogger) {
        addLogger(sysLogger);
    }

    public void addLogger(ILogger logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        loggers.add(logger);
    }

    public void removeLogger(ILogger logger) {
        loggers.remove(logger);
    }

    @Override
    public void _stderr(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._stderr(delta, e, message);
    }

    @Override
    public void _stdout(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._stdout(delta, e, message);
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._fatal(delta, e, message);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._error(delta, e, message);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._warn(delta, e, message);
    }

    @Override
    public void _mesg(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._mesg(delta, e, message);
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._info(delta, e, message);
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._log(delta, e, message);
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._debug(delta, e, message);
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._trace(delta, e, message);
    }

    @Override
    public void _status(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._status(delta, e, message);
    }

    @Override
    public void _progress(int delta, Throwable e, Object message) {
        for (ILogger l : loggers)
            l._progress(delta, e, message);
    }

}
