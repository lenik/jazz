package net.bodz.bas.log;

import javax.annotation.Generated;

/**
 * Sink-based logger.
 */
@Generated("By LoggerCG")
public interface Logger
        extends ILogComposite, ILogger, ILog4jCompat {

    /**
     * Get the stderr logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getStderrSink();

    /**
     * Get the stderr logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getStderrSink(int delta);

    /**
     * Get the stdout logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getStdoutSink();

    /**
     * Get the stdout logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getStdoutSink(int delta);

    /**
     * Get the fatal logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getFatalSink();

    /**
     * Get the fatal logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getFatalSink(int delta);

    /**
     * Get the error logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getErrorSink();

    /**
     * Get the error logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getErrorSink(int delta);

    /**
     * Get the warn logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getWarnSink();

    /**
     * Get the warn logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getWarnSink(int delta);

    /**
     * Get the mesg logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getMesgSink();

    /**
     * Get the mesg logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getMesgSink(int delta);

    /**
     * Get the info logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getInfoSink();

    /**
     * Get the info logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getInfoSink(int delta);

    /**
     * Get the log logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getLogSink();

    /**
     * Get the log logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getLogSink(int delta);

    /**
     * Get the debug logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getDebugSink();

    /**
     * Get the debug logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getDebugSink(int delta);

    /**
     * Get the trace logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getTraceSink();

    /**
     * Get the trace logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getTraceSink(int delta);

    /**
     * Get the status logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getStatusSink();

    /**
     * Get the status logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getStatusSink(int delta);

    /**
     * Get the progress logging output sink.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getProgressSink();

    /**
     * Get the progress logging output sink with a delta verbose.
     * <p>
     * If the specific logging is disabled, a NullLogSink will be returned.
     * 
     * @param delta
     *            The verbose level delta.
     * @return Non-<code>null</code> sink object.
     */
    ILogSink getProgressSink(int delta);

    NullLogger NULL = new NullLogger();

}
