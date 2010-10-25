package net.bodz.bas.log.impl;

import static net.bodz.bas.log.LogCategory.DEBUG_ID;
import static net.bodz.bas.log.LogCategory.ERROR_ID;
import static net.bodz.bas.log.LogCategory.INFO_ID;
import net.bodz.bas.log.AbstractLogComposite;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogCategory;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.impl.JCLLogSink.DebugSink;
import net.bodz.bas.log.impl.JCLLogSink.ErrorSink;
import net.bodz.bas.log.impl.JCLLogSink.FatalSink;
import net.bodz.bas.log.impl.JCLLogSink.InfoSink;
import net.bodz.bas.log.impl.JCLLogSink.TraceSink;
import net.bodz.bas.log.impl.JCLLogSink.WarnSink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

public class JCLLogComposite
        extends AbstractLogComposite {

    private final Log jclLog;

    public JCLLogComposite(Log jclLog) {
        if (jclLog == null)
            throw new NullPointerException("jclLog");
        this.jclLog = jclLog;
    }

    @Override
    public ILogSink get(LogCategory category, int verboseLevel) {
        switch (category.getId()) {
        case ERROR_ID:
            if (verboseLevel <= LEVEL_HIGH) { // (..., HIGH]
                if (jclLog.isFatalEnabled())
                    return new FatalSink(jclLog);
            } else if (verboseLevel < LEVEL_LOW) { // (HIGH, LOW)
                if (jclLog.isErrorEnabled())
                    return new ErrorSink(jclLog); // [LOW, ...)
            } else if (jclLog.isWarnEnabled())
                return new WarnSink(jclLog);
            break;

        case INFO_ID:
            if (jclLog.isInfoEnabled())
                return new InfoSink(jclLog);
            break;

        case DEBUG_ID:
            if (verboseLevel < LEVEL_LOW) {
                if (jclLog.isDebugEnabled())
                    return new DebugSink(jclLog);
            } else if (jclLog.isTraceEnabled())
                return new TraceSink(jclLog);
            break;

        default:
            return super.get(category, verboseLevel);
        }

        return NullLogSink.getInstance();
    }

    @Override
    public void fatal(Object message) {
        jclLog.fatal(message);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        jclLog.fatal(message, t);
    }

    @Override
    public void error(Object message) {
        jclLog.error(message);
    }

    @Override
    public void error(Object message, Throwable t) {
        jclLog.error(message, t);
    }

    @Override
    public void warn(Object message) {
        jclLog.warn(message);
    }

    @Override
    public void warn(Object message, Throwable t) {
        jclLog.warn(message, t);
    }

    @Override
    public void info(Object message) {
        jclLog.info(message);
    }

    @Override
    public void info(Object message, Throwable t) {
        jclLog.info(message, t);
    }

    @Override
    public void debug(Object message) {
        jclLog.debug(message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        jclLog.debug(message, t);
    }

    /**
     * @throws LogConfigurationException
     */
    public static JCLLogComposite getInstance(Class<?> clazz) {
        return new JCLLogComposite(LogFactory.getLog(clazz));
    }

    /**
     * @throws LogConfigurationException
     */
    public static JCLLogComposite getInstance(String name) {
        return new JCLLogComposite(LogFactory.getLog(name));
    }

}
