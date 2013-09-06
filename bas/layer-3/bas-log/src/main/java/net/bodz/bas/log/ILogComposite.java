package net.bodz.bas.log;

import net.bodz.bas.log.impl.NullLogSink;

public interface ILogComposite {

    LogLevel getLevel();

    void setLevel(LogLevel level);

    int getDelta();

    void setDelta(int delta);

    void setLevel(LogLevel logLevel, int delta);

    /**
     * @return {@link NullLogSink} if {@link ILogSink} for the specified <code>eventType</code>
     *         doesn't exist, or the specified <code>actualLevel</code> is larger than the effective
     *         level and so logging is suppressed.
     */
    ILogSink get(LogLevel level);

    ILogSink get(LogLevel level, int delta);

    boolean isEnabled(LogLevel level);

    boolean isEnabled(LogLevel level, int delta);

}
