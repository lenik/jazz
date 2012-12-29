package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public interface ITagType {

    /**
     * Parse a javadoc tag into value object.
     * 
     * @param cont
     *            (Continuation) The previous parsed object for the tag with the same name.
     * @param string
     *            Original javadoc tag string.
     * @return If <code>cont</code> is not <code>null</code>, this method should return
     *         <code>cont</code>, otherwise a new value is created and returned.
     */
    Object parseJavadoc(Object cont, String string, INegotiation negotiation)
            throws ParseException;

    /**
     * Format the value object to Javadoc.
     * <p>
     * This method is useful if you are going to rewrite the Javadoc (i.e., javadoc normalization).
     * <p>
     * Javadoc tags of the same tag name are grouped here, by returning the array. For example, If
     * you want to format the value to multiple <code>&#64;author</code>s, then you should return an
     * array, each item for a single author text.
     * 
     * @param value
     * @return Javadoc tags of the same name. (name is invisible here)
     */
    String[] formatJavadoc(Object value, INegotiation negotiation)
            throws FormatException;

    /**
     * Parse a single Flatf entry.
     */
    Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException;

    /**
     * Write Flatf entries for a value object.
     */
    void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws FormatException, IOException;

}
