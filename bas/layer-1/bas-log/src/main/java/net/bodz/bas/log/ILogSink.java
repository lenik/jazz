package net.bodz.bas.log;

import net.bodz.bas.log.message.ArrayJoinMessage;
import net.bodz.bas.log.message.StringFormatMessage;
import net.bodz.bas.sio.IPrintCharOut;
import net.bodz.bas.sio.indent.IIndentedCharOut;

/**
 * Features:
 * 
 * <ul>
 * <li>Short function names ({@link #p(Object)}, {@link #_(Object)}, etc.)
 * <li>{@link IPrintCharOut} functions are optional to the underlying.
 * </ul>
 */
public interface ILogSink
        extends IIndentedCharOut {

    /**
     * Device log function, discard any verbose level.
     * 
     * @throws NullPointerException
     *             If <code>event</code> is <code>null</code>.
     */
    void log(ILogEntry entry);

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

}
