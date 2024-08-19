package net.bodz.bas.log;

public abstract class AbstractLogger
        extends AbstractLogComposite
        implements
            ILogger {

    /** ⇱ Implementation Of {@link ILogger}. */
    /* _____________________________ */static section.iface __ILOGGER__;

    @Override
    public boolean isStderrEnabled() {
        return LogLevel.STDERR.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isStderrEnabled(int delta) {
        return LogLevel.STDERR.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isStdoutEnabled() {
        return LogLevel.STDOUT.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isStdoutEnabled(int delta) {
        return LogLevel.STDOUT.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isFatalEnabled() {
        return LogLevel.FATAL.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isFatalEnabled(int delta) {
        return LogLevel.FATAL.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isErrorEnabled() {
        return LogLevel.ERROR.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isErrorEnabled(int delta) {
        return LogLevel.ERROR.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isWarnEnabled() {
        return LogLevel.WARN.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isWarnEnabled(int delta) {
        return LogLevel.WARN.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isMesgEnabled() {
        return LogLevel.MESG.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isMesgEnabled(int delta) {
        return LogLevel.MESG.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isInfoEnabled() {
        return LogLevel.INFO.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isInfoEnabled(int delta) {
        return LogLevel.INFO.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isLogEnabled() {
        return LogLevel.LOG.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isLogEnabled(int delta) {
        return LogLevel.LOG.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isDebugEnabled() {
        return LogLevel.DEBUG.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isDebugEnabled(int delta) {
        return LogLevel.DEBUG.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isTraceEnabled() {
        return LogLevel.TRACE.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isTraceEnabled(int delta) {
        return LogLevel.TRACE.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isStatusEnabled() {
        return LogLevel.STATUS.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isStatusEnabled(int delta) {
        return LogLevel.STATUS.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public boolean isProgressEnabled() {
        return LogLevel.PROGRESS.getPriority() <= getMaxPriority();
    }

    @Override
    public boolean isProgressEnabled(int delta) {
        return LogLevel.PROGRESS.getPriority() + delta <= getMaxPriority();
    }

}
