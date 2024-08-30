package net.bodz.bas.fmt.flatf;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;

/**
 * @see IFlatfForm
 */
public interface IMergedFlatfForm {

    /**
     * Parse a single Flatf entry.
     */
    void parseAttribute(String attributeName, String extension, String text, IOptions options)
            throws ParseException;

    /**
     * Write Flatf entries for a value object.
     *
     * @param attributeName
     *            The root name. Implementations may add ".extension" to the name.
     * @throws FormatException
     *             If the value can't be converted to a specific text form.
     */
    void writeObject(IFlatfOutput out, String attributeName, IOptions options)
            throws IOException, FormatException;

}
