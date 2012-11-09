package net.bodz.bas.log.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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

public class Log4jLogger
        extends AbstractLogger {

    private final Logger log4j;

    static final String callerFQCN = AbstractLogger.class.getName();

    public Log4jLogger(Logger log4j) {
        if (log4j == null)
            throw new NullPointerException("log4j");
        this.log4j = log4j;
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
    public boolean _fatal(int delta, Throwable t, Object message) {
        log4j.log(callerFQCN, Level.FATAL, message, t);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable t, Object message) {
        log4j.log(callerFQCN, Level.ERROR, message, t);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable t, Object message) {
        log4j.log(callerFQCN, Level.WARN, message, t);
    }

    @Override
    public void _info(int delta, Throwable t, Object message) {
        log4j.log(callerFQCN, Level.INFO, message, t);
    }

    @Override
    public void _debug(int delta, Throwable t, Object message) {
        log4j.log(callerFQCN, Level.DEBUG, message, t);
    }

    public static Log4jLogger getInstance(Class<?> clazz) {
        String name = nameOf(clazz);
        return getInstance(name);
    }

    public static Log4jLogger getInstance(String name) {
        Logger log4j = Log4jMergedFactory.getLogger(name);
        return new Log4jLogger(log4j);
    }

}