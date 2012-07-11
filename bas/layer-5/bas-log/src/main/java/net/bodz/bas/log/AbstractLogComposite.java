package net.bodz.bas.log;

import net.bodz.bas.log.impl.PrintStreamLogSink;
import net.bodz.bas.log.message.ArrayJoinMessage;
import net.bodz.bas.log.message.StringFormatMessage;
import net.bodz.bas.meta.codehint.ChainOrder;
import net.bodz.bas.meta.codehint.ChainUsage;
import net.bodz.bas.meta.codehint.OverrideOption;

public abstract class AbstractLogComposite
        // extends SkippedCallerBase
        implements ILogComposite {

    private int maxPriority = 0;

    public int getMaxPriority() {
        return maxPriority;
    }

    public void setMaxPriority(int maxPriority) {
        this.maxPriority = maxPriority;
    }

    @Override
    public void verbose(int delta) {
        this.maxPriority += delta;
    }

    /**
     * Should be overrided.
     */
    @Override
    @OverrideOption(chain = ChainUsage.PREFERRED, order = ChainOrder.TAIL)
    public ILogSink get(LogLevel level, int delta) {
        if (level.getGroup() == LogLevel.stdGroup) {
            if (level.getInternName() == "stdout")
                return new PrintStreamLogSink(System.out);
            if (level.getInternName() == "stderr")
                return new PrintStreamLogSink(System.err);
        }
        return NullLogSink.getInstance();
    }

    @Override
    public final ILogSink get(LogLevel level) {
        return get(level, 0);
    }

    @Override
    public boolean isEnabled(LogLevel level, int delta) {
        return level.getPriority() + delta <= getMaxPriority();
    }

    @Override
    public final boolean isEnabled(LogLevel level) {
        return isEnabled(level, 0);
    }

    // utils

    protected static String nameOf(Class<?> clazz) {
        return clazz.getName();
    }

    protected static Object concat(Object... array) {
        return new ArrayJoinMessage(array);
    }

    protected static Object format(String fmt, Object... args) {
        return new StringFormatMessage(fmt, args);
    }

}
