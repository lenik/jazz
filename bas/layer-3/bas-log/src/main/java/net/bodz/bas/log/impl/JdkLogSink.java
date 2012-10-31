package net.bodz.bas.log.impl;

import java.util.logging.Level;
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

    public static class SevereSink
            extends JdkLogSink {

        public SevereSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void logMessage(Object obj) {
            jdkLogger.severe(String.valueOf(obj));
        }

        @Override
        public void logException(Object message, Throwable exception) {
            jdkLogger.log(Level.SEVERE, String.valueOf(message), exception);
        }

    }

    public static class WarningSink
            extends JdkLogSink {

        public WarningSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void logMessage(Object obj) {
            jdkLogger.warning(String.valueOf(obj));
        }

        @Override
        public void logException(Object message, Throwable exception) {
            jdkLogger.log(Level.WARNING, String.valueOf(message), exception);
        }

    }

    public static class ConfigSink
            extends JdkLogSink {

        public ConfigSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void logMessage(Object obj) {
            jdkLogger.config(String.valueOf(obj));
        }

        @Override
        public void logException(Object message, Throwable exception) {
            jdkLogger.log(Level.CONFIG, String.valueOf(message), exception);
        }

    }

    public static class InfoSink
            extends JdkLogSink {

        public InfoSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void logMessage(Object obj) {
            jdkLogger.info(String.valueOf(obj));
        }

        @Override
        public void logException(Object message, Throwable exception) {
            jdkLogger.log(Level.INFO, String.valueOf(message), exception);
        }

    }

    public static class FineSink
            extends JdkLogSink {

        public FineSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void logMessage(Object obj) {
            jdkLogger.fine(String.valueOf(obj));
        }

        @Override
        public void logException(Object message, Throwable exception) {
            jdkLogger.log(Level.FINE, String.valueOf(message), exception);
        }

    }

    public static class FinerSink
            extends JdkLogSink {

        public FinerSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void logMessage(Object obj) {
            jdkLogger.finer(String.valueOf(obj));
        }

        @Override
        public void logException(Object message, Throwable exception) {
            jdkLogger.log(Level.FINER, String.valueOf(message), exception);
        }

    }

    public static class FinestSink
            extends JdkLogSink {

        public FinestSink(Logger jdkLogger) {
            super(jdkLogger);
        }

        @Override
        public void logMessage(Object obj) {
            jdkLogger.finest(String.valueOf(obj));
        }

        @Override
        public void logException(Object message, Throwable exception) {
            jdkLogger.log(Level.FINEST, String.valueOf(message), exception);
        }

    }

}
