package net.bodz.bas.log.impl;

import org.slf4j.spi.LocationAwareLogger;

import net.bodz.bas.log.AbstractLogSink;

public abstract class Slf4jLogSink
        extends AbstractLogSink {

    static final String FQCN = Slf4jLogSink.class.getName();

    protected final LocationAwareLogger slf4j;

    public Slf4jLogSink(LocationAwareLogger log4jLogger) {
        if (log4jLogger == null)
            throw new NullPointerException("slf4jLogger");
        this.slf4j = log4jLogger;
    }

    static String format(Object obj) {
        return String.valueOf(obj);
    }

    public static class ErrorSink
            extends Slf4jLogSink {

        public ErrorSink(LocationAwareLogger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.ERROR_INT, //
                    msg, null, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.ERROR_INT, //
                    msg, null, t);
        }

    }

    public static class WarnSink
            extends Slf4jLogSink {

        public WarnSink(LocationAwareLogger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.WARN_INT, //
                    msg, null, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.WARN_INT, //
                    msg, null, t);
        }

    }

    public static class InfoSink
            extends Slf4jLogSink {

        public InfoSink(LocationAwareLogger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.INFO_INT, //
                    msg, null, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.INFO_INT, //
                    msg, null, t);
        }

    }

    public static class DebugSink
            extends Slf4jLogSink {

        public DebugSink(LocationAwareLogger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.DEBUG_INT, //
                    msg, null, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.DEBUG_INT, //
                    msg, null, t);
        }

    }

    public static class TraceSink
            extends Slf4jLogSink {

        public TraceSink(LocationAwareLogger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.TRACE_INT, //
                    msg, null, null);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.log(null, FQCN, LocationAwareLogger.TRACE_INT, //
                    msg, null, t);
        }

    }

}
