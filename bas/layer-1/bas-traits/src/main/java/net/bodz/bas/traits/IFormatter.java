package net.bodz.bas.traits;

import java.beans.ExceptionListener;
import java.io.Writer;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;

public interface IFormatter<T> {

    /**
     * @param object
     *            (? extends <code>T</code>) non-<code>null</code> value to be formatted.
     * @return non-<code>null</code> formatted string.
     */
    String format(T object);

    /**
     * Negotiation:
     * <ul>
     * <li>Optional {@link Writer} to write into, the return value must be <code>null</code> if the
     * function is supported.
     * <li><b>Mandatory</b> {@link IContinuation}: context object used for continuation.
     * <li>Optional <code>char[]</code>: code table
     * <li>Optional {@link ExceptionListener}: error handler/recover
     * </ul>
     * 
     * @param object
     *            (? extends <code>T</code>) non-<code>null</code> value to be formatted.
     * @return non-<code>null</code> formatted string.
     */
    String format(T object, INegotiation negotiation)
            throws NegotiationException;

}
