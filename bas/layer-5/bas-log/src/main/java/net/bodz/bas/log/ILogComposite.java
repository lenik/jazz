package net.bodz.bas.log;

public interface ILogComposite {

    /**
     * Critical Range: (-Inf, CRITICAL]
     */
    int LEVEL_CRITICAL = -20000;

    /**
     * High Range: (CRITICAL, HIGH]
     */
    int LEVEL_HIGH = -10000;

    /**
     * Default Range: (HIGH, LOW)
     */
    int LEVEL_DEFAULT = 0;

    /**
     * Low Range: [LOW, +Inf)
     */
    int LEVEL_LOW = 10000;

    int LEVEL_STEPLEN = 10000;

    /**
     * It's logged only if the actual level is greater then or equals to the effectivelevel.
     * 
     * @return The effective level.
     */
    int getEffectiveLevel();

    /**
     * @return {@link NullLogSink} if {@link ILogSink} for the specified <code>eventType</code>
     *         doesn't exist, or the specified <code>actualLevel</code> is larger than the effective
     *         level and so logging is suppressed.
     */
    ILogSink get(LogLevel category, int actualLevel);

    /**
     * Equals to {@link #get(LogLevel, int) get}({@link #STATUS}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STATUS} doesn't exist, or its
     *         effective level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    IStatusSink getStatusSink();

    /**
     * Equals to {@link #get(LogLevel, int) get}({@link #STDOUT}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STDOUT} doesn't exist, or its
     *         effective level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getStdoutSink();

    /**
     * Equals to {@link #get(LogLevel, int) get}({@link #STDERR}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STDERR} doesn't exist, or its
     *         effective level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getStderrSink();

    /**
     * Equals to {@link #get(LogLevel, int) get}({@link #ERROR}, {@link #LEVEL_HIGH})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or its
     *         effective level is less than {@link #LEVEL_HIGH} so logging is suppressed.
     */
    ILogSink getFatalSink();

    /**
     * Equals to {@link #get(LogLevel, int) get}({@link #ERROR}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or its
     *         effective level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getErrorSink();

    /**
     * Equals to {@link #get(LogLevel, int) get}({@link #ERROR}, {@link #LEVEL_LOW})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or its
     *         effective level is less than {@link #LEVEL_LOW} so logging is suppressed.
     */
    ILogSink getWarnSink();

    /**
     * Equals to {@link #get(LogLevel, int) get}({@link #INFO}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #INFO} doesn't exist, or its
     *         effective level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getInfoSink();

    /**
     * Equals to {@link #get(LogLevel, int) get}({@link #DEBUG}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #DEBUG} doesn't exist, or its
     *         effective level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getDebugSink();

    boolean isEnabled(LogLevel category, int actualLevel);

    boolean isStdoutEnabled();

    boolean isStderrEnabled();

    boolean isStatusEnabled();

    boolean isFatalEnabled();

    boolean isErrorEnabled();

    boolean isWarnEnabled();

    boolean isInfoEnabled();

    boolean isDebugEnabled();

}
