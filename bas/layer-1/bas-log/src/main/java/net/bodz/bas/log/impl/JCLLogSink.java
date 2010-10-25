package net.bodz.bas.log.impl;

import net.bodz.bas.log.AbstractLogSink;

import org.apache.commons.logging.Log;

public abstract class JCLLogSink
        extends AbstractLogSink {

    protected final Log jclLog;

    public JCLLogSink(Log jclLog) {
        if (jclLog == null)
            throw new NullPointerException("jclLog");
        this.jclLog = jclLog;
    }

    public static class FatalSink
            extends JCLLogSink {

        public FatalSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void logMessage(Object obj) {
            jclLog.fatal(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            jclLog.fatal(obj, t);
        }

    }

    public static class ErrorSink
            extends JCLLogSink {

        public ErrorSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void logMessage(Object obj) {
            jclLog.error(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            jclLog.error(obj, t);
        }

    }

    public static class WarnSink
            extends JCLLogSink {

        public WarnSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void logMessage(Object obj) {
            jclLog.warn(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            jclLog.warn(obj, t);
        }

    }

    public static class InfoSink
            extends JCLLogSink {

        public InfoSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void logMessage(Object obj) {
            jclLog.info(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            jclLog.info(obj, t);
        }

    }

    public static class DebugSink
            extends JCLLogSink {

        public DebugSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void logMessage(Object obj) {
            jclLog.debug(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            jclLog.debug(obj, t);
        }

    }

    public static class TraceSink
            extends JCLLogSink {

        public TraceSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void logMessage(Object obj) {
            jclLog.trace(obj);
        }

        @Override
        public void logException(Object obj, Throwable t) {
            jclLog.trace(obj, t);
        }

    }

}
