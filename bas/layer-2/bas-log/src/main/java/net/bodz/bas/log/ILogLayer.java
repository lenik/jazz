package net.bodz.bas.log;

public interface ILogLayer {

    int STDOUT = 1;
    int ERROR = 2;
    int WARN = 3;
    int INFO = 4;
    int DEBUG = 5;
    int TRACE = 6;
    int USER = 100;

    /**
     * @return {@link NullLogSink} if {@link ILogSink} for the specified <code>index</code> doesn't
     *         exist, or the specified <code>verboseLevel</code> is larger than the configured level
     *         and so logging is suppressed.
     */
    ILogSink get(int index, int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #STDOUT}, {@link ILogSink#LEVEL_DEFAULT})
     */
    ILogSink getStdoutSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #ERROR}, {@link ILogSink#LEVEL_DEFAULT})
     */
    ILogSink getErrorSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #WARN}, {@link ILogSink#LEVEL_DEFAULT})
     */
    ILogSink getWarnSink();

    /**
     * Equals to {@link #get(int, int) get}({@link #INFO}, {@link ILogSink#LEVEL_DEFAULT})
     */
    ILogSink getInfoSink();

    ILogSink getInfoSink(int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #DEBUG}, {@link ILogSink#LEVEL_DEFAULT})
     */
    ILogSink getDebugSink();

    ILogSink getDebugSink(int verboseLevel);

    /**
     * Equals to {@link #get(int, int) get}({@link #TRACE}, {@link ILogSink#LEVEL_DEFAULT})
     */
    ILogSink getTraceSink();

    ILogSink getTraceSink(int verboseLevel);

}
