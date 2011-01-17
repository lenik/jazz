package net.bodz.bas.log.impl;

import static net.bodz.bas.log.LogLevel.DEBUG_ID;
import static net.bodz.bas.log.LogLevel.ERROR_ID;
import static net.bodz.bas.log.LogLevel.INFO_ID;
import net.bodz.bas.log.AbstractLogger;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.impl.Log4jLogSink.DebugSink;
import net.bodz.bas.log.impl.Log4jLogSink.ErrorSink;
import net.bodz.bas.log.impl.Log4jLogSink.FatalSink;
import net.bodz.bas.log.impl.Log4jLogSink.InfoSink;
import net.bodz.bas.log.impl.Log4jLogSink.TraceSink;
import net.bodz.bas.log.impl.Log4jLogSink.WarnSink;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jLogger
        extends AbstractLogger {

    private final Logger log4j;

    public Log4jLogger(Logger logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        this.log4j = logger;
    }

    @Override
    public ILogSink get(LogLevel category, int actualLevel) {
        switch (category.getId()) {
        case ERROR_ID:
            if (actualLevel <= LEVEL_HIGH) {
                if (log4j.isEnabledFor(Level.FATAL))
                    return new FatalSink(log4j);
            } else if (actualLevel < LEVEL_LOW) {
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
            if (actualLevel <= LEVEL_DEFAULT) {
                if (log4j.isEnabledFor(Level.DEBUG))
                    return new DebugSink(log4j);
            } else {
                if (log4j.isEnabledFor(Level.TRACE))
                    return new TraceSink(log4j);
            }
            break;

        default:
            return super.get(category, actualLevel);
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

    public static Log4jLogger getInstance(Class<?> clazz) {
        return new Log4jLogger(Logger.getLogger(clazz));
    }

    public static Log4jLogger getInstance(String name) {
        return new Log4jLogger(Logger.getLogger(name));
    }

}
