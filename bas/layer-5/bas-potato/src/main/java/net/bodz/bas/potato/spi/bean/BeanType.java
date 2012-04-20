package net.bodz.bas.potato.spi.bean;

import java.beans.BeanInfo;

import net.bodz.bas.potato.traits.AbstractType;
import net.bodz.bas.potato.traits.IConstructorMap;
import net.bodz.bas.potato.traits.IEventMap;
import net.bodz.bas.potato.traits.IMethodMap;
import net.bodz.bas.potato.traits.IPropertyMap;
import net.bodz.bas.potato.traits.util.NullConstructorMap;

public class BeanType
        extends AbstractType {

    private BeanPropertyMap propertyMap;
    private BeanMethodMap methodMap;
    private NullConstructorMap constructorMap;
    private BeanEventMap eventMap;

    public BeanType(BeanInfo beanInfo) {
        super(beanInfo.getBeanDescriptor().getName());
        propertyMap = new BeanPropertyMap(beanInfo);
        methodMap = new BeanMethodMap(beanInfo);
        constructorMap = new NullConstructorMap();
        eventMap = new BeanEventMap(beanInfo);
    }

    @Override
    public IPropertyMap getPropertyMap() {
        return propertyMap;
    }

    @Override
    public IMethodMap getMethodMap() {
        return methodMap;
    }

    @Override
    public IConstructorMap getConstructorMap() {
        return constructorMap;
    }

    @Override
    public IEventMap getEventMap() {
        return eventMap;
    }

}
