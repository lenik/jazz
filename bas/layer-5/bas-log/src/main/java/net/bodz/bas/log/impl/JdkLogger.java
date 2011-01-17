package net.bodz.bas.log.impl;

import static net.bodz.bas.log.LogLevel.DEBUG_ID;
import static net.bodz.bas.log.LogLevel.ERROR_ID;
import static net.bodz.bas.log.LogLevel.INFO_ID;

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
    public ILogSink get(LogLevel category, int actualLevel) {
        switch (category.getId()) {
        case ERROR_ID:
            if (actualLevel < LEVEL_LOW) { // (..., HIGH, ... , DEFAULT, ..., LOW)
                if (jdkLogger.isLoggable(Level.SEVERE))
                    return new SevereSink(jdkLogger);
            } else { // [LOW, ...)
                if (jdkLogger.isLoggable(Level.WARNING))
                    return new WarningSink(jdkLogger);
            }
            break;

        case INFO_ID:
            if (actualLevel <= LEVEL_DEFAULT) {
                if (jdkLogger.isLoggable(Level.INFO))
                    return new InfoSink(jdkLogger);
            } else if (jdkLogger.isLoggable(Level.FINE))
                return new FineSink(jdkLogger);
            break;

        case DEBUG_ID:
            if (actualLevel <= LEVEL_DEFAULT) {
                if (jdkLogger.isLoggable(Level.FINER))
                    return new FinerSink(jdkLogger);
            } else if (jdkLogger.isLoggable(Level.FINEST))
                return new FinestSink(jdkLogger);
            break;

        default:
            return super.get(category, actualLevel);
        }

        return NullLogSink.getInstance();
    }

    @Override
    public void fatal(Object message) {
        jdkLogger.severe(String.valueOf(message));
    }

    @Override
    public void fatal(Object message, Throwable t) {
        jdkLogger.log(Level.SEVERE, String.valueOf(message), t);
    }

    @Override
    public void error(Object message) {
        jdkLogger.severe(String.valueOf(message));
    }

    @Override
    public void error(Object message, Throwable t) {
        jdkLogger.log(Level.SEVERE, String.valueOf(message), t);
    }

    @Override
    public void warn(Object message) {
        jdkLogger.warning(String.valueOf(message));
    }

    @Override
    public void warn(Object message, Throwable t) {
        jdkLogger.log(Level.WARNING, String.valueOf(message), t);
    }

    @Override
    public void info(Object message) {
        jdkLogger.info(String.valueOf(message));
    }

    @Override
    public void info(Object message, Throwable t) {
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
    public void debug(Object message) {
        jdkLogger.finer(String.valueOf(message));
    }

    @Override
    public void debug(Object message, Throwable t) {
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
