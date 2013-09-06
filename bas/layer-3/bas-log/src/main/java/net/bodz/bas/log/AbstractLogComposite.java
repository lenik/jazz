package net.bodz.bas.log;

import net.bodz.bas.log.impl.NullLogSink;
import net.bodz.bas.log.impl.PrintStreamLogSink;
import net.bodz.bas.log.message.ArrayJoinMessage;
import net.bodz.bas.log.message.StringFormatMessage;
import net.bodz.bas.meta.source.ChainOrder;
import net.bodz.bas.meta.source.ChainUsage;
import net.bodz.bas.meta.source.OverrideOption;

public abstract class AbstractLogComposite
        // extends SkippedCallerBase
        implements ILogComposite {

    private LogLevel level = LogLevel.INFO;
    private int delta = 0;
    private int maxPriority = level.getPriority() + delta;

    @Override
    public LogLevel getLevel() {
        return level;
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
        this.maxPriority = level.getPriority() + delta;
    }

    @Override
    public int getDelta() {
        return delta;
    }

    @Override
    public void setDelta(int delta) {
        this.delta = delta;
        this.maxPriority = level.getPriority() + delta;
    }

    @Override
    public void setLevel(LogLevel level, int delta) {
        this.level = level;
        this.delta = delta;
        this.maxPriority = level.getPriority() + delta;
    }

    protected int getMaxPriority() {
        return maxPriority;
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
        return level.getPriority() + delta <= maxPriority;
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
