package net.bodz.bas.log.adapters;

import java.util.logging.Logger;

import net.bodz.bas.log.AbstractLogSink;

public abstract class JdkLogSink
        extends AbstractLogSink {

    protected final Logger jdkLogger;

    public JdkLogSink(Logger jdkLogger) {
        if (jdkLogger == null)
            throw new NullPointerException("jdkLogger");
        this.jdkLogger = jdkLogger;
    }

    @Override
    protected int getConfiguredVerboseLevel() {
        return LEVEL_DEFAULT;
    }

    public static class SevereSink
            extends JdkLogSink {

        public SevereSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void put(Object obj) {
            jdkLogger.severe(obj.toString());
        }

    }

    public static class WarningSink
            extends JdkLogSink {

        public WarningSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void put(Object obj) {
            jdkLogger.warning(obj.toString());
        }

    }

    public static class ConfigSink
            extends JdkLogSink {

        public ConfigSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void put(Object obj) {
            jdkLogger.config(obj.toString());
        }

    }

    public static class InfoSink
            extends JdkLogSink {

        public InfoSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void put(Object obj) {
            jdkLogger.info(obj.toString());
        }

    }

    public static class FineSink
            extends JdkLogSink {

        public FineSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void put(Object obj) {
            jdkLogger.fine(obj.toString());
        }

    }

    public static class FinerSink
            extends JdkLogSink {

        public FinerSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void put(Object obj) {
            jdkLogger.finer(obj.toString());
        }

    }

    public static class FinestSink
            extends JdkLogSink {

        public FinestSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void put(Object obj) {
            jdkLogger.finest(obj.toString());
        }

    }

}
