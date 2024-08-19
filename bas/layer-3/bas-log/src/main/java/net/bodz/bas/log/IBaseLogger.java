package net.bodz.bas.log;

public interface IBaseLogger {

    /**
     * Check if stderr logging is enabled.
     *
     * @return <code>true</code> If stderr logging is enabled.
     */
    boolean isStderrEnabled();

    /**
     * Check if stderr logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF stderr logging with the specific delta is enabled.
     */
    boolean isStderrEnabled(int delta);

    /**
     * Add a stderr logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _stderr(int delta, Throwable e, Object message);

    /**
     * Check if stdout logging is enabled.
     *
     * @return <code>true</code> If stdout logging is enabled.
     */
    boolean isStdoutEnabled();

    /**
     * Check if stdout logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF stdout logging with the specific delta is enabled.
     */
    boolean isStdoutEnabled(int delta);

    /**
     * Add a stdout logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _stdout(int delta, Throwable e, Object message);

    /**
     * Check if fatal logging is enabled.
     *
     * @return <code>true</code> If fatal logging is enabled.
     */
    boolean isFatalEnabled();

    /**
     * Check if fatal logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF fatal logging with the specific delta is enabled.
     */
    boolean isFatalEnabled(int delta);

    /**
     * Add a fatal logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    boolean _fatal(int delta, Throwable e, Object message);

    /**
     * Check if error logging is enabled.
     *
     * @return <code>true</code> If error logging is enabled.
     */
    boolean isErrorEnabled();

    /**
     * Check if error logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF error logging with the specific delta is enabled.
     */
    boolean isErrorEnabled(int delta);

    /**
     * Add a error logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    boolean _error(int delta, Throwable e, Object message);

    /**
     * Check if warn logging is enabled.
     *
     * @return <code>true</code> If warn logging is enabled.
     */
    boolean isWarnEnabled();

    /**
     * Check if warn logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF warn logging with the specific delta is enabled.
     */
    boolean isWarnEnabled(int delta);

    /**
     * Add a warn logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _warn(int delta, Throwable e, Object message);

    /**
     * Check if mesg logging is enabled.
     *
     * @return <code>true</code> If mesg logging is enabled.
     */
    boolean isMesgEnabled();

    /**
     * Check if mesg logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF mesg logging with the specific delta is enabled.
     */
    boolean isMesgEnabled(int delta);

    /**
     * Add a mesg logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _mesg(int delta, Throwable e, Object message);

    /**
     * Check if info logging is enabled.
     *
     * @return <code>true</code> If info logging is enabled.
     */
    boolean isInfoEnabled();

    /**
     * Check if info logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF info logging with the specific delta is enabled.
     */
    boolean isInfoEnabled(int delta);

    /**
     * Add a info logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _info(int delta, Throwable e, Object message);

    /**
     * Check if log logging is enabled.
     *
     * @return <code>true</code> If log logging is enabled.
     */
    boolean isLogEnabled();

    /**
     * Check if log logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF log logging with the specific delta is enabled.
     */
    boolean isLogEnabled(int delta);

    /**
     * Add a log logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _log(int delta, Throwable e, Object message);

    /**
     * Check if debug logging is enabled.
     *
     * @return <code>true</code> If debug logging is enabled.
     */
    boolean isDebugEnabled();

    /**
     * Check if debug logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF debug logging with the specific delta is enabled.
     */
    boolean isDebugEnabled(int delta);

    /**
     * Add a debug logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _debug(int delta, Throwable e, Object message);

    /**
     * Check if trace logging is enabled.
     *
     * @return <code>true</code> If trace logging is enabled.
     */
    boolean isTraceEnabled();

    /**
     * Check if trace logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF trace logging with the specific delta is enabled.
     */
    boolean isTraceEnabled(int delta);

    /**
     * Add a trace logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _trace(int delta, Throwable e, Object message);

    /**
     * Check if status logging is enabled.
     *
     * @return <code>true</code> If status logging is enabled.
     */
    boolean isStatusEnabled();

    /**
     * Check if status logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF status logging with the specific delta is enabled.
     */
    boolean isStatusEnabled(int delta);

    /**
     * Add a status logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _status(int delta, Throwable e, Object message);

    /**
     * Check if progress logging is enabled.
     *
     * @return <code>true</code> If progress logging is enabled.
     */
    boolean isProgressEnabled();

    /**
     * Check if progress logging with a delta verbose is enabled.
     *
     * @param delta
     *            The verbose level delta.
     * @return <code>true</code> IF progress logging with the specific delta is enabled.
     */
    boolean isProgressEnabled(int delta);

    /**
     * Add a progress logging with a delta verbose with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void _progress(int delta, Throwable e, Object message);

}
