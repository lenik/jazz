package net.bodz.bas.text.rst;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.ProcessException;

public interface IElementHandler {

    IDataCodec codec = new DataCodec();

    /**
     * @return <code>true</code> if this attribute is handled, and should be dropped away.
     *         <code>false</code> if this attribute should be included in the enclosing element to
     *         be processed in the future.
     */
    boolean attribute(String name, String data)
            throws ProcessException, ParseException;

    IElementHandler beginChild(String name, String[] args)
            throws ProcessException;

    /**
     * @return <code>true</code> if this (child-) element is handled, and should be dropped away.
     *         <code>false</code> if this (child-) element should be included in the enclosing
     *         element to be processed in the future.
     */
    boolean endChild(IRstElement element)
            throws ProcessException;

    void complete(IRstElement element)
            throws ProcessException;

}
