package net.bodz.bas.potato.traits;

import java.util.Collection;

/**
 * This is also usable as member/inner type.
 */
public interface IType
        extends IElement {

    /**
     * Get the property map.
     * 
     * @return Non-<code>null</code> property map.
     */
    IPropertyMap getPropertyMap();

    /**
     * Get the method map.
     * 
     * @return Non-<code>null</code> method map.
     */
    IMethodMap getMethodMap();

    /**
     * Get the constructor map.
     * 
     * @return Non-<code>null</code> constructor map.
     */
    IConstructorMap getConstructorMap();

    /**
     * Get the event map.
     * 
     * @return Non-<code>null</code> event map.
     */
    IEventMap getEventMap();

    /**
     * Get the properties collection.
     * 
     * @return Non-<code>null</code> properties collection.
     */
    Collection<IProperty> getProperties();

    /**
     * Get the methods collection.
     * 
     * @return Non-<code>null</code> methods collection.
     */
    Collection<IMethod> getMethods();

    /**
     * Get the constructors collection.
     * 
     * @return Non-<code>null</code> constructors collection.
     */
    Collection<IConstructor> getConstructors();

    /**
     * Get the events collection.
     * 
     * @return Non-<code>null</code> events collection.
     */
    Collection<IEvent> getEvents();

    /**
     * Get property of specific name.
     * 
     * @return The property with the specific name, <code>null</code> if the property doesn't exist.
     */
    IProperty getProperty(String propertyName);

    /**
     * Get method of the specific signature.
     * 
     * @return The method with the specific signature, <code>null</code> if the method doesn't
     *         exist.
     */
    IMethod getMethod(String methodName, Class<?>... parameterTypes);

    /**
     * Get constructor of the specific signature.
     * 
     * @return The constructor with the specific signature, <code>null</code> if the constructor
     *         doesn't exist.
     */
    IConstructor getConstructor(Class<?>... parameterTypes);

    /**
     * Get event of specific name.
     * 
     * @return The event with the specific name, <code>null</code> if the event doesn't exist.
     */

    IEvent getEvent(String eventName);

}
