package net.bodz.bas.potato.spi.bean;

import java.beans.BeanInfo;

import net.bodz.bas.potato.spi.builtin.DefaultEventMap;
import net.bodz.bas.potato.spi.builtin.DefaultMethodMap;
import net.bodz.bas.potato.spi.builtin.DefaultPropertyMap;
import net.bodz.bas.potato.spi.builtin.NullConstructorMap;
import net.bodz.bas.potato.traits.AbstractType;
import net.bodz.bas.potato.traits.IConstructorMap;
import net.bodz.bas.potato.traits.IEventMap;
import net.bodz.bas.potato.traits.IMethodMap;
import net.bodz.bas.potato.traits.IPropertyMap;

public class BeanType
        extends AbstractType {

    private IPropertyMap propertyMap;
    private IMethodMap methodMap;
    private IConstructorMap constructorMap;
    private IEventMap eventMap;

    public BeanType(BeanInfo beanInfo) {
        super(beanInfo.getBeanDescriptor().getName());
        propertyMap = new DefaultPropertyMap().addBeanProperties(beanInfo);
        methodMap = new DefaultMethodMap().addBeanMethods(beanInfo);
        constructorMap = NullConstructorMap.getInstance();
        eventMap = new DefaultEventMap().addBeanEvents(beanInfo);
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
