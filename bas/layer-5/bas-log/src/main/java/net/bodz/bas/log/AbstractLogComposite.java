package net.bodz.bas.log;

import static net.bodz.bas.log.LogCategory.DEBUG;
import static net.bodz.bas.log.LogCategory.ERROR;
import static net.bodz.bas.log.LogCategory.INFO;
import static net.bodz.bas.log.LogCategory.STATUS;
import static net.bodz.bas.log.LogCategory.STATUS_ID;
import static net.bodz.bas.log.LogCategory.STDERR;
import static net.bodz.bas.log.LogCategory.STDERR_ID;
import static net.bodz.bas.log.LogCategory.STDOUT;
import static net.bodz.bas.log.LogCategory.STDOUT_ID;
import net.bodz.bas.log.api.Logger;
import net.bodz.bas.log.impl.PrintStreamLogSink;
import net.bodz.bas.log.impl.PrintStreamStatusSink;
import net.bodz.bas.meta.oop.ChainOrder;
import net.bodz.bas.meta.oop.ChainUsage;
import net.bodz.bas.meta.oop.OverrideOption;

public abstract class AbstractLogComposite
        implements ILogComposite, Logger {

    /**
     * Should be overrided.
     */
    @Override
    @OverrideOption(chain = ChainUsage.PREFERRED, order = ChainOrder.TAIL)
    public ILogSink get(LogCategory category, int verboseLevel) {
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
    public IStatusSink getStatusSink(int verboseLevel) {
        return (IStatusSink) get(STATUS, verboseLevel);
    }

    @Override
    public ILogSink getStdoutSink() {
        return get(STDOUT, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getStdoutSink(int verboseLevel) {
        return get(STDOUT, verboseLevel);
    }

    @Override
    public ILogSink getStderrSink() {
        return get(STDERR, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getStderrSink(int verboseLevel) {
        return get(STDERR, verboseLevel);
    }

    @Override
    public ILogSink getFatalSink() {
        return get(ERROR, LEVEL_HIGH);
    }

    @Override
    public ILogSink getFatalSink(int verboseLevel) {
        return get(ERROR, LEVEL_HIGH + (verboseLevel - LEVEL_DEFAULT));
    }

    @Override
    public ILogSink getErrorSink() {
        return get(ERROR, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getErrorSink(int verboseLevel) {
        return get(ERROR, verboseLevel);
    }

    @Override
    public ILogSink getWarnSink() {
        return get(ERROR, LEVEL_LOW);
    }

    @Override
    public ILogSink getWarnSink(int verboseLevel) {
        return get(ERROR, LEVEL_LOW + (verboseLevel - LEVEL_DEFAULT));
    }

    @Override
    public ILogSink getInfoSink() {
        return get(INFO, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getInfoSink(int verboseLevel) {
        return get(INFO, verboseLevel);
    }

    @Override
    public ILogSink getDebugSink() {
        return get(DEBUG, LEVEL_DEFAULT);
    }

    @Override
    public ILogSink getDebugSink(int verboseLevel) {
        return get(DEBUG, verboseLevel);
    }

    @Override
    public void fatal(Object message) {
        getErrorSink().p(message);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        getErrorSink().p(t, message);
    }

    @Override
    public void error(Object message) {
        getErrorSink().p(message);
    }

    @Override
    public void error(Object message, Throwable t) {
        getErrorSink().p(t, message);
    }

    @Override
    public void warn(Object message) {
        getWarnSink().p(message);
    }

    @Override
    public void warn(Object message, Throwable t) {
        getWarnSink().p(t, message);
    }

    @Override
    public void info(Object message) {
        getInfoSink().p(message);
    }

    @Override
    public void info(Object message, Throwable t) {
        getInfoSink().p(t, message);
    }

    @Override
    public void debug(Object message) {
        getDebugSink().p(message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        getDebugSink().p(t, message);
    }

}
