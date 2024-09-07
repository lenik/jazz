package net.bodz.bas.typer.std;

import java.io.Writer;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.RuntimizedException;
import net.bodz.bas.rtx.IOptions;

public interface IFormatter<T>
        extends
            IStdTyper {

    int typerIndex = 0x07155834; // IFormatter

    /**
     * @param object
     *            (? extends <code>T</code>) non-<code>null</code> value to be formatted.
     * @return non-<code>null</code> formatted string.
     * @throws FormatException
     *             If the object can't be converted to a specific text form.
     */
    default String format(T object)
            throws FormatException {
        return format(object, IOptions.NULL);
    }

    /**
     */
    String DEFAULT_FALLBACK = "error";

    default String _format(T object) {
        try {
            return format(object);
        } catch (FormatException e) {
            throw new RuntimizedException(e.getMessage(), e);
        }
    }

    default String format(T object, String fallback) {
        try {
            return format(object, IOptions.NULL);
        } catch (FormatException e) {
            return fallback;
        }
    }

    /**
     * Negotiation:
     * <ul>
     * <li>Optional {@link Writer} to write into, the return value must be <code>null</code> if the
     * function is supported.
     * <li><b>Mandatory</b> {@link IContinuation}: context object used for continuation.
     * <li>Optional <code>char[]</code>: code table
     * <li>Optional {@link IExceptionListener}: error handler/recover
     * </ul>
     *
     * @param object
     *            (? extends <code>T</code>) non-<code>null</code> value to be formatted.
     * @return non-<code>null</code> formatted string.
     * @throws FormatException
     *             If the object can't be converted to a specific text form.
     */
    String format(T object, IOptions options)
            throws FormatException;

    default String format(T object, IOptions options, String fallback) {
        try {
            return format(object, options);
        } catch (FormatException e) {
            return fallback;
        }
    }

}
