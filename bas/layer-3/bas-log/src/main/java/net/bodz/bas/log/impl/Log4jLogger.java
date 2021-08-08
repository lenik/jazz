package net.bodz.bas.log.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.SinkBasedLogger;
import net.bodz.bas.log.impl.Log4jLogSink.DebugSink;
import net.bodz.bas.log.impl.Log4jLogSink.ErrorSink;
import net.bodz.bas.log.impl.Log4jLogSink.FatalSink;
import net.bodz.bas.log.impl.Log4jLogSink.InfoSink;
import net.bodz.bas.log.impl.Log4jLogSink.TraceSink;
import net.bodz.bas.log.impl.Log4jLogSink.WarnSink;

public class Log4jLogger
        extends SinkBasedLogger {

    private final Logger log4j;

    static final String fqcn = SinkBasedLogger.class.getName();

    public Log4jLogger(Logger log4j) {
        if (log4j == null)
            throw new NullPointerException("log4j");
        this.log4j = log4j;
    }

    static final Map<Level, LogLevel> map = new HashMap<>();
    static final Map<LogLevel, Level> rmap = new HashMap<>();

    static void match(Level a, LogLevel b) {
        map.put(a, b);
        rmap.put(b, a);
    }

    static {
        match(Level.OFF, LogLevel.OFF);
        match(Level.FATAL, LogLevel.FATAL);
        match(Level.ERROR, LogLevel.ERROR);
        match(Level.WARN, LogLevel.WARN);
        match(Level.INFO, LogLevel.INFO);
        match(Level.DEBUG, LogLevel.DEBUG);
        match(Level.TRACE, LogLevel.TRACE);
        match(Level.ALL, LogLevel.ALL);

        rmap.put(LogLevel.MESG, Level.INFO);
        rmap.put(LogLevel.LOG, Level.INFO);
    }

    @Override
    public LogLevel getLevel() {
        Level level = log4j.getLevel();
        LogLevel logLevel = map.get(level);
        if (logLevel != null)
            return logLevel;

        int priority = log4j.getLevel().toInt();
        // int syslog = log4j.getLevel().getSyslogEquivalent();

        // FATAL 0, ERROR 3, WARN 4, INFO 6, DEBUG 7

        if (priority >= Priority.OFF_INT)
            return LogLevel.OFF;

        if (priority >= Priority.FATAL_INT)
            return LogLevel.FATAL;

        if (priority >= Priority.ERROR_INT)
            return LogLevel.ERROR;

        if (priority >= Priority.WARN_INT)
            return LogLevel.WARN;

        if (priority >= Priority.INFO_INT)
            return LogLevel.INFO;

        if (priority >= Priority.DEBUG_INT)
            return LogLevel.DEBUG;

        return LogLevel.ALL;
    }

    @Override
    public void setLevel(LogLevel logLevel) {
        Level level = rmap.get(logLevel);
        if (level == null)
            level = Level.ALL;
        log4j.setLevel(level);
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

    private String getCallerFQCN() {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        String lastLogClass = fqcn;
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            String className = element.getClassName();
            if (!className.startsWith("net.bodz.bas.log."))
                break;
            lastLogClass = className;
        }
        return lastLogClass;
    }

    @Override
    public boolean _fatal(int delta, Throwable t, Object message) {
        if (log4j.isEnabledFor(Level.FATAL))
            log4j.log(getCallerFQCN(), Level.FATAL, message, t);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable t, Object message) {
        if (log4j.isEnabledFor(Level.ERROR))
            log4j.log(getCallerFQCN(), Level.ERROR, message, t);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable t, Object message) {
        if (log4j.isEnabledFor(Level.WARN))
            log4j.log(getCallerFQCN(), Level.WARN, message, t);
    }

    @Override
    public void _info(int delta, Throwable t, Object message) {
        if (log4j.isEnabledFor(Level.INFO))
            log4j.log(getCallerFQCN(), Level.INFO, message, t);
    }

    @Override
    public void _debug(int delta, Throwable t, Object message) {
        if (log4j.isDebugEnabled())
            log4j.log(getCallerFQCN(), Level.DEBUG, message, t);
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
