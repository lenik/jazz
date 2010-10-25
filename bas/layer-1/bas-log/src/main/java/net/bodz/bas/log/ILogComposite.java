package net.bodz.bas.log;

import org.apache.log4j.Category;

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
     * @return {@link NullLogSink} if {@link ILogSink} for the specified <code>eventType</code>
     *         doesn't exist, or the specified <code>verboseLevel</code> is larger than the enabled
     *         level and so logging is suppressed.
     */
    ILogSink get(LogCategory category, int verboseLevel);

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #STATUS}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STATUS} doesn't exist, or its
     *         enabled level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    IStatusSink getStatusSink();

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #STATUS}, <code>verboseLevel</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STATUS} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the enabled level and so logging
     *         is suppressed.
     */
    IStatusSink getStatusSink(int verboseLevel);

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #STDOUT}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STDOUT} doesn't exist, or its
     *         enabled level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getStdoutSink();

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #STDOUT}, <code>verboseLevel</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STDOUT} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the enabled level and so logging
     *         is suppressed.
     */
    ILogSink getStdoutSink(int verboseLevel);

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #STDERR}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STDERR} doesn't exist, or its
     *         enabled level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getStderrSink();

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #STDERR}, <code>verboseLevel</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STDERR} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the enabled level and so logging
     *         is suppressed.
     */
    ILogSink getStderrSink(int verboseLevel);

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #ERROR}, {@link #LEVEL_HIGH})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or its
     *         enabled level is less than {@link #LEVEL_HIGH} so logging is suppressed.
     */
    ILogSink getFatalSink();

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #ERROR},
     * <code>verboseLevel + ILogSink.LEVEL_STEPLEN</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or the
     *         specified <code>verboseLevel + ILogSink.LEVEL_STEPLEN</code> is larger than the
     *         enabled level and so logging is suppressed.
     */
    ILogSink getFatalSink(int verboseLevel);

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #ERROR}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or its
     *         enabled level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getErrorSink();

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #ERROR}, <code>verboseLevel</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the enabled level and so logging
     *         is suppressed.
     */
    ILogSink getErrorSink(int verboseLevel);

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #ERROR}, {@link #LEVEL_LOW})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or its
     *         enabled level is less than {@link #LEVEL_LOW} so logging is suppressed.
     */
    ILogSink getWarnSink();

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #ERROR},
     * <code>verboseLevel + ILogSink.LEVEL_STEPLEN</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or the
     *         specified verbose level (+ {@link #LEVEL_STEPLEN}) is larger than the enabled level
     *         and so logging is suppressed.
     */
    ILogSink getWarnSink(int verboseLevel);

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #INFO}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #INFO} doesn't exist, or its
     *         enabled level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getInfoSink();

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #INFO}, <code>verboseLevel</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #INFO} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the enabled level and so logging
     *         is suppressed.
     */
    ILogSink getInfoSink(int verboseLevel);

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #DEBUG}, {@link #LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #DEBUG} doesn't exist, or its
     *         enabled level is less than {@link #LEVEL_DEFAULT} so logging is suppressed.
     */
    ILogSink getDebugSink();

    /**
     * Equals to {@link #get(LogCategory, int) get}({@link #DEBUG}, <code>verboseLevel</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #DEBUG} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the enabled level and so logging
     *         is suppressed.
     */
    ILogSink getDebugSink(int verboseLevel);

    // Log4j compatible methods

    /**
     * @see Category#error(Object)
     */
    void fatal(Object message);

    /**
     * @see Category#error(Object)
     */
    void fatal(Object message, Throwable t);

    /**
     * @see Category#error(Object)
     */
    void error(Object message);

    /**
     * @see Category#error(Object)
     */
    void error(Object message, Throwable t);

    /**
     * @see Category#warn(Object)
     */
    void warn(Object message);

    /**
     * @see Category#warn(Object)
     */
    void warn(Object message, Throwable t);

    /**
     * @see Category#info(Object)
     */
    void info(Object message);

    /**
     * @see Category#info(Object)
     */
    void info(Object message, Throwable t);

    /**
     * @see Category#debug(Object)
     */
    void debug(Object message);

    /**
     * @see Category#debug(Object)
     */
    void debug(Object message, Throwable t);

}
