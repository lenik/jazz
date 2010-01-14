package net.bodz.bas.log.adapters;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.bodz.bas.log.AbstractLogLayer;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.adapters.JdkLogSink.FineSink;
import net.bodz.bas.log.adapters.JdkLogSink.FinerSink;
import net.bodz.bas.log.adapters.JdkLogSink.FinestSink;
import net.bodz.bas.log.adapters.JdkLogSink.InfoSink;
import net.bodz.bas.log.adapters.JdkLogSink.SevereSink;
import net.bodz.bas.log.adapters.JdkLogSink.WarningSink;

public class JdkLogLayer
        extends AbstractLogLayer {

    private final Logger jdkLogger;

    public JdkLogLayer(Logger jdkLogger) {
        if (jdkLogger == null)
            throw new NullPointerException("jdkLogger");
        this.jdkLogger = jdkLogger;
    }

    @Override
    public ILogSink get(int index, int verboseLevel) {
        // Mix the LogLayer and Jdk log levels,
        // so a LogLayer(INFO, LEVEL_MORE) is equals to Jdk(DEBUG)
        index = mixLevel(index, verboseLevel);

        switch (index) {
        case ERROR:
            if (jdkLogger.isLoggable(Level.SEVERE))
                return new SevereSink(jdkLogger);
            break;
        case WARN:
            if (jdkLogger.isLoggable(Level.WARNING))
                return new WarningSink(jdkLogger);
            break;
        case INFO:
            if (jdkLogger.isLoggable(Level.INFO))
                return new InfoSink(jdkLogger);
            break;
        case DEBUG:
            if (jdkLogger.isLoggable(Level.FINE))
                return new FineSink(jdkLogger);
            break;
        case TRACE:
            if (jdkLogger.isLoggable(Level.FINER))
                return new FinerSink(jdkLogger);
            break;
        case TRACE + 1:
            if (jdkLogger.isLoggable(Level.FINEST))
                return new FinestSink(jdkLogger);
        case STDOUT:
            return new StdoutLogSink();
        }
        return NullLogSink.getInstance();
    }

    /**
     * @throws NullPointerException
     */
    public static JdkLogLayer getInstance(Class<?> clazz) {
        return new JdkLogLayer(Logger.getLogger(clazz.getName()));
    }

    /**
     * @throws NullPointerException
     */
    public static JdkLogLayer getInstance(String name) {
        return new JdkLogLayer(Logger.getLogger(name));
    }

}
