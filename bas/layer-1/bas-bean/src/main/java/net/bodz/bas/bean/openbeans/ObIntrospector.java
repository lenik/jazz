package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IIntrospector;
import net.bodz.bas.bean.api.IPropertyChangeSupport;
import net.bodz.bas.bean.api.IntrospectionException;

import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyChangeSupport;

public class ObIntrospector
        implements
            IIntrospector {

    @Override
    public IBeanInfo getBeanInfo(Class<?> beanClass)
            throws IntrospectionException {
        try {
            return ObBeanInfo.convert(Introspector.getBeanInfo(beanClass));
        } catch (com.googlecode.openbeans.IntrospectionException e) {
            throw ObIntrospectionException.convert(e);
        }
    }

    @Override
    public IBeanInfo getBeanInfo(Class<?> beanClass, int flags)
            throws IntrospectionException {
        try {
            return ObBeanInfo.convert(Introspector.getBeanInfo(beanClass, flags));
        } catch (com.googlecode.openbeans.IntrospectionException e) {
            throw ObIntrospectionException.convert(e);
        }
    }

    @Override
    public IBeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass)
            throws IntrospectionException {
        try {
            return ObBeanInfo.convert(Introspector.getBeanInfo(beanClass, stopClass));
        } catch (com.googlecode.openbeans.IntrospectionException e) {
            throw ObIntrospectionException.convert(e);
        }
    }

    @Override
    public IBeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass, int flags)
            throws IntrospectionException {
        if (flags != USE_ALL_BEANINFO)
            throw new UnsupportedOperationException();
        try {
            return ObBeanInfo.convert(Introspector.getBeanInfo(beanClass, stopClass));
        } catch (com.googlecode.openbeans.IntrospectionException e) {
            throw ObIntrospectionException.convert(e);
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
        return ObPropertyChangeSupport.convert(pcs);
    }

}
