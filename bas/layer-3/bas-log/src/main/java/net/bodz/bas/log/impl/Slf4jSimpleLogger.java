package net.bodz.bas.log.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.SinkBasedLogger;
import net.bodz.bas.log.impl.Slf4jSimpleLogSink.DebugSink;
import net.bodz.bas.log.impl.Slf4jSimpleLogSink.ErrorSink;
import net.bodz.bas.log.impl.Slf4jSimpleLogSink.InfoSink;
import net.bodz.bas.log.impl.Slf4jSimpleLogSink.TraceSink;
import net.bodz.bas.log.impl.Slf4jSimpleLogSink.WarnSink;

public class Slf4jSimpleLogger
        extends SinkBasedLogger {

    private final Logger slf4j;

    public Slf4jSimpleLogger(Logger logger) {
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
    public boolean _fatal(int delta, Throwable t, Object message) {
        slf4j.error(format(message), t);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable t, Object message) {
        slf4j.error(format(message), t);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable t, Object message) {
        slf4j.warn(format(message), t);
    }

    @Override
    public void _info(int delta, Throwable t, Object message) {
        slf4j.info(format(message), t);
    }

    @Override
    public void _debug(int delta, Throwable t, Object message) {
        slf4j.debug(format(message), t);
    }

    public static Slf4jSimpleLogger getInstance(Class<?> clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        LocationAwareLogger lal = (LocationAwareLogger) logger;
        return new Slf4jSimpleLogger(lal);
    }

    public static Slf4jSimpleLogger getInstance(String name) {
        Logger logger = LoggerFactory.getLogger(name);
        LocationAwareLogger lal = (LocationAwareLogger) logger;
        return new Slf4jSimpleLogger(lal);
    }

}
