package net.bodz.bas.bean.java;

import java.beans.Introspector;
import java.beans.PropertyChangeSupport;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IIntrospector;
import net.bodz.bas.bean.api.IPropertyChangeSupport;
import net.bodz.bas.bean.api.IntrospectionException;

public class JbIntrospector
        implements IIntrospector {

    @Override
    public IBeanInfo getBeanInfo(Class<?> beanClass)
            throws IntrospectionException {
        try {
            return JbBeanInfo.convert(Introspector.getBeanInfo(beanClass));
        } catch (java.beans.IntrospectionException e) {
            throw JbIntrospectionException.convert(e);
        }
    }

    @Override
    public IBeanInfo getBeanInfo(Class<?> beanClass, int flags)
            throws IntrospectionException {
        try {
            return JbBeanInfo.convert(Introspector.getBeanInfo(beanClass, flags));
        } catch (java.beans.IntrospectionException e) {
            throw JbIntrospectionException.convert(e);
        }
    }

    @Override
    public IBeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass)
            throws IntrospectionException {
        try {
            return JbBeanInfo.convert(Introspector.getBeanInfo(beanClass, stopClass));
        } catch (java.beans.IntrospectionException e) {
            throw JbIntrospectionException.convert(e);
        }
    }

    @Override
    public IBeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass, int flags)
            throws IntrospectionException {
        try {
            return JbBeanInfo.convert(Introspector.getBeanInfo(beanClass, stopClass, flags));
        } catch (java.beans.IntrospectionException e) {
            throw JbIntrospectionException.convert(e);
        }
    }

    @Override
    public String[] getBeanInfoSearchPath() {
        return Introspector.getBeanInfoSearchPath();
    }

    @Override
    public void setBeanInfoSearchPath(String[] path) {
        Introspector.setBeanInfoSearchPath(path);
    }

    @Override
    public void flushCaches() {
        Introspector.flushCaches();
    }

    @Override
    public void flushFromCaches(Class<?> clz) {
        Introspector.flushFromCaches(clz);
    }

    @Override
    public IPropertyChangeSupport newPropertyChangeSupport(Object o) {
        PropertyChangeSupport pcs = new PropertyChangeSupport(o);
        return JbPropertyChangeSupport.convert(pcs);
    }

}
