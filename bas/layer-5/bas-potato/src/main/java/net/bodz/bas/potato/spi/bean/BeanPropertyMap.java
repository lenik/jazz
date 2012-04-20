package net.bodz.bas.potato.spi.bean;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;

import net.bodz.bas.potato.traits.AbstractPropertyMap;

public class BeanPropertyMap
        extends AbstractPropertyMap {

    private static final long serialVersionUID = 1L;

    public BeanPropertyMap(BeanInfo beanInfo) {
        Class<?> beanClass = beanInfo.getBeanDescriptor().getBeanClass();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String name = propertyDescriptor.getName();
            BeanProperty beanProperty = new BeanProperty(beanClass, propertyDescriptor);
            put(name, beanProperty);
        }
    }

}
