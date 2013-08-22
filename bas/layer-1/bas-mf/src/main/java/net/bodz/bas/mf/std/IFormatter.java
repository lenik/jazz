package net.bodz.bas.mf.std;

import java.beans.ExceptionListener;
import java.io.Writer;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.rtx.INegotiation;

public interface IFormatter<T> {

    int mdaFeatureIndex = -1939991935; // IFormatter

    /**
     * @param object
     *            (? extends <code>T</code>) non-<code>null</code> value to be formatted.
     * @return non-<code>null</code> formatted string.
     * @throws FormatException
     *             If failed to format.
     */
    String format(T object)
            throws FormatException;

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
     * @throws FormatException
     *             If failed to format.
     */
    String format(T object, INegotiation negotiation)
            throws FormatException;

}
