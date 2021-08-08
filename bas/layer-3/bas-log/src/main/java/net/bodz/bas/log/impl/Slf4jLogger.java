package net.bodz.bas.log.impl;

import org.slf4j.spi.LocationAwareLogger;

import net.bodz.bas.log.AbstractLogger;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.SinkBasedLogger;
import net.bodz.bas.log.impl.Slf4jLogSink.DebugSink;
import net.bodz.bas.log.impl.Slf4jLogSink.ErrorSink;
import net.bodz.bas.log.impl.Slf4jLogSink.InfoSink;
import net.bodz.bas.log.impl.Slf4jLogSink.TraceSink;
import net.bodz.bas.log.impl.Slf4jLogSink.WarnSink;

public class Slf4jLogger
        extends SinkBasedLogger {

    private static final String FQCN = AbstractLogger.class.getName();

    private final LocationAwareLogger slf4j;

    public Slf4jLogger(LocationAwareLogger logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        this.slf4j = logger;
    }

    @Override
    public LogLevel getLevel() {
        if (slf4j.isErrorEnabled())
            return LogLevel.ERROR;
        if (slf4j.isWarnEnabled())
            return LogLevel.WARN;
        if (slf4j.isInfoEnabled())
            return LogLevel.INFO;
        if (slf4j.isDebugEnabled())
            return LogLevel.DEBUG;
        if (slf4j.isTraceEnabled())
            return LogLevel.TRACE;
        return LogLevel.OFF;
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
        slf4j.log(null, FQCN, LocationAwareLogger.ERROR_INT, //
                format(message), null, t);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable t, Object message) {
        slf4j.log(null, FQCN, LocationAwareLogger.ERROR_INT, //
                format(message), null, t);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable t, Object message) {
        slf4j.log(null, FQCN, LocationAwareLogger.WARN_INT, //
                format(message), null, t);
    }

    @Override
    public void _info(int delta, Throwable t, Object message) {
        slf4j.log(null, FQCN, LocationAwareLogger.INFO_INT, //
                format(message), null, t);
    }

    @Override
    public void _debug(int delta, Throwable t, Object message) {
        slf4j.log(null, FQCN, LocationAwareLogger.DEBUG_INT, //
                format(message), null, t);
    }

}
