package net.bodz.bas.log;

import net.bodz.bas.log.objects.ErrorLogEvent;
import net.bodz.bas.log.objects.ILogEvent;
import net.bodz.bas.log.objects.IMessage;
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
    void p(ILogEvent event);

    void p(Object message);

    /**
     * @see IMessage
     */
    void p(Object... messagePieces);

    /**
     * @param exception
     *            Set to <code>null</code> if no exception.
     * @see ErrorLogEvent
     */
    void p(Throwable exception, Object message);

    void p_(Object message);

    /**
     * @param exception
     *            Set to <code>null</code> if no exception.
     * @see ErrorLogEvent
     */
    void p_(Throwable exception, Object message);

    /**
     * @see IMessage
     */
    void p_(Object... messagePieces);

    /**
     * @param exception
     *            Set to <code>null</code> if no exception.
     * @see IMessage
     * @see ErrorLogEvent
     */
    void p_(Throwable exception, Object... messagePieces);

    /**
     * @param exception
     *            Set to <code>null</code> if no exception.
     * @see ErrorLogEvent
     */
    void p_(Throwable exception);

    /**
     * @see StringFormatMessage
     */
    void f(String format, Object... args);

    /**
     * @param exception
     *            Set to <code>null</code> if no exception.
     * @see ErrorLogEvent
     * @see StringFormatMessage
     */
    void f(Throwable exception, String format, Object... args);

}
