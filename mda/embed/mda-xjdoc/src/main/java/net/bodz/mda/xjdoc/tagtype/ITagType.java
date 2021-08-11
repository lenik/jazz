package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.c.type.AggregationEnum;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;

public interface ITagType {

    Class<?> getValueType();

    AggregationEnum getAggregationEnum();

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
    Object parseJavadoc(String tagNameSpec, Object cont, String string, IOptions options)
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
     * @param rootTagName
     *            The tag name prefix (without the dot).
     * @param value
     *            The value to be written, never be <code>null</code>.
     * @param writer
     *            The javadoc writer.
     * @throws FormatException
     *             If the value can't be converted to a specific text form.
     */
    void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, IOptions options)
            throws IOException, FormatException;

    /**
     * Parse a single Flatf entry.
     */
    Object parseEntry(Object cont, String suffix, String string, IOptions options)
            throws ParseException;

    /**
     * Write Flatf entries for a value object.
     *
     * @throws FormatterException
     *             If the value can't be converted to a specific text form.
     */
    void writeEntries(IFlatfOutput out, String prefix, Object value, IOptions options)
            throws IOException;

}
