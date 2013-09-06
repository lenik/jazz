package net.bodz.bas.m2.util;

import org.apache.maven.plugin.logging.Log;

import net.bodz.bas.log.AbstractLogger;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.impl.NullLogSink;
import net.bodz.bas.m2.util.MavenLogSink.DebugSink;
import net.bodz.bas.m2.util.MavenLogSink.ErrorSink;
import net.bodz.bas.m2.util.MavenLogSink.InfoSink;
import net.bodz.bas.m2.util.MavenLogSink.WarnSink;

public class MavenLogLogger
        extends AbstractLogger {

    private final Log mlog;

    static final String FQCN = AbstractLogger.class.getName();

    public MavenLogLogger(Log mlog) {
        if (mlog == null)
            throw new NullPointerException("mlog");
        this.mlog = mlog;
    }

    @Override
    public ILogSink get(LogLevel level, int delta) {
        if (level.getGroup() != LogLevel.logGroup)
            return super.get(level, delta);

        int priority = level.getPriority() + delta;
        switch (priority) {
        case -2:
            if (mlog.isErrorEnabled())
                return new ErrorSink(mlog);
            break;
        case -1:
            if (mlog.isWarnEnabled())
                return new WarnSink(mlog);
            break;
        case 0:
        case 1:
        case 2:
            if (mlog.isInfoEnabled())
                return new InfoSink(mlog);
            break;
        case 3:
            if (mlog.isDebugEnabled())
                return new DebugSink(mlog);
            break;
        default:
            if (priority <= -3) {
                if (mlog.isErrorEnabled())
                    return new ErrorSink(mlog);
            } else {
                if (mlog.isDebugEnabled())
                    return new DebugSink(mlog);
            }
            break;
        }
        return NullLogSink.getInstance();
    }

    @Override
    public boolean _fatal(int delta, Throwable t, Object message) {
        mlog.error(message.toString(), t);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable t, Object message) {
        mlog.error(message.toString(), t);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable t, Object message) {
        mlog.warn(message.toString(), t);
    }

    @Override
    public void _info(int delta, Throwable t, Object message) {
        mlog.info(message.toString(), t);
    }

    @Override
    public void _debug(int delta, Throwable t, Object message) {
        mlog.debug(message.toString(), t);
    }

}
