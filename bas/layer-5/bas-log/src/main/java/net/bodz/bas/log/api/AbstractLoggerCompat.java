package net.bodz.bas.log.api;

import net.bodz.bas.log.AbstractLogComposite;
import net.bodz.bas.log.LogLevel;

public class AbstractLoggerCompat
        extends AbstractLogComposite
        implements LoggerCompat {

    // Log4j style shortcuts

    @Override
    public final boolean fatal(Object message) {
        return fatal(message, null);
    }

    @Override
    public boolean fatal(Object message, Throwable t) {
        get(LogLevel.FATAL).p(t, message);
        return false;
    }

    @Override
    public final boolean error(Object message) {
        return error(message, null);
    }

    @Override
    public boolean error(Object message, Throwable t) {
        get(LogLevel.ERROR).p(t, message);
        return false;
    }

    @Override
    public final void warn(Object message) {
        warn(message, null);
    }

    @Override
    public void warn(Object message, Throwable t) {
        get(LogLevel.WARN).p(t, message);
    }

    @Override
    public final void info(Object message) {
        info(message, null);
    }

    @Override
    public void info(Object message, Throwable t) {
        get(LogLevel.INFO).p(t, message);
    }

    @Override
    public final void debug(Object message) {
        debug(message, null);
    }

    @Override
    public void debug(Object message, Throwable t) {
        get(LogLevel.DEBUG).p(t, message);
    }

}
