package net.bodz.bas.log.adapters;

import net.bodz.bas.log.AbstractLogLayer;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.adapters.JCLLogSink.DebugSink;
import net.bodz.bas.log.adapters.JCLLogSink.InfoSink;
import net.bodz.bas.log.adapters.JCLLogSink.TraceSink;
import net.bodz.bas.log.adapters.JCLLogSink.WarnSink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

public class JCLLogLayer
        extends AbstractLogLayer {

    private final Log jclLog;

    public JCLLogLayer(Log jclLog) {
        if (jclLog == null)
            throw new NullPointerException("jclLog");
        this.jclLog = jclLog;
    }

    @Override
    public ILogSink get(int index, int verboseLevel) {
        // Mix the LogLayer and JCL log levels,
        // so a LogLayer(INFO, LEVEL_MORE) is equals to JCL(DEBUG)
        index = mixLevel(index, verboseLevel);

        switch (index) {
        case ERROR:
            if (jclLog.isErrorEnabled())
                return new InfoSink(jclLog);
            break;
        case WARN:
            if (jclLog.isWarnEnabled())
                return new WarnSink(jclLog);
            break;
        case INFO:
            if (jclLog.isInfoEnabled())
                return new InfoSink(jclLog);
            break;
        case DEBUG:
            if (jclLog.isDebugEnabled())
                return new DebugSink(jclLog);
            break;
        case TRACE:
            if (jclLog.isTraceEnabled())
                return new TraceSink(jclLog);
            break;
        case STDOUT:
            return new StdoutLogSink();
        }
        return NullLogSink.getInstance();
    }

    /**
     * @throws LogConfigurationException
     */
    public static JCLLogLayer getInstance(Class<?> clazz) {
        return new JCLLogLayer(LogFactory.getLog(clazz));
    }

    /**
     * @throws LogConfigurationException
     */
    public static JCLLogLayer getInstance(String name) {
        return new JCLLogLayer(LogFactory.getLog(name));
    }

}
