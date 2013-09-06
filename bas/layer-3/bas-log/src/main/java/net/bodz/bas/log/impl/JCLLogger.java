package net.bodz.bas.log.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

import net.bodz.bas.log.AbstractLogger;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.impl.JCLLogSink.DebugSink;
import net.bodz.bas.log.impl.JCLLogSink.ErrorSink;
import net.bodz.bas.log.impl.JCLLogSink.FatalSink;
import net.bodz.bas.log.impl.JCLLogSink.InfoSink;
import net.bodz.bas.log.impl.JCLLogSink.TraceSink;
import net.bodz.bas.log.impl.JCLLogSink.WarnSink;

public class JCLLogger
        extends AbstractLogger {

    private final Log jclLog;

    public JCLLogger(Log jclLog) {
        if (jclLog == null)
            throw new NullPointerException("jclLog");
        this.jclLog = jclLog;
    }

    @Override
    public ILogSink get(LogLevel level, int delta) {
        if (level.getGroup() != LogLevel.logGroup)
            return super.get(level, delta);

        int priority = level.getPriority() + delta;
        switch (priority) {
        case -2:
            if (jclLog.isErrorEnabled())
                return new ErrorSink(jclLog);
            break;
        case -1:
            if (jclLog.isWarnEnabled())
                return new WarnSink(jclLog);
            break;
        case 0:
        case 1:
        case 2:
            if (jclLog.isInfoEnabled())
                return new InfoSink(jclLog);
            break;
        case 3:
            if (jclLog.isDebugEnabled())
                return new DebugSink(jclLog);
            break;
        default:
            if (priority <= -3) {
                if (jclLog.isFatalEnabled())
                    return new FatalSink(jclLog);
            } else {
                if (jclLog.isTraceEnabled())
                    return new TraceSink(jclLog);
            }
            break;
        }
        return NullLogSink.getInstance();
    }

    @Override
    public boolean _fatal(int delta, Throwable t, Object message) {
        jclLog.fatal(message, t);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable t, Object message) {
        jclLog.error(message, t);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable t, Object message) {
        jclLog.warn(message, t);
    }

    @Override
    public void _info(int delta, Throwable t, Object message) {
        jclLog.info(message, t);
    }

    @Override
    public void _debug(int delta, Throwable t, Object message) {
        jclLog.debug(message, t);
    }

    /**
     * @throws LogConfigurationException
     */
    public static JCLLogger getInstance(Class<?> clazz) {
        return new JCLLogger(LogFactory.getLog(clazz));
    }

    /**
     * @throws LogConfigurationException
     */
    public static JCLLogger getInstance(String name) {
        return new JCLLogger(LogFactory.getLog(name));
    }

}
