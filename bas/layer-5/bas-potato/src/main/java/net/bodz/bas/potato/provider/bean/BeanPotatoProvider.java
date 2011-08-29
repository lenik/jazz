package net.bodz.bas.potato.provider.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.potato.AbstractPotatoProvider;
import net.bodz.bas.potato.traits.IConstructorMap;
import net.bodz.bas.potato.traits.IEventMap;
import net.bodz.bas.potato.traits.IMethodMap;
import net.bodz.bas.potato.traits.IPropertyMap;
import net.bodz.bas.potato.traits.IType;

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
        return new BeanPropertyMap(beanInfo);
    }

    @Override
    public IMethodMap getMethodMap(Class<?> objType) {
        BeanInfo beanInfo = getBeanInfo(objType);
        return new BeanMethodMap(beanInfo);
    }

    @Override
    public IConstructorMap getConstructorMap(Class<?> objType) {
        return null;
    }

    @Override
    public IEventMap getEventMap(Class<?> objType) {
        BeanInfo beanInfo = getBeanInfo(objType);
        return new BeanEventMap(beanInfo);
    }

}
