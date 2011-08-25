package net.bodz.bas.log.api;

import javax.annotation.Generated;

import net.bodz.bas.log.ILogSink;

@Generated("By LoggerCG")
public interface Logger
        extends LoggerCompat {

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
     * Add a stderr logging.
     * 
     * @param message
     *            The logging message.
     */
    void stderr(Object message);

    /**
     * Add a stderr logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void stderr(Throwable e, Object message);

    /**
     * Add a stderr logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void stderr(int delta, Object message);

    /**
     * Add a stderr logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void stderr(int delta, Throwable e, Object message);

    /**
     * Add a stderr logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void stderr(Object... messageArray);

    /**
     * Add a stderr logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void stderr(Throwable e, Object... messageArray);

    /**
     * Add a stderr logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void stderr(int delta, Object... messageArray);

    /**
     * Add a stderr logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void stderr(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a stderr logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void stderrFormat(String format, Object... args);

    /**
     * Add formatted a stderr logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void stderrFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a stderr logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void stderrFormat(int delta, String format, Object... args);

    /**
     * Add formatted a stderr logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void stderrFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a stdout logging.
     * 
     * @param message
     *            The logging message.
     */
    void stdout(Object message);

    /**
     * Add a stdout logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void stdout(Throwable e, Object message);

    /**
     * Add a stdout logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void stdout(int delta, Object message);

    /**
     * Add a stdout logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void stdout(int delta, Throwable e, Object message);

    /**
     * Add a stdout logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void stdout(Object... messageArray);

    /**
     * Add a stdout logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void stdout(Throwable e, Object... messageArray);

    /**
     * Add a stdout logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void stdout(int delta, Object... messageArray);

    /**
     * Add a stdout logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void stdout(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a stdout logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void stdoutFormat(String format, Object... args);

    /**
     * Add formatted a stdout logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void stdoutFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a stdout logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void stdoutFormat(int delta, String format, Object... args);

    /**
     * Add formatted a stdout logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void stdoutFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a fatal logging.
     * 
     * @param message
     *            The logging message.
     */
    boolean fatal(Object message);

    /**
     * Add a fatal logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    boolean fatal(Throwable e, Object message);

    /**
     * Add a fatal logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    boolean fatal(int delta, Object message);

    /**
     * Add a fatal logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    boolean fatal(int delta, Throwable e, Object message);

    /**
     * Add a fatal logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    boolean fatal(Object... messageArray);

    /**
     * Add a fatal logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    boolean fatal(Throwable e, Object... messageArray);

    /**
     * Add a fatal logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    boolean fatal(int delta, Object... messageArray);

    /**
     * Add a fatal logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    boolean fatal(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a fatal logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    boolean fatalFormat(String format, Object... args);

    /**
     * Add formatted a fatal logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    boolean fatalFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a fatal logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    boolean fatalFormat(int delta, String format, Object... args);

    /**
     * Add formatted a fatal logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    boolean fatalFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a error logging.
     * 
     * @param message
     *            The logging message.
     */
    boolean error(Object message);

    /**
     * Add a error logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    boolean error(Throwable e, Object message);

    /**
     * Add a error logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    boolean error(int delta, Object message);

    /**
     * Add a error logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    boolean error(int delta, Throwable e, Object message);

    /**
     * Add a error logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    boolean error(Object... messageArray);

    /**
     * Add a error logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    boolean error(Throwable e, Object... messageArray);

    /**
     * Add a error logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    boolean error(int delta, Object... messageArray);

    /**
     * Add a error logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    boolean error(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a error logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    boolean errorFormat(String format, Object... args);

    /**
     * Add formatted a error logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    boolean errorFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a error logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    boolean errorFormat(int delta, String format, Object... args);

    /**
     * Add formatted a error logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    boolean errorFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a warn logging.
     * 
     * @param message
     *            The logging message.
     */
    void warn(Object message);

    /**
     * Add a warn logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void warn(Throwable e, Object message);

    /**
     * Add a warn logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void warn(int delta, Object message);

    /**
     * Add a warn logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void warn(int delta, Throwable e, Object message);

    /**
     * Add a warn logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void warn(Object... messageArray);

    /**
     * Add a warn logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void warn(Throwable e, Object... messageArray);

    /**
     * Add a warn logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void warn(int delta, Object... messageArray);

    /**
     * Add a warn logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void warn(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a warn logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void warnFormat(String format, Object... args);

    /**
     * Add formatted a warn logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void warnFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a warn logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void warnFormat(int delta, String format, Object... args);

    /**
     * Add formatted a warn logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void warnFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a log logging.
     * 
     * @param message
     *            The logging message.
     */
    void log(Object message);

    /**
     * Add a log logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void log(Throwable e, Object message);

    /**
     * Add a log logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void log(int delta, Object message);

    /**
     * Add a log logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void log(int delta, Throwable e, Object message);

    /**
     * Add a log logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void log(Object... messageArray);

    /**
     * Add a log logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void log(Throwable e, Object... messageArray);

    /**
     * Add a log logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void log(int delta, Object... messageArray);

    /**
     * Add a log logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void log(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a log logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void logFormat(String format, Object... args);

    /**
     * Add formatted a log logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void logFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a log logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void logFormat(int delta, String format, Object... args);

    /**
     * Add formatted a log logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void logFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a info logging.
     * 
     * @param message
     *            The logging message.
     */
    void info(Object message);

    /**
     * Add a info logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void info(Throwable e, Object message);

    /**
     * Add a info logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void info(int delta, Object message);

    /**
     * Add a info logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void info(int delta, Throwable e, Object message);

    /**
     * Add a info logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void info(Object... messageArray);

    /**
     * Add a info logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void info(Throwable e, Object... messageArray);

    /**
     * Add a info logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void info(int delta, Object... messageArray);

    /**
     * Add a info logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void info(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a info logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void infoFormat(String format, Object... args);

    /**
     * Add formatted a info logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void infoFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a info logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void infoFormat(int delta, String format, Object... args);

    /**
     * Add formatted a info logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void infoFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a debug logging.
     * 
     * @param message
     *            The logging message.
     */
    void debug(Object message);

    /**
     * Add a debug logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void debug(Throwable e, Object message);

    /**
     * Add a debug logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void debug(int delta, Object message);

    /**
     * Add a debug logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void debug(int delta, Throwable e, Object message);

    /**
     * Add a debug logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void debug(Object... messageArray);

    /**
     * Add a debug logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void debug(Throwable e, Object... messageArray);

    /**
     * Add a debug logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void debug(int delta, Object... messageArray);

    /**
     * Add a debug logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void debug(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a debug logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void debugFormat(String format, Object... args);

    /**
     * Add formatted a debug logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void debugFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a debug logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void debugFormat(int delta, String format, Object... args);

    /**
     * Add formatted a debug logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void debugFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a trace logging.
     * 
     * @param message
     *            The logging message.
     */
    void trace(Object message);

    /**
     * Add a trace logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void trace(Throwable e, Object message);

    /**
     * Add a trace logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void trace(int delta, Object message);

    /**
     * Add a trace logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void trace(int delta, Throwable e, Object message);

    /**
     * Add a trace logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void trace(Object... messageArray);

    /**
     * Add a trace logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void trace(Throwable e, Object... messageArray);

    /**
     * Add a trace logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void trace(int delta, Object... messageArray);

    /**
     * Add a trace logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void trace(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a trace logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void traceFormat(String format, Object... args);

    /**
     * Add formatted a trace logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void traceFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a trace logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void traceFormat(int delta, String format, Object... args);

    /**
     * Add formatted a trace logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void traceFormat(int delta, Throwable e, String format, Object... args);

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
     * Add a status logging.
     * 
     * @param message
     *            The logging message.
     */
    void status(Object message);

    /**
     * Add a status logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void status(Throwable e, Object message);

    /**
     * Add a status logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void status(int delta, Object message);

    /**
     * Add a status logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void status(int delta, Throwable e, Object message);

    /**
     * Add a status logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void status(Object... messageArray);

    /**
     * Add a status logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void status(Throwable e, Object... messageArray);

    /**
     * Add a status logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void status(int delta, Object... messageArray);

    /**
     * Add a status logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void status(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a status logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void statusFormat(String format, Object... args);

    /**
     * Add formatted a status logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void statusFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a status logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void statusFormat(int delta, String format, Object... args);

    /**
     * Add formatted a status logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void statusFormat(int delta, Throwable e, String format, Object... args);

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

    /**
     * Add a progress logging.
     * 
     * @param message
     *            The logging message.
     */
    void progress(Object message);

    /**
     * Add a progress logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void progress(Throwable e, Object message);

    /**
     * Add a progress logging with a delta verbose.
     * 
     * @param message
     *            The logging message.
     */
    void progress(int delta, Object message);

    /**
     * Add a progress logging with a delta verbose with an exception.
     * 
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    void progress(int delta, Throwable e, Object message);

    /**
     * Add a progress logging.
     * 
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void progress(Object... messageArray);

    /**
     * Add a progress logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void progress(Throwable e, Object... messageArray);

    /**
     * Add a progress logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void progress(int delta, Object... messageArray);

    /**
     * Add a progress logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    void progress(int delta, Throwable e, Object... messageArray);

    /**
     * Add formatted a progress logging.
     * 
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void progressFormat(String format, Object... args);

    /**
     * Add formatted a progress logging with an exception.
     * 
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void progressFormat(Throwable e, String format, Object... args);

    /**
     * Add formatted a progress logging with a delta verbose.
     * 
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void progressFormat(int delta, String format, Object... args);

    /**
     * Add formatted a progress logging with a delta verbose with an exception.
     * 
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    void progressFormat(int delta, Throwable e, String format, Object... args);

}
