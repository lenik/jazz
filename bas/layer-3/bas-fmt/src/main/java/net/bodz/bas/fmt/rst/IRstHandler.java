package net.bodz.bas.fmt.rst;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.api.IDataCodec;
import net.bodz.bas.fmt.api.StdDataCodec;

/**
 * @see RstElement
 */
public interface IRstHandler {

    IDataCodec codec = new StdDataCodec();

    /**
     * @return <code>true</code> if this attribute is handled, and should be dropped away.
     *         <code>false</code> if this attribute should be included in the enclosing element to
     *         be processed in the future.
     */
    boolean attribute(String name, String data)
            throws ParseException, ElementHandlerException;

    /**
     * Called when the child element begins.
     */
    IRstHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException;

    /**
     * Post-process child element.
     *
     * @param element
     *            The child element, contains unprocessed attributes.
     * @return <code>true</code> if this (child-) element is handled, and should be dropped away.
     *         <code>false</code> if this (child-) element should be included in the enclosing
     *         element to be processed in the future.
     */
    default boolean endChild(IRstElement element)
            throws ElementHandlerException {
        return true;
    }

    default void complete(IRstElement element)
            throws ElementHandlerException {
        return;
    }

}
