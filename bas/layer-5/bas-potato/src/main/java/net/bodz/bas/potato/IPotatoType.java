package net.bodz.bas.potato;

import java.util.Collection;

public interface IPotatoType<T>
        extends IPotatoElement { // , ITypeTraits<T> {

    Class<T> getJavaType();

    /**
     * (Reserved)
     * 
     * @return <code>null</code> If no traits available.
     */
    // ITypeTraits<?> getTraits();

    Collection<? extends IPotatoType<?>> getInheritedPotatos();

    /**
     * @return Empty {@link Collection} if no property exists.
     */
    Collection<? extends IPotatoProperty> getProperties();

    /**
     * @return <code>null</code> If the specified property isn't existed.
     */
    IPotatoProperty getProperty(String propertyName);

    /**
     * @return <code>null</code> If no default property.
     */
    IPotatoProperty getDefaultProperty();

    /**
     * @return Empty {@link Collection} if no method exists.
     */
    Collection<? extends IPotatoMethod> getMethods();

    /**
     * @return <code>null</code> If specified method isn't existed.
     */
    IPotatoMethod getMethod(String methodName, Class<?>... parameterTypes);

    /**
     * @return Empty {@link Collection} if no event exists.
     */
    Collection<? extends IPotatoEvent> getEvents();

    /**
     * @return <code>null</code> If specified event isn't existed.
     */
    IPotatoEvent getEvent(String eventName);

    /**
     * @throws NullPointerException
     *             If <code>propertyName</code> is <code>null</code>.
     * @throws PotatoException
     *             If specified propertyName isn't existed.
     * @throws PotatoTargetException
     */
    Object getPropertyValue(Object instance, String propertyName)
            throws PotatoException, NoSuchPotatoPropertyException, PotatoTargetException;

    /**
     * @throws NullPointerException
     *             If <code>propertyName</code> is <code>null</code>.
     * @throws PotatoException
     *             If specified propertyName isn't existed.
     * @throws PotatoTargetException
     */
    void setPropertyValue(Object instance, String propertyName, Object propertyValue)
            throws PotatoException, NoSuchPotatoPropertyException, PotatoTargetException;

    /**
     * @throws NullPointerException
     *             If <code>methodName</code> is <code>null</code>.
     * @throws PotatoException
     *             If can't determine which method to be invoked, or the specified method isn't
     *             existed.
     * @throws PotatoTargetException
     */
    Object invoke(Object instance, String methodName, Object... parameters)
            throws PotatoException, NoSuchPotatoMethodException, PotatoTargetException;

}
