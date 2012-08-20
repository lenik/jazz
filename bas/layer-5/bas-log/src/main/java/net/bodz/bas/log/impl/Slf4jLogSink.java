package net.bodz.bas.log.impl;

import org.slf4j.Logger;

import net.bodz.bas.log.AbstractLogSink;

public abstract class Slf4jLogSink
        extends AbstractLogSink {

    protected final Logger slf4j;

    public Slf4jLogSink(Logger log4jLogger) {
        if (log4jLogger == null)
            throw new NullPointerException("slf4jLogger");
        this.slf4j = log4jLogger;
    }

    static String format(Object obj) {
        return String.valueOf(obj);
    }

    public static class ErrorSink
            extends Slf4jLogSink {

        public ErrorSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.error(msg);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.error(msg, t);
        }

    }

    public static class WarnSink
            extends Slf4jLogSink {

        public WarnSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.warn(msg);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.warn(msg, t);
        }

    }

    public static class InfoSink
            extends Slf4jLogSink {

        public InfoSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.info(msg);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.info(msg, t);
        }

    }

    public static class DebugSink
            extends Slf4jLogSink {

        public DebugSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.debug(msg);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.debug(msg, t);
        }

    }

    public static class TraceSink
            extends Slf4jLogSink {

        public TraceSink(Logger log4jLogger) {
            super(log4jLogger);
        }

        @Override
        public void logMessage(Object obj) {
            String msg = format(obj);
            slf4j.trace(msg);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            String msg = format(obj);
            slf4j.trace(msg, t);
        }

    }

}
