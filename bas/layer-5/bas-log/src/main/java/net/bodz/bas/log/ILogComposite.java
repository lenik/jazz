package net.bodz.bas.log;

public interface ILogComposite {

    /**
     * It's logged only if the actual level is less then or equals to the max priority.
     * 
     * @return Max priority.
     */
    int getMaxPriority();

    /**
     * Set the max priority.
     * 
     * @param maxPriority
     *            The max priority.
     */
    void setMaxPriority(int maxPriority);

    /**
     * Set the max priority with current priority plus the specified <code>delta</code>.
     */
    void verbose(int delta);

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
