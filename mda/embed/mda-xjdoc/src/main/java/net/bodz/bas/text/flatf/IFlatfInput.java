package net.bodz.bas.text.flatf;

import java.io.IOException;
import java.text.ParseException;

public interface IFlatfInput {

    /** Preprocessing Instruction */
    int PI = 1;

    /** [section-name] */
    int SECTION_BEGIN = 2;

    /** key.name = value string */
    int ATTRIBUTE = 3;

    /** End-Of-File */
    int EOF = -1;

    /**
     * @see #SECTION_BEGIN
     * @see #ATTRIBUTE
     * @see #EOF
     */
    int next()
            throws ParseException, IOException;

    /**
     * Find the next section.
     * 
     * @return <code>null</code> if no more section.
     */
    boolean nextSection()
            throws ParseException, IOException;

    /**
     * Fetch an attribute with-in the current section.
     * 
     * @return <code>null</code> if no more attribute with-in the current section.
     */
    boolean nextAttribute()
            throws ParseException, IOException;

    /**
     * Get the last preprocessing instruction.
     * 
     * @return <code>null</code> if no preprocessing instruction is read.
     * 
     */
    String getPiText();

    /**
     * Get the name of the current section.
     * 
     * @return <code>null</code> if current section is not set.
     */
    String getSectionName();

    /**
     * Get the name of the current attribute.
     * 
     * @return <code>null</code> if current attribute is not set.
     */
    String getAttributeName();

    /**
     * Get the value text of the current attribute.
     * 
     * @return <code>null</code> if current attribute is not set.
     */
    String getAttributeText();

}
