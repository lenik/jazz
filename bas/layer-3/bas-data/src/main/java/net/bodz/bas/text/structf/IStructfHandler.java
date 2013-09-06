package net.bodz.bas.text.structf;

public interface IStructfHandler {

    /**
     * @return <code>true</code> if this attribute is handled, and should be dropped away.
     *         <code>false</code> if this attribute should be included in the enclosing element to
     *         be processed in the future.
     */
    boolean attribute(String name, String data)
            throws StructfHandlerException;

    IStructfHandler beginChild(String name, String[] args)
            throws StructfHandlerException;

    /**
     * @return <code>true</code> if this (child-) element is handled, and should be dropped away.
     *         <code>false</code> if this (child-) element should be included in the enclosing
     *         element to be processed in the future.
     */
    boolean endChild(IStructfElement element)
            throws StructfHandlerException;

    void element(IStructfElement element)
            throws StructfHandlerException;

}
