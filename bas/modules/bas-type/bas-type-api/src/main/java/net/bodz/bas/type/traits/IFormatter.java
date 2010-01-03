package net.bodz.bas.type.traits;

import java.beans.ExceptionListener;
import java.io.Writer;

import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;

public interface IFormatter<T> {

    /**
     * @param object
     *            non-<code>null</code> value to be formatted.
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
     *            non-<code>null</code> value to be formatted.
     * @return non-<code>null</code> formatted string.
     */
    String format(T object, INegotiation negotiation)
            throws NegotiationException;

}
