package net.bodz.bas.fmt.rst;

import java.io.Closeable;
import java.io.IOException;

import net.bodz.bas.err.ParseException;

public interface IRstInput
        extends Closeable {

    /** element arg arg ... { */
    int ELEMENT_BEGIN = 1;

    /** } */
    int ELEMENT_END = 2;

    /** name = data */
    int ATTRIBUTE = 3;

    /** End-Of-File */
    int EOF = -1;

    /**
     * @see #ELEMENT_BEGIN
     * @see #ELEMENT_END
     * @see #ATTRIBUTE
     * @see #EOF
     */
    int next()
            throws ParseException, IOException;

    /**
     * Get the name of the current element.
     * 
     * @return <code>null</code> if current element is not set.
     */
    String getElementName();

    String[] getElementArguments();

    /**
     * Get the name of the current attribute.
     * 
     * @return <code>null</code> if current attribute is not set.
     */
    String getAttributeName();

    /**
     * Get the data of the current attribute.
     * 
     * @return <code>null</code> if current attribute is not set.
     */
    String getAttributeData();

}
