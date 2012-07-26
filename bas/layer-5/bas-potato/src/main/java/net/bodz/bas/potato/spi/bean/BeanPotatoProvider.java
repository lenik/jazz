package net.bodz.bas.potato.spi.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.potato.spi.AbstractPotatoProvider;
import net.bodz.bas.potato.spi.builtin.DefaultConstructorMap;
import net.bodz.bas.potato.spi.builtin.DefaultEventMap;
import net.bodz.bas.potato.spi.builtin.DefaultMethodMap;
import net.bodz.bas.potato.spi.builtin.DefaultPropertyMap;
import net.bodz.bas.potato.traits.*;

public class BeanPotatoProvider
        extends AbstractPotatoProvider {

    BeanInfo getBeanInfo(Class<?> objType) {
        try {
            return Introspector.getBeanInfo(objType);
        } catch (IntrospectionException e) {
            throw new QueryException(e.getMessage(), e);
        }
    }

    @Override
    public IType getType(Class<?> objType)
            throws QueryException {
        BeanInfo beanInfo = getBeanInfo(objType);
        return new BeanType(beanInfo);
    }

    @Override
    public IPropertyMap getPropertyMap(Class<?> objType)
            throws QueryException {
        BeanInfo beanInfo = getBeanInfo(objType);
        DefaultPropertyMap propertyMap = new DefaultPropertyMap();
        propertyMap.addBeanProperties(beanInfo);
        return propertyMap;
    }

    @Override
    public IMethodMap getMethodMap(Class<?> objType) {
        BeanInfo beanInfo = getBeanInfo(objType);
        DefaultMethodMap methodMap = new DefaultMethodMap();
        methodMap.addBeanMethods(beanInfo);
        return methodMap;
    }

    @Override
    public IConstructorMap getConstructorMap(Class<?> objType) {
        DefaultConstructorMap constructorMap = new DefaultConstructorMap();
        constructorMap.addClassConstructors(objType);
        return constructorMap;
    }

    @Override
    public IEventMap getEventMap(Class<?> objType) {
        BeanInfo beanInfo = getBeanInfo(objType);
        DefaultEventMap eventMap = new DefaultEventMap();
        eventMap.addBeanEvents(beanInfo);
        return eventMap;
    }

}
