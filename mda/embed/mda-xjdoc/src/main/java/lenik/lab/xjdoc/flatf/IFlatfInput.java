package lenik.lab.xjdoc.flatf;

import java.io.IOException;
import java.text.ParseException;

public interface IFlatfInput {

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
