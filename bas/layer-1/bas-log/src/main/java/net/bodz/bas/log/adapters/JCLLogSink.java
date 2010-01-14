package net.bodz.bas.log.adapters;

import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.ILogSink;

import org.apache.commons.logging.Log;

public abstract class JCLLogSink
        extends AbstractLogSink {

    protected final Log jclLog;
    protected int configuredLevel = ILogSink.LEVEL_DEFAULT;

    public JCLLogSink(Log jclLog) {
        if (jclLog == null)
            throw new NullPointerException("jclLog");
        this.jclLog = jclLog;
    }

    @Override
    protected int getConfiguredVerboseLevel() {
        return configuredLevel;
    }

    @Override
    public abstract void put(Object obj, Throwable t);

    public static class ErrorSink
            extends JCLLogSink {

        public ErrorSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void put(Object obj) {
            jclLog.error(obj);
        }

        @Override
        public void put(Object obj, Throwable t) {
            jclLog.error(obj, t);
        }

    }

    public static class WarnSink
            extends JCLLogSink {

        public WarnSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void put(Object obj) {
            jclLog.warn(obj);
        }

        @Override
        public void put(Object obj, Throwable t) {
            jclLog.warn(obj, t);
        }

    }

    public static class InfoSink
            extends JCLLogSink {

        public InfoSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void put(Object obj) {
            jclLog.info(obj);
        }

        @Override
        public void put(Object obj, Throwable t) {
            jclLog.info(obj, t);
        }

    }

    public static class DebugSink
            extends JCLLogSink {

        public DebugSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void put(Object obj) {
            jclLog.debug(obj);
        }

        @Override
        public void put(Object obj, Throwable t) {
            jclLog.debug(obj, t);
        }

    }

    public static class TraceSink
            extends JCLLogSink {

        public TraceSink(Log jclLog) {
            super(jclLog);
        }

        @Override
        public void put(Object obj) {
            jclLog.trace(obj);
        }

        @Override
        public void put(Object obj, Throwable t) {
            jclLog.trace(obj, t);
        }

    }

}
