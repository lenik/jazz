package net.bodz.bas.potato.traits;

import java.beans.PropertyDescriptor;

import net.bodz.bas.util.event.IPropertyChangeListener;

public interface IProperty
        extends IElement {

    /**
     * The property type.
     * 
     * @return Type of the property, never <code>null</code>.
     */
    Class<?> getPropertyType();

    boolean isReadable();

    boolean isWritable();

    /**
     * Get the property value from a particular instance.
     * 
     * @throws NullPointerException
     *             If <code>instance</code> is <code>null</code> and the property is not static.
     * @throws ClassCastException
     *             If <code>instance</code> isn't of the declaring potato type
     */
    Object getValue(Object instance)
            throws ReflectiveOperationException;

    /**
     * Set the property value to a particular instance.
     * 
     * @throws NullPointerException
     *             If <code>instance</code> is <code>null</code> and the property is not static.
     * @throws ClassCastException
     *             If <code>instance</code> isn't of the declaring potato type
     * @throws IllegalArgumentException
     *             If <code>value</code> isn't instance of type of this property.
     */
    void setValue(Object instance, Object value)
            throws ReflectiveOperationException;

    /**
     * A bound property can cause property change event.
     * 
     * @return <code>true</code> If this is a bound property.
     * @see PropertyDescriptor#isBound()
     */
    boolean isPropertyChangeSource();

    void addPropertyChangeListener(Object instance, IPropertyChangeListener listener);

    void addPropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener);

    void removePropertyChangeListener(Object instance, IPropertyChangeListener listener);

    void removePropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener);

}
