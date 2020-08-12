package net.bodz.bas.typer.std;

import com.googlecode.openbeans.ExceptionListener;
import java.io.Writer;

import net.bodz.bas.err.FormatterException;
import net.bodz.bas.rtx.IOptions;

public interface IFormatter<T>
        extends IStdTyper {

    int typerIndex = 0x07155834; // IFormatter

    /**
     * @param object
     *            (? extends <code>T</code>) non-<code>null</code> value to be formatted.
     * @return non-<code>null</code> formatted string.
     * @throws FormatterException
     *             If the object can't be converted to a specific text form.
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
     * @throws FormatterException
     *             If the object can't be converted to a specific text form.
     */
    String format(T object, IOptions options);

}
