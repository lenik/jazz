package net.bodz.bas.log.impl;

import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.api.AbstractLogger;
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
    public ILogSink get(LogLevel level, int delta) {
        if (level.getGroup() != LogLevel.logGroup)
            return super.get(level, delta);

        int priority = level.getPriority() + delta;
        switch (priority) {
        case -2:
            if (log4j.isEnabledFor(Level.FATAL))
                return new ErrorSink(log4j);
            break;
        case -1:
            if (log4j.isEnabledFor(Level.WARN))
                return new WarnSink(log4j);
            break;
        case 0:
        case 1:
        case 2:
            if (log4j.isInfoEnabled())
                return new InfoSink(log4j);
            break;
        case 3:
            if (log4j.isDebugEnabled())
                return new DebugSink(log4j);
            break;
        default:
            if (priority <= -3) {
                if (log4j.isEnabledFor(Level.FATAL))
                    return new FatalSink(log4j);
            } else {
                if (log4j.isTraceEnabled())
                    return new TraceSink(log4j);
            }
            break;
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
