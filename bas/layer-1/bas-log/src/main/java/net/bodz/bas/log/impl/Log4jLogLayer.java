package net.bodz.bas.log.impl;

import static net.bodz.bas.log.LogCategory.DEBUG_ID;
import static net.bodz.bas.log.LogCategory.ERROR_ID;
import static net.bodz.bas.log.LogCategory.INFO_ID;
import net.bodz.bas.log.AbstractLogComposite;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogCategory;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.impl.Log4jLogSink.DebugSink;
import net.bodz.bas.log.impl.Log4jLogSink.ErrorSink;
import net.bodz.bas.log.impl.Log4jLogSink.FatalSink;
import net.bodz.bas.log.impl.Log4jLogSink.InfoSink;
import net.bodz.bas.log.impl.Log4jLogSink.TraceSink;
import net.bodz.bas.log.impl.Log4jLogSink.WarnSink;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jLogLayer
        extends AbstractLogComposite {

    private final Logger log4j;

    public Log4jLogLayer(Logger logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        this.log4j = logger;
    }

    @Override
    public ILogSink get(LogCategory category, int verboseLevel) {
        switch (category.getId()) {
        case ERROR_ID:
            if (verboseLevel <= LEVEL_HIGH) {
                if (log4j.isEnabledFor(Level.FATAL))
                    return new FatalSink(log4j);
            } else if (verboseLevel < LEVEL_LOW) {
                if (log4j.isEnabledFor(Level.ERROR))
                    return new ErrorSink(log4j);
            } else {
                if (log4j.isEnabledFor(Level.WARN))
                    return new WarnSink(log4j);
            }
            break;

        case INFO_ID:
            if (log4j.isEnabledFor(Level.INFO))
                return new InfoSink(log4j);
            break;

        case DEBUG_ID:
            if (verboseLevel <= LEVEL_DEFAULT) {
                if (log4j.isEnabledFor(Level.DEBUG))
                    return new DebugSink(log4j);
            } else {
                if (log4j.isEnabledFor(Level.TRACE))
                    return new TraceSink(log4j);
            }
            break;

        default:
            return super.get(category, verboseLevel);
        }

        return NullLogSink.getInstance();
    }

    @Override
    public void fatal(Object message) {
        log4j.fatal(message);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        log4j.fatal(message, t);
    }

    @Override
    public void error(Object message) {
        log4j.error(message);
    }

    @Override
    public void error(Object message, Throwable t) {
        log4j.error(message, t);
    }

    @Override
    public void warn(Object message) {
        log4j.warn(message);
    }

    @Override
    public void warn(Object message, Throwable t) {
        log4j.warn(message, t);
    }

    @Override
    public void info(Object message) {
        log4j.info(message);
    }

    @Override
    public void info(Object message, Throwable t) {
        log4j.info(message, t);
    }

    @Override
    public void debug(Object message) {
        log4j.debug(message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        log4j.debug(message, t);
    }

    public static Log4jLogLayer getInstance(Class<?> clazz) {
        return new Log4jLogLayer(Logger.getLogger(clazz));
    }

    public static Log4jLogLayer getInstance(String name) {
        return new Log4jLogLayer(Logger.getLogger(name));
    }

}
