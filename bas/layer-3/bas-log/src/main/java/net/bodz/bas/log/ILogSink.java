package net.bodz.bas.log;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.log.impl.NullLogSink;
import net.bodz.bas.log.message.ArrayJoinMessage;
import net.bodz.bas.log.message.StringFormatMessage;

/**
 * Features:
 * 
 * <ul>
 * <li>Short function names ({@link #p(Object)}, {@link #_(Object)}, etc.)
 * <li>{@link IPrintOut} functions are optional to the underlying.
 * </ul>
 */
public interface ILogSink
        extends ITreeOut {

    /**
     * Device log function, discard any verbose level.
     * 
     * @throws NullPointerException
     *             If <code>event</code> is <code>null</code>.
     */
    void log(ILogEntry entry);

    /**
     * The same as {@link #log(ILogEntry)} with message only {@link LogEntry}.
     */
    void logMessage(Object message);

    /**
     * The same as {@link #log(ILogEntry)} with composed {@link LogEntry}.
     */
    void logException(Object message, Throwable exception);

    /**
     * The most simple function for optimization.
     */
    void p(String message);

    void p(Object message);

    /**
     * @see ArrayJoinMessage
     */
    void p(Object... messagePieces);

    /**
     * @param exception
     *            Can be <code>null</code> if no exception.
     */
    void p(Throwable exception, Object message);

    /**
     * @param exception
     *            Can be <code>null</code> if no exception.
     * @see ArrayJoinMessage
     */
    void p(Throwable exception, Object... messagePieces);

    /**
     * @see StringFormatMessage
     */
    void f(String format, Object... args);

    /**
     * @param exception
     *            Can be <code>null</code> if no exception.
     * @see StringFormatMessage
     */
    void f(Throwable exception, String format, Object... args);

    /**
     * Message may be buffered, without new line.
     */
    void _(Object message);

    /**
     * @see ArrayJoinMessage
     */
    void _(Object... messagePieces);

    /**
     * All buffered messages composed to a log entry. If there is none, do nothing.
     */
    void _done();

    /**
     * All buffered messages composed to a log entry. If there is none, do nothing.
     * 
     * @param exception
     *            Can be <code>null</code> if no exception.
     */
    void _done(Throwable exception);

    NullLogSink NULL = new NullLogSink();

}
