package net.bodz.bas.m2.util;

import net.bodz.bas.log.AbstractLogSink;

import org.apache.maven.plugin.logging.Log;

public abstract class MavenLogSink
        extends AbstractLogSink {

    protected final Log mlog;

    public MavenLogSink(Log mlog) {
        if (mlog == null)
            throw new NullPointerException("mlog");
        this.mlog = mlog;
    }

    public static class ErrorSink
            extends MavenLogSink {

        public ErrorSink(Log mlog) {
            super(mlog);
        }

        @Override
        public void logMessage(Object obj) {
            mlog.error(obj.toString());
        }

        @Override
        public void logException(Object obj, Throwable t) {
            mlog.error(obj.toString(), t);
        }

    }

    public static class WarnSink
            extends MavenLogSink {

        public WarnSink(Log mlog) {
            super(mlog);
        }

        @Override
        public void logMessage(Object obj) {
            mlog.warn(obj.toString());
        }

        @Override
        public void logException(Object obj, Throwable t) {
            mlog.warn(obj.toString(), t);
        }

    }

    public static class InfoSink
            extends MavenLogSink {

        public InfoSink(Log mlog) {
            super(mlog);
        }

        @Override
        public void logMessage(Object obj) {
            mlog.info(obj.toString());
        }

        @Override
        public void logException(Object obj, Throwable t) {
            mlog.info(obj.toString(), t);
        }

    }

    public static class DebugSink
            extends MavenLogSink {

        public DebugSink(Log mlog) {
            super(mlog);
        }

        @Override
        public void logMessage(Object obj) {
            mlog.debug(obj.toString());
        }

        @Override
        public void logException(Object obj, Throwable t) {
            mlog.debug(obj.toString(), t);
        }

    }

}
