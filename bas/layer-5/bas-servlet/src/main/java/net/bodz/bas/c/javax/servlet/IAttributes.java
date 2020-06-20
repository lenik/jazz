package net.bodz.bas.c.javax.servlet;

import java.util.Enumeration;

public interface IAttributes {

    /**
     * Returns the attribute with the given name, or <code>null</code> if there is no attribute by
     * that name.
     * 
     * A list of supported attributes can be retrieved using <code>getAttributeNames</code>.
     * 
     * <p>
     * The attribute is returned as a <code>java.lang.Object</code> or some subclass.
     * 
     * @param name
     *            a <code>String</code> specifying the name of the attribute
     * 
     * @return an <code>Object</code> containing the value of the attribute, or <code>null</code> if
     *         no attribute exists matching the given name
     * 
     * @see #getAttributeNames
     */
    Object getAttribute(String name);

    /**
     * Returns an <code>Enumeration</code> containing the attribute names.
     * 
     * Use the {@link #getAttribute} method with an attribute name to get the value of an attribute.
     * 
     * @return an <code>Enumeration</code> of attribute names
     * 
     * @see #getAttribute
     */
    Enumeration<String> getAttributeNames();

    /**
     * Binds an object to a given attribute name. If the name specified is already used for an
     * attribute, this method will replace the attribute with the new to the new attribute.
     * 
     * If a null value is passed, the effect is the same as calling <code>removeAttribute()</code>.
     * 
     * @param name
     *            a <code>String</code> specifying the name of the attribute
     * 
     * @param object
     *            an <code>Object</code> representing the attribute to be bound
     */
    void setAttribute(String name, Object object);

    /**
     * Removes the attribute with the given name. After removal, subsequent calls to
     * {@link #getAttribute} to retrieve the attribute's value will return <code>null</code>.
     * 
     * @param name
     *            a <code>String</code> specifying the name of the attribute to be removed
     */
    void removeAttribute(String name);

}
