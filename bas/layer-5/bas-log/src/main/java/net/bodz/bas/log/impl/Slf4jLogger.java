package net.bodz.bas.log.impl;

import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.api.AbstractLogger;
import net.bodz.bas.log.impl.Slf4jLogSink.DebugSink;
import net.bodz.bas.log.impl.Slf4jLogSink.ErrorSink;
import net.bodz.bas.log.impl.Slf4jLogSink.InfoSink;
import net.bodz.bas.log.impl.Slf4jLogSink.TraceSink;
import net.bodz.bas.log.impl.Slf4jLogSink.WarnSink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogger
        extends AbstractLogger {

    private final Logger slf4j;

    public Slf4jLogger(Logger logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        this.slf4j = logger;
    }

    @Override
    public ILogSink get(LogLevel level, int delta) {
        if (level.getGroup() != LogLevel.logGroup)
            return super.get(level, delta);

        int priority = level.getPriority() + delta;
        switch (priority) {
        case -2:
            if (slf4j.isErrorEnabled())
                return new ErrorSink(slf4j);
            break;
        case -1:
            if (slf4j.isWarnEnabled())
                return new WarnSink(slf4j);
            break;
        case 0:
        case 1:
        case 2:
            if (slf4j.isInfoEnabled())
                return new InfoSink(slf4j);
            break;
        case 3:
            if (slf4j.isDebugEnabled())
                return new DebugSink(slf4j);
            break;
        default:
            if (priority <= -3) {
                if (slf4j.isErrorEnabled())
                    return new ErrorSink(slf4j);
            } else {
                if (slf4j.isTraceEnabled())
                    return new TraceSink(slf4j);
            }
            break;
        }
        return NullLogSink.getInstance();
    }

    static String format(Object message) {
        return String.valueOf(message);
    }

    @Override
    public boolean fatal(Object message) {
        slf4j.error(format(message));
        return false;
    }

    @Override
    public boolean fatal(Object message, Throwable t) {
        slf4j.error(format(message), t);
        return false;
    }

    @Override
    public boolean error(Object message) {
        slf4j.error(format(message));
        return false;
    }

    @Override
    public boolean error(Object message, Throwable t) {
        slf4j.error(format(message), t);
        return false;
    }

    @Override
    public void warn(Object message) {
        slf4j.warn(format(message));
    }

    @Override
    public void warn(Object message, Throwable t) {
        slf4j.warn(format(message), t);
    }

    @Override
    public void info(Object message) {
        slf4j.info(format(message));
    }

    @Override
    public void info(Object message, Throwable t) {
        slf4j.info(format(message), t);
    }

    @Override
    public void debug(Object message) {
        slf4j.debug(format(message));
    }

    @Override
    public void debug(Object message, Throwable t) {
        slf4j.debug(format(message), t);
    }

    public static Slf4jLogger getInstance(Class<?> clazz) {
        return new Slf4jLogger(LoggerFactory.getLogger(clazz));
    }

    public static Slf4jLogger getInstance(String name) {
        return new Slf4jLogger(LoggerFactory.getLogger(name));
    }

}
