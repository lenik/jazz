package net.bodz.bas.log.impl;

import static net.bodz.bas.log.LogCategory.DEBUG_ID;
import static net.bodz.bas.log.LogCategory.ERROR_ID;
import static net.bodz.bas.log.LogCategory.INFO_ID;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.bodz.bas.log.AbstractLogComposite;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogCategory;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.impl.JdkLogSink.FineSink;
import net.bodz.bas.log.impl.JdkLogSink.FinerSink;
import net.bodz.bas.log.impl.JdkLogSink.FinestSink;
import net.bodz.bas.log.impl.JdkLogSink.InfoSink;
import net.bodz.bas.log.impl.JdkLogSink.SevereSink;
import net.bodz.bas.log.impl.JdkLogSink.WarningSink;

public class JdkLogComposite
        extends AbstractLogComposite {

    private final Logger jdkLogger;

    public JdkLogComposite(Logger jdkLogger) {
        if (jdkLogger == null)
            throw new NullPointerException("jdkLogger");
        this.jdkLogger = jdkLogger;
    }

    @Override
    public ILogSink get(LogCategory category, int verboseLevel) {
        switch (category.getId()) {
        case ERROR_ID:
            if (verboseLevel < LEVEL_LOW) { // (..., HIGH, ... , DEFAULT, ..., LOW)
                if (jdkLogger.isLoggable(Level.SEVERE))
                    return new SevereSink(jdkLogger);
            } else { // [LOW, ...)
                if (jdkLogger.isLoggable(Level.WARNING))
                    return new WarningSink(jdkLogger);
            }
            break;

        case INFO_ID:
            if (verboseLevel <= LEVEL_DEFAULT) {
                if (jdkLogger.isLoggable(Level.INFO))
                    return new InfoSink(jdkLogger);
            } else if (jdkLogger.isLoggable(Level.FINE))
                return new FineSink(jdkLogger);
            break;

        case DEBUG_ID:
            if (verboseLevel <= LEVEL_DEFAULT) {
                if (jdkLogger.isLoggable(Level.FINER))
                    return new FinerSink(jdkLogger);
            } else if (jdkLogger.isLoggable(Level.FINEST))
                return new FinestSink(jdkLogger);
            break;

        default:
            return super.get(category, verboseLevel);
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
    public static JdkLogComposite getInstance(Class<?> clazz) {
        return new JdkLogComposite(Logger.getLogger(clazz.getName()));
    }

    /**
     * @throws NullPointerException
     */
    public static JdkLogComposite getInstance(String name) {
        return new JdkLogComposite(Logger.getLogger(name));
    }

}
