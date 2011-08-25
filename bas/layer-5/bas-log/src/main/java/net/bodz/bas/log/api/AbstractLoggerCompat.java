package net.bodz.bas.log.api;

import net.bodz.bas.log.AbstractLogComposite;
import net.bodz.bas.log.LogLevel;

public class AbstractLoggerCompat
        extends AbstractLogComposite
        implements LoggerCompat {

    // Log4j style shortcuts

    @Override
    public boolean fatal(Object message) {
        get(LogLevel.FATAL).p(message);
        return false;
    }

    @Override
    public boolean fatal(Object message, Throwable t) {
        get(LogLevel.FATAL).p(t, message);
        return false;
    }

    @Override
    public boolean error(Object message) {
        get(LogLevel.ERROR).p(message);
        return false;
    }

    @Override
    public boolean error(Object message, Throwable t) {
        get(LogLevel.ERROR).p(t, message);
        return false;
    }

    @Override
    public void warn(Object message) {
        get(LogLevel.WARN).p(message);
    }

    @Override
    public void warn(Object message, Throwable t) {
        get(LogLevel.WARN).p(t, message);
    }

    @Override
    public void info(Object message) {
        get(LogLevel.INFO).p(message);
    }

    @Override
    public void info(Object message, Throwable t) {
        get(LogLevel.INFO).p(t, message);
    }

    @Override
    public void debug(Object message) {
        get(LogLevel.DEBUG).p(message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        get(LogLevel.DEBUG).p(t, message);
    }

}
