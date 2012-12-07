package net.bodz.bas.repr.naming;

import java.util.Collection;

public interface INamedNode
        extends INamed/* , IOperational */{

    /**
     * The priority is used for reversed object lookup.
     * 
     * @return The less number the higher priority is.
     */
    int getPriority();

    /**
     * Get the parent node.
     * 
     * @return <code>null</code> if this node doesn't have a parent.
     */
    INamedNode getParent();

    /**
     * Re-attach the parent node.
     * 
     * @param parent
     *            The new parent.
     */
    void setParent(INamedNode parent);

    /**
     * Get the base type for the immediate children nodes.
     * 
     * @return Non-<code>null</code> type of the children nodes.
     */
    Class<?> getChildType();

    /**
     * Check if the child object is managed by this node.
     * 
     * @param obj
     *            child object to be checked
     * @return <code>true</code> if the child object is owned by this node.
     */
    boolean containsChild(Object obj);

    /**
     * Get managed node with specific name.
     * 
     * @param childName
     *            A location component.
     * @return Non-<code>null</code> object located. Returns <code>null</code> if the location isn't
     *         recognized, and the next locator for the same type should be checked.
     */
    Object getChild(String childName);

    /**
     * Get the URI location component for the object.
     * 
     * @param childObj
     *            Non-<code>null</code> object.
     * @return Location string. Returns <code>null</code> if specified <code>obj</code> is not owned
     *         by this locator, and other locators for the same type should be checked.
     * @see ReverseLookupRegistry#getLocatorForObject(Object)
     */
    String findChild(Object childObj);

    /**
     * Get all children.
     * 
     * @return Non-<code>null</code> children iterable.
     */
    Collection<?> getChildren();

    /**
     * Get all children's path names.
     * 
     * @return Non-<code>null</code> string collection of child names.
     */
    Collection<String> getChildrenNames();

}