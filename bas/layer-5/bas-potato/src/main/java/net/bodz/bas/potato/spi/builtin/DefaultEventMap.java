package net.bodz.bas.potato.spi.builtin;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.potato.model.IEvent;
import net.bodz.bas.potato.model.IEventMap;
import net.bodz.bas.potato.spi.bean.BeanEvent;
import net.bodz.bas.util.order.ComparableComparator;

public class DefaultEventMap
        implements IEventMap {

    Map<String, IEvent> map;

    public DefaultEventMap() {
        map = new TreeMap<String, IEvent>(ComparableComparator.getInstance());
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<IEvent> getEvents() {
        return map.values();
    }

    @Override
    public IEvent getEvent(String eventName) {
        return map.get(eventName);
    }

    public DefaultEventMap addBeanEvents(BeanInfo beanInfo) {
        Class<?> beanClass = beanInfo.getBeanDescriptor().getBeanClass();

        EventSetDescriptor[] eventSetDescriptors = beanInfo.getEventSetDescriptors();
        for (EventSetDescriptor eventSetDescriptor : eventSetDescriptors) {
            String name = eventSetDescriptor.getName();
            BeanEvent beanEvent = new BeanEvent(beanClass, eventSetDescriptor);
            map.put(name, beanEvent);
        }
        return this;
    }

}
