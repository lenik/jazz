package net.bodz.mda.xjdoc.meta;

import java.io.IOException;

import net.bodz.bas.text.flatf.IFlatfOutput;

public interface ITagType {

    // ITagType negotiate(INegotiation negotiation)
    // throws NegotiationException;

    /**
     * Parse a javadoc tag into value object.
     * 
     * @param cont
     *            (Continuation) A previous parsed object for tags occurred many times.
     * @param string
     *            Original javadoc tag string.
     * @return If <code>cont</code> is not <code>null</code>, this method should return
     *         <code>cont</code>, otherwise a new value is created and returned.
     */
    Object parseJavadoc(Object cont, String string);

    /**
     * Format the value object.
     * 
     * @param value
     * @return Javadoc tags of the same name. (name is invisible here)
     */
    String[] formatJavadoc(Object value);

    Object parseAttribute(Object cont, String suffix, String string);

    void writeAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException;

}
