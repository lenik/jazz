package net.bodz.bas.potato.provider.bean;

import java.beans.EventSetDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.potato.element.AbstractEvent;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class BeanEvent
        extends AbstractEvent {

    private final EventSetDescriptor eventSetDescriptor;

    public BeanEvent(Class<?> declaringType, EventSetDescriptor eventSetDescriptor, IElementDoc xjdoc) {
        super(declaringType, eventSetDescriptor.getName(), //
                eventSetDescriptor.getListenerType(), null);
        this.eventSetDescriptor = eventSetDescriptor;

        setXjdoc(xjdoc);
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

    @Override
    public Annotation[] getAnnotations() {
        Class<?> listenerType = eventSetDescriptor.getListenerType();
        return listenerType.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        Class<?> listenerType = eventSetDescriptor.getListenerType();
        return listenerType.getDeclaredAnnotations();
    }

}
