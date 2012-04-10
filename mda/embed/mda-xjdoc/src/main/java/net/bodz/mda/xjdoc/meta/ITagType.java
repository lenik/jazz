package net.bodz.mda.xjdoc.meta;

public interface ITagType {

    /**
     * Parse javadoc string into value object.
     * 
     * @param cont
     *            (Continuation) A previous parsed object for tags occurred many times.
     * @param text
     *            Original javadoc tag string.
     * @return If <code>cont</code> is not <code>null</code>, this method should return
     *         <code>cont</code>, otherwise a new value is created and returned.
     */
    Object parse(Object cont, String string);

    /**
     * Format the value object.
     * 
     * @param value
     * @return String array each for a javadoc annotation.
     */
    String[] format(Object value);

}
