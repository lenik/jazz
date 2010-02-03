package net.bodz.bas.potato.adapter.bean;

import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class PotatoBeanInfo
        extends SimpleBeanInfo {

    PotatoBeanDescriptor beanDescriptor;
    PotatoPropertyDescriptor[] propertyDescriptors;
    PotatoMethodDescriptor[] methodDescriptors;
    PotatoEventSetDescriptor[] eventSetDescriptors;

    @Override
    public BeanDescriptor getBeanDescriptor() {
        return beanDescriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return propertyDescriptors;
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        return methodDescriptors;
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        return eventSetDescriptors;
    }

}
