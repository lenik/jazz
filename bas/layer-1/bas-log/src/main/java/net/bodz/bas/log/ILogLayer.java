package net.bodz.bas.log;

import org.apache.log4j.Category;

public interface ILogLayer {

    String STDOUT = "stdout";
    String ERROR = "error";
    String WARN = "warn";
    String INFO = "info";
    String DEBUG = "debug";
    String TRACE = "trace";
    String STATUS = "status";

    /**
     * @return {@link NullLogSink} if {@link ILogSink} for the specified <code>eventType</code>
     *         doesn't exist, or the specified <code>verboseLevel</code> is larger than the
     *         configured level and so logging is suppressed.
     */
    ILogSink get(String eventType, int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #STDOUT}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STDOUT} doesn't exist, or its
     *         configured level is less than {@link ILogSink#LEVEL_DEFAULT} so logging is
     *         suppressed.
     */
    ILogSink getStdoutSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #STDOUT}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STDOUT} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the configured level and so
     *         logging is suppressed.
     */
    ILogSink getStdoutSink(int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #ERROR}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or its
     *         configured level is less than {@link ILogSink#LEVEL_DEFAULT} so logging is
     *         suppressed.
     */
    ILogSink getErrorSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #ERROR}, <code>verboseLevel</code>)
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #ERROR} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the configured level and so
     *         logging is suppressed.
     */
    ILogSink getErrorSink(int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #WARN}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #WARN} doesn't exist, or its
     *         configured level is less than {@link ILogSink#LEVEL_DEFAULT} so logging is
     *         suppressed.
     */
    ILogSink getWarnSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #WARN}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #WARN} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the configured level and so
     *         logging is suppressed.
     */
    ILogSink getWarnSink(int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #INFO}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #INFO} doesn't exist, or its
     *         configured level is less than {@link ILogSink#LEVEL_DEFAULT} so logging is
     *         suppressed.
     */
    ILogSink getInfoSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #INFO}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #INFO} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the configured level and so
     *         logging is suppressed.
     */
    ILogSink getInfoSink(int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #DEBUG}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #DEBUG} doesn't exist, or its
     *         configured level is less than {@link ILogSink#LEVEL_DEFAULT} so logging is
     *         suppressed.
     */
    ILogSink getDebugSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #DEBUG}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #DEBUG} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the configured level and so
     *         logging is suppressed.
     */
    ILogSink getDebugSink(int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #TRACE}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #TRACE} doesn't exist, or its
     *         configured level is less than {@link ILogSink#LEVEL_DEFAULT} so logging is
     *         suppressed.
     */
    ILogSink getTraceSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #TRACE}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #TRACE} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the configured level and so
     *         logging is suppressed.
     */
    ILogSink getTraceSink(int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #STATUS}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STATUS} doesn't exist, or its
     *         configured level is less than {@link ILogSink#LEVEL_DEFAULT} so logging is
     *         suppressed.
     */
    IStatusSink getStatusSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #STATUS}, {@link ILogSink#LEVEL_DEFAULT})
     * 
     * @return {@link NullLogSink} if {@link ILogSink} for the {@link #STATUS} doesn't exist, or the
     *         specified <code>verboseLevel</code> is larger than the configured level and so
     *         logging is suppressed.
     */
    IStatusSink getStatusSink(int verboseLevel);

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
