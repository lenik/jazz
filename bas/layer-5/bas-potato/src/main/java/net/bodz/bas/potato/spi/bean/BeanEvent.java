package net.bodz.bas.potato.spi.bean;

import java.beans.EventSetDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.potato.traits.AbstractEvent;

public class BeanEvent
        extends AbstractEvent {

    private final EventSetDescriptor eventSetDescriptor;

    public BeanEvent(Class<?> declaringType, EventSetDescriptor eventSetDescriptor) {
        super(declaringType, eventSetDescriptor.getName(), eventSetDescriptor.getListenerType(), null);
        this.eventSetDescriptor = eventSetDescriptor;
    }

    @Override
    protected Method getGetListenersMethod()
            throws ReflectiveOperationException {
        return eventSetDescriptor.getGetListenerMethod();
    }

    @Override
    protected Method getAddListenerMethod()
            throws ReflectiveOperationException {
        return eventSetDescriptor.getAddListenerMethod();
    }

    @Override
    protected Method getRemoveListenerMethod()
            throws ReflectiveOperationException {
        return eventSetDescriptor.getRemoveListenerMethod();
    }

}
