package net.bodz.bas.log.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import net.bodz.bas.log.AbstractLogSink;

public abstract class Log4jLogSink
        extends AbstractLogSink {

    protected final Logger log4j;

    // static final String FQCN_FATAL = FatalSink.class.getName();
    // static final String FQCN_ERROR = ErrorSink.class.getName();
    // static final String FQCN_WARN = WarnSink.class.getName();
    // static final String FQCN_INFO = InfoSink.class.getName();
    // static final String FQCN_DEBUG = DebugSink.class.getName();
    // static final String FQCN_TRACE = TraceSink.class.getName();

    static final String FQCN_FATAL = AbstractLogSink.class.getName();
    static final String FQCN_ERROR = AbstractLogSink.class.getName();
    static final String FQCN_WARN = AbstractLogSink.class.getName();
    static final String FQCN_INFO = AbstractLogSink.class.getName();
    static final String FQCN_DEBUG = AbstractLogSink.class.getName();
    static final String FQCN_TRACE = AbstractLogSink.class.getName();

    public Log4jLogSink(Logger log4jLogger) {
        if (log4jLogger == null)
            throw new NullPointerException("log4jLogger");
        this.log4j = log4jLogger;
    }

    public static class FatalSink
            extends Log4jLogSink {

        public FatalSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.log(FQCN_FATAL, Level.FATAL, obj, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.log(FQCN_FATAL, Level.FATAL, obj, t);
        }

    }

    public static class ErrorSink
            extends Log4jLogSink {

        public ErrorSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.log(FQCN_ERROR, Level.ERROR, obj, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.log(FQCN_ERROR, Level.ERROR, obj, t);
        }

    }

    public static class WarnSink
            extends Log4jLogSink {

        public WarnSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.log(FQCN_WARN, Level.WARN, obj, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.log(FQCN_WARN, Level.WARN, obj, t);
        }

    }

    public static class InfoSink
            extends Log4jLogSink {

        public InfoSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.log(FQCN_INFO, Level.INFO, obj, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.log(FQCN_INFO, Level.INFO, obj, t);
        }

    }

    public static class DebugSink
            extends Log4jLogSink {

        public DebugSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.log(FQCN_DEBUG, Level.DEBUG, obj, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.log(FQCN_DEBUG, Level.DEBUG, obj, t);
        }

    }

    public static class TraceSink
            extends Log4jLogSink {

        public TraceSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.log(FQCN_TRACE, Level.TRACE, obj, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.log(FQCN_TRACE, Level.TRACE, obj, t);
        }

    }

}
