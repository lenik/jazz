package net.bodz.bas.log.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.bodz.bas.log.AbstractLogger;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.impl.JdkLogSink.FineSink;
import net.bodz.bas.log.impl.JdkLogSink.FinerSink;
import net.bodz.bas.log.impl.JdkLogSink.FinestSink;
import net.bodz.bas.log.impl.JdkLogSink.InfoSink;
import net.bodz.bas.log.impl.JdkLogSink.SevereSink;
import net.bodz.bas.log.impl.JdkLogSink.WarningSink;

public class JdkLogger
        extends AbstractLogger {

    private final Logger jdkLogger;

    public JdkLogger(Logger jdkLogger) {
        if (jdkLogger == null)
            throw new NullPointerException("jdkLogger");
        this.jdkLogger = jdkLogger;
    }

    @Override
    public ILogSink get(LogLevel level, int delta) {
        if (level.getGroup() != LogLevel.logGroup)
            return super.get(level, delta);

        int priority = level.getPriority() + delta;
        switch (priority) {
        case -1:
            if (jdkLogger.isLoggable(Level.WARNING))
                return new WarningSink(jdkLogger);
            break;
        case 0:
            if (jdkLogger.isLoggable(Level.INFO))
                return new InfoSink(jdkLogger);
            break;
        case 1:
            if (jdkLogger.isLoggable(Level.FINE))
                return new FineSink(jdkLogger);
            break;
        case 2:
            if (jdkLogger.isLoggable(Level.FINER))
                return new FinerSink(jdkLogger);
            break;
        case 3:
            if (jdkLogger.isLoggable(Level.FINEST))
                return new FinestSink(jdkLogger);
            break;
        default:
            if (priority <= -2) {
                if (jdkLogger.isLoggable(Level.SEVERE))
                    return new SevereSink(jdkLogger);
            } else {
                if (jdkLogger.isLoggable(Level.ALL))
                    return new FinestSink(jdkLogger);
            }
            break;
        }
        return NullLogSink.getInstance();
    }

    @Override
    public boolean _fatal(int delta, Throwable t, Object message) {
        jdkLogger.log(Level.SEVERE, String.valueOf(message), t);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable t, Object message) {
        jdkLogger.log(Level.SEVERE, String.valueOf(message), t);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable t, Object message) {
        jdkLogger.log(Level.WARNING, String.valueOf(message), t);
    }

    @Override
    public void _info(int delta, Throwable t, Object message) {
        jdkLogger.log(Level.INFO, String.valueOf(message), t);
    }

    /** Unmapped */
    public void config(Object message) {
        jdkLogger.config(String.valueOf(message));
    }

    /** Unmapped */
    public void config(Object message, Throwable t) {
        jdkLogger.log(Level.CONFIG, String.valueOf(message), t);
    }

    @Override
    public void _debug(int delta, Throwable t, Object message) {
        jdkLogger.log(Level.FINER, String.valueOf(message), t);
    }

    /**
     * @throws NullPointerException
     */
    public static JdkLogger getInstance(Class<?> clazz) {
        return new JdkLogger(Logger.getLogger(clazz.getName()));
    }

    /**
     * @throws NullPointerException
     */
    public static JdkLogger getInstance(String name) {
        return new JdkLogger(Logger.getLogger(name));
    }

}
