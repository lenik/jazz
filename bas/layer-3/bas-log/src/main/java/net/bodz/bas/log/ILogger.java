package net.bodz.bas.log;

public interface ILogger
        extends
            IBaseLogger {

    /**
     * Add a stderr logging.
     *
     * @param message
     *            The logging message.
     */
    default void stderr(Object message) {
        _stderr(0, null, message);
    }

    /**
     * Add a stderr logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void stderr(Throwable e, Object message) {
        _stderr(0, e, message);
    }

    /**
     * Add a stderr logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _stderr(int delta, Object message) {
        _stderr(delta, null, message);
    }

    /**
     * Add a stderr logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void stderr(Object... messageArray) {
        _stderr(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a stderr logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void stderr(Throwable e, Object... messageArray) {
        _stderr(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a stderr logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _stderr(int delta, Object... messageArray) {
        _stderr(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _stderr(int delta, Throwable e, Object... messageArray) {
        _stderr(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a stderr logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void stderrf(String format, Object... args) {
        _stderr(0, null, LogUtils.format(format, args));
    }

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
    default void stderrf(Throwable e, String format, Object... args) {
        _stderr(0, e, LogUtils.format(format, args));
    }

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
    default void _stderrf(int delta, String format, Object... args) {
        _stderr(delta, format, LogUtils.format(format, args));
    }

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
    default void _stderrf(int delta, Throwable e, String format, Object... args) {
        _stderr(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a stdout logging.
     *
     * @param message
     *            The logging message.
     */
    default void stdout(Object message) {
        _stdout(0, null, message);
    }

    /**
     * Add a stdout logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void stdout(Throwable e, Object message) {
        _stdout(0, e, message);
    }

    /**
     * Add a stdout logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _stdout(int delta, Object message) {
        _stdout(delta, null, message);
    }

    /**
     * Add a stdout logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void stdout(Object... messageArray) {
        _stdout(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a stdout logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void stdout(Throwable e, Object... messageArray) {
        _stdout(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a stdout logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _stdout(int delta, Object... messageArray) {
        _stdout(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _stdout(int delta, Throwable e, Object... messageArray) {
        _stdout(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a stdout logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void stdoutf(String format, Object... args) {
        _stdout(0, null, LogUtils.format(format, args));
    }

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
    default void stdoutf(Throwable e, String format, Object... args) {
        _stdout(0, e, LogUtils.format(format, args));
    }

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
    default void _stdoutf(int delta, String format, Object... args) {
        _stdout(delta, format, LogUtils.format(format, args));
    }

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
    default void _stdoutf(int delta, Throwable e, String format, Object... args) {
        _stdout(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a fatal logging.
     *
     * @param message
     *            The logging message.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean fatal(Object message) {
        return _fatal(0, null, message);
    }

    /**
     * Add a fatal logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean fatal(Throwable e, Object message) {
        return _fatal(0, e, message);
    }

    /**
     * Add a fatal logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _fatal(int delta, Object message) {
        return _fatal(delta, null, message);
    }

    /**
     * Add a fatal logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean fatal(Object... messageArray) {
        return _fatal(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a fatal logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean fatal(Throwable e, Object... messageArray) {
        return _fatal(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a fatal logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _fatal(int delta, Object... messageArray) {
        return _fatal(delta, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a fatal logging with a delta verbose with an exception.
     *
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _fatal(int delta, Throwable e, Object... messageArray) {
        return _fatal(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a fatal logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean fatalf(String format, Object... args) {
        return _fatal(0, null, LogUtils.format(format, args));
    }

    /**
     * Add formatted a fatal logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean fatalf(Throwable e, String format, Object... args) {
        return _fatal(0, e, LogUtils.format(format, args));
    }

    /**
     * Add formatted a fatal logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _fatalf(int delta, String format, Object... args) {
        return _fatal(delta, format, LogUtils.format(format, args));
    }

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
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _fatalf(int delta, Throwable e, String format, Object... args) {
        return _fatal(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a error logging.
     *
     * @param message
     *            The logging message.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean error(Object message) {
        return _error(0, null, message);
    }

    /**
     * Add a error logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean error(Throwable e, Object message) {
        return _error(0, e, message);
    }

    /**
     * Add a error logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _error(int delta, Object message) {
        return _error(delta, null, message);
    }

    /**
     * Add a error logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean error(Object... messageArray) {
        return _error(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a error logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean error(Throwable e, Object... messageArray) {
        return _error(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a error logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _error(int delta, Object... messageArray) {
        return _error(delta, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a error logging with a delta verbose with an exception.
     *
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _error(int delta, Throwable e, Object... messageArray) {
        return _error(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a error logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean errorf(String format, Object... args) {
        return _error(0, null, LogUtils.format(format, args));
    }

    /**
     * Add formatted a error logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean errorf(Throwable e, String format, Object... args) {
        return _error(0, e, LogUtils.format(format, args));
    }

    /**
     * Add formatted a error logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _errorf(int delta, String format, Object... args) {
        return _error(delta, format, LogUtils.format(format, args));
    }

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
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    default boolean _errorf(int delta, Throwable e, String format, Object... args) {
        return _error(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a warn logging.
     *
     * @param message
     *            The logging message.
     */
    default void warn(Object message) {
        _warn(0, null, message);
    }

    /**
     * Add a warn logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void warn(Throwable e, Object message) {
        _warn(0, e, message);
    }

    /**
     * Add a warn logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _warn(int delta, Object message) {
        _warn(delta, null, message);
    }

    /**
     * Add a warn logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void warn(Object... messageArray) {
        _warn(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a warn logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void warn(Throwable e, Object... messageArray) {
        _warn(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a warn logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _warn(int delta, Object... messageArray) {
        _warn(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _warn(int delta, Throwable e, Object... messageArray) {
        _warn(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a warn logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void warnf(String format, Object... args) {
        _warn(0, null, LogUtils.format(format, args));
    }

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
    default void warnf(Throwable e, String format, Object... args) {
        _warn(0, e, LogUtils.format(format, args));
    }

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
    default void _warnf(int delta, String format, Object... args) {
        _warn(delta, format, LogUtils.format(format, args));
    }

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
    default void _warnf(int delta, Throwable e, String format, Object... args) {
        _warn(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a mesg logging.
     *
     * @param message
     *            The logging message.
     */
    default void mesg(Object message) {
        _mesg(0, null, message);
    }

    /**
     * Add a mesg logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void mesg(Throwable e, Object message) {
        _mesg(0, e, message);
    }

    /**
     * Add a mesg logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _mesg(int delta, Object message) {
        _mesg(delta, null, message);
    }

    /**
     * Add a mesg logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void mesg(Object... messageArray) {
        _mesg(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a mesg logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void mesg(Throwable e, Object... messageArray) {
        _mesg(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a mesg logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _mesg(int delta, Object... messageArray) {
        _mesg(delta, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a mesg logging with a delta verbose with an exception.
     *
     * @param delta
     *            The verbose level delta.
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _mesg(int delta, Throwable e, Object... messageArray) {
        _mesg(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a mesg logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void mesgf(String format, Object... args) {
        _mesg(0, null, LogUtils.format(format, args));
    }

    /**
     * Add formatted a mesg logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void mesgf(Throwable e, String format, Object... args) {
        _mesg(0, e, LogUtils.format(format, args));
    }

    /**
     * Add formatted a mesg logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void _mesgf(int delta, String format, Object... args) {
        _mesg(delta, format, LogUtils.format(format, args));
    }

    /**
     * Add formatted a mesg logging with a delta verbose with an exception.
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
    default void _mesgf(int delta, Throwable e, String format, Object... args) {
        _mesg(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a info logging.
     *
     * @param message
     *            The logging message.
     */
    default void info(Object message) {
        _info(0, null, message);
    }

    /**
     * Add a info logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void info(Throwable e, Object message) {
        _info(0, e, message);
    }

    /**
     * Add a info logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _info(int delta, Object message) {
        _info(delta, null, message);
    }

    /**
     * Add a info logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void info(Object... messageArray) {
        _info(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a info logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void info(Throwable e, Object... messageArray) {
        _info(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a info logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _info(int delta, Object... messageArray) {
        _info(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _info(int delta, Throwable e, Object... messageArray) {
        _info(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a info logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void infof(String format, Object... args) {
        _info(0, null, LogUtils.format(format, args));
    }

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
    default void infof(Throwable e, String format, Object... args) {
        _info(0, e, LogUtils.format(format, args));
    }

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
    default void _infof(int delta, String format, Object... args) {
        _info(delta, format, LogUtils.format(format, args));
    }

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
    default void _infof(int delta, Throwable e, String format, Object... args) {
        _info(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a log logging.
     *
     * @param message
     *            The logging message.
     */
    default void log(Object message) {
        _log(0, null, message);
    }

    /**
     * Add a log logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void log(Throwable e, Object message) {
        _log(0, e, message);
    }

    /**
     * Add a log logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _log(int delta, Object message) {
        _log(delta, null, message);
    }

    /**
     * Add a log logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void log(Object... messageArray) {
        _log(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a log logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void log(Throwable e, Object... messageArray) {
        _log(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a log logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _log(int delta, Object... messageArray) {
        _log(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _log(int delta, Throwable e, Object... messageArray) {
        _log(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a log logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void logf(String format, Object... args) {
        _log(0, null, LogUtils.format(format, args));
    }

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
    default void logf(Throwable e, String format, Object... args) {
        _log(0, e, LogUtils.format(format, args));
    }

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
    default void _logf(int delta, String format, Object... args) {
        _log(delta, format, LogUtils.format(format, args));
    }

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
    default void _logf(int delta, Throwable e, String format, Object... args) {
        _log(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a debug logging.
     *
     * @param message
     *            The logging message.
     */
    default void debug(Object message) {
        _debug(0, null, message);
    }

    /**
     * Add a debug logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void debug(Throwable e, Object message) {
        _debug(0, e, message);
    }

    /**
     * Add a debug logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _debug(int delta, Object message) {
        _debug(delta, null, message);
    }

    /**
     * Add a debug logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void debug(Object... messageArray) {
        _debug(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a debug logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void debug(Throwable e, Object... messageArray) {
        _debug(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a debug logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _debug(int delta, Object... messageArray) {
        _debug(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _debug(int delta, Throwable e, Object... messageArray) {
        _debug(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a debug logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void debugf(String format, Object... args) {
        _debug(0, null, LogUtils.format(format, args));
    }

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
    default void debugf(Throwable e, String format, Object... args) {
        _debug(0, e, LogUtils.format(format, args));
    }

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
    default void _debugf(int delta, String format, Object... args) {
        _debug(delta, format, LogUtils.format(format, args));
    }

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
    default void _debugf(int delta, Throwable e, String format, Object... args) {
        _debug(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a trace logging.
     *
     * @param message
     *            The logging message.
     */
    default void trace(Object message) {
        _trace(0, null, message);
    }

    /**
     * Add a trace logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void trace(Throwable e, Object message) {
        _trace(0, e, message);
    }

    /**
     * Add a trace logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _trace(int delta, Object message) {
        _trace(delta, null, message);
    }

    /**
     * Add a trace logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void trace(Object... messageArray) {
        _trace(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a trace logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void trace(Throwable e, Object... messageArray) {
        _trace(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a trace logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _trace(int delta, Object... messageArray) {
        _trace(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _trace(int delta, Throwable e, Object... messageArray) {
        _trace(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a trace logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void tracef(String format, Object... args) {
        _trace(0, null, LogUtils.format(format, args));
    }

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
    default void tracef(Throwable e, String format, Object... args) {
        _trace(0, e, LogUtils.format(format, args));
    }

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
    default void _tracef(int delta, String format, Object... args) {
        _trace(delta, format, LogUtils.format(format, args));
    }

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
    default void _tracef(int delta, Throwable e, String format, Object... args) {
        _trace(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a status logging.
     *
     * @param message
     *            The logging message.
     */
    default void status(Object message) {
        _status(0, null, message);
    }

    /**
     * Add a status logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void status(Throwable e, Object message) {
        _status(0, e, message);
    }

    /**
     * Add a status logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _status(int delta, Object message) {
        _status(delta, null, message);
    }

    /**
     * Add a status logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void status(Object... messageArray) {
        _status(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a status logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void status(Throwable e, Object... messageArray) {
        _status(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a status logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _status(int delta, Object... messageArray) {
        _status(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _status(int delta, Throwable e, Object... messageArray) {
        _status(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a status logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void statusf(String format, Object... args) {
        _status(0, null, LogUtils.format(format, args));
    }

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
    default void statusf(Throwable e, String format, Object... args) {
        _status(0, e, LogUtils.format(format, args));
    }

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
    default void _statusf(int delta, String format, Object... args) {
        _status(delta, format, LogUtils.format(format, args));
    }

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
    default void _statusf(int delta, Throwable e, String format, Object... args) {
        _status(delta, e, LogUtils.format(format, args));
    }

    /**
     * Add a progress logging.
     *
     * @param message
     *            The logging message.
     */
    default void progress(Object message) {
        _progress(0, null, message);
    }

    /**
     * Add a progress logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param message
     *            The logging message.
     */
    default void progress(Throwable e, Object message) {
        _progress(0, e, message);
    }

    /**
     * Add a progress logging with a delta verbose.
     *
     * @param message
     *            The logging message.
     */
    default void _progress(int delta, Object message) {
        _progress(delta, null, message);
    }

    /**
     * Add a progress logging.
     *
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void progress(Object... messageArray) {
        _progress(0, null, LogUtils.concat(messageArray));
    }

    /**
     * Add a progress logging with an exception.
     *
     * @param e
     *            The exception object.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void progress(Throwable e, Object... messageArray) {
        _progress(0, e, LogUtils.concat(messageArray));
    }

    /**
     * Add a progress logging with a delta verbose.
     *
     * @param delta
     *            The verbose level delta.
     * @param messageArray
     *            The logging message in pieces, will be concated.
     */
    default void _progress(int delta, Object... messageArray) {
        _progress(delta, null, LogUtils.concat(messageArray));
    }

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
    default void _progress(int delta, Throwable e, Object... messageArray) {
        _progress(delta, e, LogUtils.concat(messageArray));
    }

    /**
     * Add formatted a progress logging.
     *
     * @param format
     *            The message format.
     * @param args
     *            The arguments to the format.
     */
    default void progressf(String format, Object... args) {
        _progress(0, null, LogUtils.format(format, args));
    }

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
    default void progressf(Throwable e, String format, Object... args) {
        _progress(0, e, LogUtils.format(format, args));
    }

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
    default void _progressf(int delta, String format, Object... args) {
        _progress(delta, format, LogUtils.format(format, args));
    }

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
    default void _progressf(int delta, Throwable e, String format, Object... args) {
        _progress(delta, e, LogUtils.format(format, args));
    }

}
