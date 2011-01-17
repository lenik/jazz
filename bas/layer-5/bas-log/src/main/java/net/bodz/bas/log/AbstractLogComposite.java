package net.bodz.bas.log;

import static net.bodz.bas.log.LogLevel.DEBUG;
import static net.bodz.bas.log.LogLevel.ERROR;
import static net.bodz.bas.log.LogLevel.INFO;
import static net.bodz.bas.log.LogLevel.STATUS;
import static net.bodz.bas.log.LogLevel.STDERR;
import static net.bodz.bas.log.LogLevel.STDOUT;
import net.bodz.bas.log.impl.PrintStreamLogSink;
import net.bodz.bas.log.impl.PrintStreamStatusSink;
import net.bodz.bas.meta.util.ChainOrder;
import net.bodz.bas.meta.util.ChainUsage;
import net.bodz.bas.meta.util.OverrideOption;

public abstract class AbstractLogComposite
        implements ILogComposite {

    /**
     * Should be overrided.
     */
    @Override
    @OverrideOption(chain = ChainUsage.PREFERRED, order = ChainOrder.TAIL)
    public ILogSink get(LogLevel category, int actualLevel) {
        switch (category.getId()) {
        case STATUS_ID:
            return new PrintStreamStatusSink(System.out);

        case STDOUT_ID:
            return new PrintStreamLogSink(System.out);

        case STDERR_ID:
            return new PrintStreamLogSink(System.err);
        }
        return null;
    }

    @Override
    public IStatusSink getStatusSink() {
        return (IStatusSink) get(STATUS, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getStdoutSink() {
        return get(STDOUT, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getStderrSink() {
        return get(STDERR, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getFatalSink() {
        return get(ERROR, LEVEL_HIGH);
    }

    @Override
    public ILogSink getErrorSink() {
        return get(ERROR, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getWarnSink() {
        return get(ERROR, LEVEL_LOW);
    }

    @Override
    public ILogSink getInfoSink() {
        return get(INFO, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getDebugSink() {
        return get(DEBUG, LEVEL_DEFAULT);
    }

    @Override
    public boolean isStdoutEnabled() {
        return getEffectiveLevel() >= LEVEL_DEFAULT;
    }

    @Override
    public boolean isStderrEnabled() {
        return getEffectiveLevel() >= LEVEL_DEFAULT;
    }

    @Override
    public boolean isStatusEnabled() {
        return getEffectiveLevel() >= LEVEL_LOW;
    }

    @Override
    public boolean isFatalEnabled() {
        return getEffectiveLevel() >= LEVEL_HIGH;
    }

    @Override
    public boolean isErrorEnabled() {
        return getEffectiveLevel() >= LEVEL_DEFAULT;
    }

    @Override
    public boolean isWarnEnabled() {
        return getEffectiveLevel() >= LEVEL_LOW;
    }

    @Override
    public boolean isInfoEnabled() {
        return getEffectiveLevel() >= LEVEL_DEFAULT;
    }

    @Override
    public boolean isDebugEnabled() {
        return getEffectiveLevel() >= LEVEL_LOW;
    }

}
