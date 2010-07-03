package net.bodz.bas.log;

import net.bodz.bas.log.objects.ArrayJoinMessage;
import net.bodz.bas.log.objects.ILogEntry;
import net.bodz.bas.log.objects.StringFormatMessage;
import net.bodz.bas.sio.indent.IIndentedCharOut;

public interface ILogSink
        extends IIndentedCharOut {

    int LEVEL_CRITICAL = -20000;
    int LEVEL_LESS = -10000;
    int LEVEL_DEFAULT = 0;
    int LEVEL_MORE = 10000;

    int LEVEL_STEPLEN = 10000;

    int getVerboseLevel();

    void setVerboseLevel(int level);

    /**
     * @throws NullPointerException
     *             If <code>event</code> is <code>null</code>.
     */
    void p(ILogEntry entry);

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

    void p_(Object message);

    /**
     * @see ArrayJoinMessage
     */
    void p_(Object... messagePieces);

    /**
     * @param exception
     *            Can be <code>null</code> if no exception.
     */
    void p_(Throwable exception);

    /**
     * @param exception
     *            Can be <code>null</code> if no exception.
     */
    void p_(Throwable exception, Object message);

    /**
     * @param exception
     *            Can be <code>null</code> if no exception.
     * @see ArrayJoinMessage
     */
    void p_(Throwable exception, Object... messagePieces);

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

}
