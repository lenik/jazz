package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.model.javadoc.IJavadocWriter;

public interface IJavadocForm {

    /**
     * Parse a javadoc tag into value object.
     *
     * @param javadoc
     *            Original javadoc tag string.
     */
    void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
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
     * @param tagName
     *            The root tag name
     * @param value
     *            The value to be written, never be <code>null</code>.
     * @param writer
     *            The javadoc writer.
     * @throws FormatException
     *             If the value can't be converted to a specific text form.
     */
    void writeJavadoc(String tagName, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException;

}
