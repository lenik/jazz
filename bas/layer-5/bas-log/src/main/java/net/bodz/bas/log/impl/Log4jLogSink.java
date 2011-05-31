package net.bodz.bas.log.impl;

import org.apache.log4j.Logger;

import net.bodz.bas.log.AbstractLogSink;

public abstract class Log4jLogSink
        extends AbstractLogSink {

    protected final Logger log4j;

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
            log4j.fatal(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.fatal(obj, t);
        }

    }

    public static class ErrorSink
            extends Log4jLogSink {

        public ErrorSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.error(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.error(obj, t);
        }

    }

    public static class WarnSink
            extends Log4jLogSink {

        public WarnSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.warn(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.warn(obj, t);
        }

    }

    public static class InfoSink
            extends Log4jLogSink {

        public InfoSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.info(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.info(obj, t);
        }

    }

    public static class DebugSink
            extends Log4jLogSink {

        public DebugSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.debug(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.debug(obj, t);
        }

    }

    public static class TraceSink
            extends Log4jLogSink {

        public TraceSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            log4j.trace(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            log4j.trace(obj, t);
        }

    }

}
