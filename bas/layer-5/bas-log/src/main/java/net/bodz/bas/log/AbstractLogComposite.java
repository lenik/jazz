package net.bodz.bas.log;

import net.bodz.bas.log.impl.PrintStreamLogSink;
import net.bodz.bas.meta.util.ChainOrder;
import net.bodz.bas.meta.util.ChainUsage;
import net.bodz.bas.meta.util.OverrideOption;

public abstract class AbstractLogComposite
        implements ILogComposite {

    private int maxPriority = 0;

    public int getMaxPriority() {
        return maxPriority;
    }

    public void setMaxPriority(int maxPriority) {
        this.maxPriority = maxPriority;
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

}
