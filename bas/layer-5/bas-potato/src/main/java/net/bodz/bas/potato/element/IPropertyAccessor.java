package net.bodz.bas.potato.element;

public interface IPropertyAccessor {

    Class<?> getPropertyClass();

    IType getPropertyType();

    /**
     * Get the property value from a particular instance.
     *
     * @throws NullPointerException If <code>instance</code> is <code>null</code> and the property is not static.
     * @throws ClassCastException   If <code>instance</code> isn't of the declaring potato type
     */
    Object read(Object instance)
            throws PropertyReadException;

    /**
     * Set the property value to a particular instance.
     *
     * @throws NullPointerException     If <code>instance</code> is <code>null</code> and the property is not static.
     * @throws ClassCastException       If <code>instance</code> isn't of the declaring potato type
     * @throws IllegalArgumentException If <code>value</code> isn't instance of type of this property.
     */
    void write(Object instance, Object value)
            throws PropertyWriteException;

}
