package net.bodz.bas.potato;

import java.beans.PropertyDescriptor;

public interface IPotatoProperty
        extends IPotatoElement {

    /**
     * @return non-<code>null</code>
     */
    IPotatoType<?> getDeclaringType();

    /**
     * @return non-<code>null</code> class of the property value.
     */
    Class<?> getType();

    /**
     * @throws NullPointerException
     *             If <code>instance</code> is <code>null</code>.
     * @throws ClassCastException
     *             If <code>instance</code> isn't of the declaring potato type
     */
    Object get(Object instance)
            throws PotatoException;

    /**
     * @throws NullPointerException
     *             If <code>instance</code> is <code>null</code>.
     * @throws ClassCastException
     *             If <code>instance</code> isn't of the declaring potato type
     * @throws IllegalArgumentException
     *             If <code>value</code> isn't instance of type of this property.
     */
    void set(Object instance, Object value)
            throws PotatoException;

    /**
     * @see PropertyDescriptor#isBound()
     */
    boolean isBound();

    /**
     * @see PropertyDescriptor#isConstrained()
     */
    boolean isConstrained();

}
