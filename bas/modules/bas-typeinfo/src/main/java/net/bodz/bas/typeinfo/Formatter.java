package net.bodz.bas.typeinfo;

import java.beans.ExceptionListener;
import java.io.Writer;

import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.types.Negotiation;

public interface Formatter<T> {

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
     * <li><b>Mandatory</b> {@link Continuation}: context object used for continuation.
     * <li>Optional <code>char[]</code>: code table
     * <li>Optional {@link ExceptionListener}: error handler/recover
     * </ul>
     * 
     * @param object
     *            non-<code>null</code> value to be formatted.
     * @return non-<code>null</code> formatted string.
     */
    String format(T object, Negotiation negotiation)
            throws NegotiationException;

}
