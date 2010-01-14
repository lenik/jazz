package net.bodz.bas.log.adapters;

import net.bodz.bas.log.AbstractLogLayer;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.NullLogSink;
import net.bodz.bas.log.adapters.Log4jLogSink.DebugSink;
import net.bodz.bas.log.adapters.Log4jLogSink.ErrorSink;
import net.bodz.bas.log.adapters.Log4jLogSink.FatalSink;
import net.bodz.bas.log.adapters.Log4jLogSink.InfoSink;
import net.bodz.bas.log.adapters.Log4jLogSink.TraceSink;
import net.bodz.bas.log.adapters.Log4jLogSink.WarnSink;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jLogLayer
        extends AbstractLogLayer {

    private final Logger log4j;

    public Log4jLogLayer(Logger logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        this.log4j = logger;
    }

    @Override
    public ILogSink get(int index, int verboseLevel) {
        // Mix the LogLayer and Log4j log levels,
        // so a LogLayer(INFO, LEVEL_MORE) is equals to Log4j(DEBUG)
        index = mixLevel(index, verboseLevel);

        switch (index) {
        case ERROR:
            if (verboseLevel < ILogSink.LEVEL_DEFAULT) {
                if (log4j.isEnabledFor(Level.FATAL))
                    return new FatalSink(log4j);
            } else {
                if (log4j.isEnabledFor(Level.ERROR))
                    return new ErrorSink(log4j);
            }
            break;
        case WARN:
            if (log4j.isEnabledFor(Level.WARN))
                return new WarnSink(log4j);
            break;
        case INFO:
            if (log4j.isEnabledFor(Level.INFO))
                return new InfoSink(log4j);
            break;
        case DEBUG:
            if (log4j.isEnabledFor(Level.DEBUG))
                return new DebugSink(log4j);
            break;
        case TRACE:
            if (log4j.isEnabledFor(Level.TRACE))
                return new TraceSink(log4j);
            break;
        case STDOUT:
            return new StdoutLogSink();
        }
        return NullLogSink.getInstance();
    }

    public static Log4jLogLayer getInstance(Class<?> clazz) {
        return new Log4jLogLayer(Logger.getLogger(clazz));
    }

    public static Log4jLogLayer getInstance(String name) {
        return new Log4jLogLayer(Logger.getLogger(name));
    }

}
