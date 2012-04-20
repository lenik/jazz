package net.bodz.bas.potato.spi.bean;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;

import net.bodz.bas.potato.traits.AbstractEventMap;

public class BeanEventMap
        extends AbstractEventMap {

    private static final long serialVersionUID = 1L;

    public BeanEventMap(BeanInfo beanInfo) {
        Class<?> beanClass = beanInfo.getBeanDescriptor().getBeanClass();

        EventSetDescriptor[] eventSetDescriptors = beanInfo.getEventSetDescriptors();
        for (EventSetDescriptor eventSetDescriptor : eventSetDescriptors) {
            String name = eventSetDescriptor.getName();
            BeanEvent beanEvent = new BeanEvent(beanClass, eventSetDescriptor);
            put(name, beanEvent);
        }
    }

}
