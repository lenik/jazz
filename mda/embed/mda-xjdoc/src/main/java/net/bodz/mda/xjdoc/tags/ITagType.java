package net.bodz.mda.xjdoc.tags;

import java.io.IOException;

import net.bodz.bas.lang.negotiation.INegotiation;
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
    Object parseJavadoc(Object cont, String string, INegotiation negotiation);

    /**
     * Format the value object.
     * 
     * @param value
     * @return Javadoc tags of the same name. (name is invisible here)
     */
    String[] formatJavadoc(Object value, INegotiation negotiation);

    /**
     * Parse a single Flatf entry.
     */
    Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation);

    /**
     * Write Flatf entries for a value object.
     */
    void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws IOException;

}
