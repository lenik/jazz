package net.bodz.bas.log;

import net.bodz.bas.log.impl.ConsoleLogger;

public abstract class AbstractLinearLogger
        extends AbstractLogger {

    static final ConsoleLogger consoleLogger = ConsoleLogger.getInstance();

    @Override
    public void _stderr(int delta, Throwable e, Object message) {
        consoleLogger._stderr(delta, e, message);
    }

    @Override
    public void _stdout(int delta, Throwable e, Object message) {
        consoleLogger._stdout(delta, e, message);
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        return _error(delta - 1, e, message);
    }

    @Override
    public void _mesg(int delta, Throwable e, Object message) {
        _info(delta - 1, e, message);
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        _info(delta + 1, e, message);
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        _debug(delta + 1, e, message);
    }

    @Override
    public void _progress(int delta, Throwable e, Object message) {
        _status(delta + 1, e, message);
    }

}
