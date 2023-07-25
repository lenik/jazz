package net.bodz.bas.bean.api;

public interface IIntrospector {

    // Flags that can be used to control getBeanInfo:
    /**
     * Flag to indicate to use of all beaninfo.
     */
    int USE_ALL_BEANINFO = 1;
    /**
     * Flag to indicate to ignore immediate beaninfo.
     */
    int IGNORE_IMMEDIATE_BEANINFO = 2;
    /**
     * Flag to indicate to ignore all beaninfo.
     */
    int IGNORE_ALL_BEANINFO = 3;

    /**
     * Introspect on a Java Bean and learn about all its properties, exposed methods, and events.
     * <p>
     * If the BeanInfo class for a Java Bean has been previously Introspected then the BeanInfo
     * class is retrieved from the BeanInfo cache.
     *
     * @param beanClass
     *            The bean class to be analyzed.
     * @return A BeanInfo object describing the target bean.
     * @exception IntrospectionException
     *                if an exception occurs during introspection.
     * @see #flushCaches
     * @see #flushFromCaches
     */
    IBeanInfo getBeanInfo(Class<?> beanClass)
            throws IntrospectionException;

    /**
     * Introspect on a Java bean and learn about all its properties, exposed methods, and events,
     * subject to some control flags.
     * <p>
     * If the BeanInfo class for a Java Bean has been previously Introspected based on the same
     * arguments then the BeanInfo class is retrieved from the BeanInfo cache.
     *
     * @param beanClass
     *            The bean class to be analyzed.
     * @param flags
     *            Flags to control the introspection. If flags == USE_ALL_BEANINFO then we use all
     *            of the BeanInfo classes we can discover. If flags == IGNORE_IMMEDIATE_BEANINFO
     *            then we ignore any BeanInfo associated with the specified beanClass. If flags ==
     *            IGNORE_ALL_BEANINFO then we ignore all BeanInfo associated with the specified
     *            beanClass or any of its parent classes.
     * @return A BeanInfo object describing the target bean.
     * @exception IntrospectionException
     *                if an exception occurs during introspection.
     */
    IBeanInfo getBeanInfo(Class<?> beanClass, int flags)
            throws IntrospectionException;

    /**
     * Introspect on a Java bean and learn all about its properties, exposed methods, below a given
     * "stop" point.
     * <p>
     * If the BeanInfo class for a Java Bean has been previously Introspected based on the same
     * arguments, then the BeanInfo class is retrieved from the BeanInfo cache.
     *
     * @return the BeanInfo for the bean
     * @param beanClass
     *            The bean class to be analyzed.
     * @param stopClass
     *            The baseclass at which to stop the analysis. Any methods/properties/events in the
     *            stopClass or in its baseclasses will be ignored in the analysis.
     * @exception IntrospectionException
     *                if an exception occurs during introspection.
     */
    IBeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass)
            throws IntrospectionException;

    /**
     * Introspect on a Java Bean and learn about all its properties, exposed methods and events,
     * below a given {@code stopClass} point subject to some control {@code flags}.
     * <dl>
     * <dt>USE_ALL_BEANINFO</dt>
     * <dd>Any BeanInfo that can be discovered will be used.</dd>
     * <dt>IGNORE_IMMEDIATE_BEANINFO</dt>
     * <dd>Any BeanInfo associated with the specified {@code beanClass} will be ignored.</dd>
     * <dt>IGNORE_ALL_BEANINFO</dt>
     * <dd>Any BeanInfo associated with the specified {@code beanClass} or any of its parent classes
     * will be ignored.</dd>
     * </dl>
     * Any methods/properties/events in the {@code stopClass} or in its parent classes will be
     * ignored in the analysis.
     * <p>
     * If the BeanInfo class for a Java Bean has been previously introspected based on the same
     * arguments then the BeanInfo class is retrieved from the BeanInfo cache.
     *
     * @param beanClass
     *            the bean class to be analyzed
     * @param stopClass
     *            the parent class at which to stop the analysis
     * @param flags
     *            flags to control the introspection
     * @return a BeanInfo object describing the target bean
     * @exception IntrospectionException
     *                if an exception occurs during introspection
     *
     * @since 1.7
     */
    IBeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass, int flags)
            throws IntrospectionException;

    /**
     * Gets the list of package names that will be used for finding BeanInfo classes.
     *
     * @return The array of package names that will be searched in order to find BeanInfo classes.
     *         The default value for this array is implementation-dependent; e.g. Sun implementation
     *         initially sets to {"sun.beans.infos"}.
     */

    String[] getBeanInfoSearchPath();

    /**
     * Change the list of package names that will be used for finding BeanInfo classes. The
     * behaviour of this method is undefined if parameter path is null.
     *
     * <p>
     * First, if there is a security manager, its <code>checkPropertiesAccess</code> method is
     * called. This could result in a SecurityException.
     *
     * @param path
     *            Array of package names.
     * @exception SecurityException
     *                if a security manager exists and its <code>checkPropertiesAccess</code> method
     *                doesn't allow setting of system properties.
     * @see SecurityManager#checkPropertiesAccess
     */

    void setBeanInfoSearchPath(String[] path);

    /**
     * Flush all of the Introspector's internal caches. This method is not normally required. It is
     * normally only needed by advanced tools that update existing "Class" objects in-place and need
     * to make the Introspector re-analyze existing Class objects.
     */

    void flushCaches();

    /**
     * Flush the Introspector's internal cached information for a given class. This method is not
     * normally required. It is normally only needed by advanced tools that update existing "Class"
     * objects in-place and need to make the Introspector re-analyze an existing Class object.
     *
     * Note that only the direct state associated with the target Class object is flushed. We do not
     * flush state for other Class objects with the same name, nor do we flush state for any related
     * Class objects (such as subclasses), even though their state may include information
     * indirectly obtained from the target Class object.
     *
     * @param clz
     *            Class object to be flushed.
     * @throws NullPointerException
     *             If the Class object is null.
     */
    void flushFromCaches(Class<?> clz);

    IPropertyChangeSupport newPropertyChangeSupport(Object o);

}