package net.bodz.bas.bean.api;

import net.bodz.bas.bean.java.JbIntrospector;

public class Introspectors {

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

    public static IIntrospector provider = new JbIntrospector();

    public static IBeanInfo getBeanInfo(Class<?> beanClass)
            throws IntrospectionException {
        return provider.getBeanInfo(beanClass);
    }

    public static IBeanInfo getBeanInfo(Class<?> beanClass, int flags)
            throws IntrospectionException {
        return provider.getBeanInfo(beanClass, flags);
    }

    public static IBeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass)
            throws IntrospectionException {
        return provider.getBeanInfo(beanClass, stopClass);
    }

    public static IBeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass, int flags)
            throws IntrospectionException {
        return provider.getBeanInfo(beanClass, stopClass, flags);
    }

    public static String[] getBeanInfoSearchPath() {
        return provider.getBeanInfoSearchPath();
    }

    public static void setBeanInfoSearchPath(String[] path) {
        provider.setBeanInfoSearchPath(path);
    }

    public static void flushCaches() {
        provider.flushCaches();
    }

    public static void flushFromCaches(Class<?> clz) {
        provider.flushFromCaches(clz);
    }

    public static IPropertyChangeSupport newPropertyChangeSupport(Object o) {
        return provider.newPropertyChangeSupport(o);
    }

}
