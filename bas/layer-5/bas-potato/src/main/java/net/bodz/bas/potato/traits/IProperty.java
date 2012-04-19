package net.bodz.bas.potato.traits;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;

public interface IProperty
        extends IElement {

    /**
     * The property type.
     * 
     * @return Type of the property, never <code>null</code>.
     */
    Class<?> getType();

    /**
     * Get the property value from a particular instance.
     * 
     * @throws NullPointerException
     *             If <code>instance</code> is <code>null</code> and the property is not static.
     * @throws ClassCastException
     *             If <code>instance</code> isn't of the declaring potato type
     */
    Object get(Object instance)
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
    void set(Object instance, Object value)
            throws ReflectiveOperationException;

    /**
     * A bound property can cause property change event.
     * 
     * @return <code>true</code> If this is a bound property.
     * @see PropertyChangeListener
     * @see PropertyChangeEvent
     */
    boolean isBound();

    /**
     * 
     * A constrained property can cause vetoable property change event. *
     * 
     * @return <code>true</code> If this is a constrained property.
     * @see VetoableChangeListener
     * @see PropertyChangeEvent
     */
    boolean isConstrained();

}
