package net.bodz.bas.potato.provider.bean;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.element.*;

public class BeanPotatoElementProvider
        extends AbstractPotatoElementProvider {

    public static final int PRIORITY = 100;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    BeanInfo getBeanInfo(Class<?> objType) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(objType);
            return beanInfo;
        } catch (IntrospectionException e) {
            return null;
        }
    }

    @Override
    public IProperty getProperty(Class<?> objType, Object obj, String propertyName) {
        BeanInfo beanInfo = getBeanInfo(objType);
        if (beanInfo == null)
            return null;

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors())
            if (propertyDescriptor.getName().equals(propertyName))
                return new BeanProperty(objType, propertyDescriptor);

        return null;
    }

    @Override
    public IMethod getMethod(Class<?> objType, Object obj, MethodSignature signature) {
        BeanInfo beanInfo = getBeanInfo(objType);
        if (beanInfo == null)
            return null;

        for (MethodDescriptor methodDescriptor : beanInfo.getMethodDescriptors()) {
            MethodSignature sig = new MethodSignature(methodDescriptor.getMethod());
            if (sig.equals(signature))
                return new BeanMethod(methodDescriptor);
        }

        return null;
    }

    @Override
    public IEvent getEvent(Class<?> objType, Object obj, String eventName) {
        BeanInfo beanInfo = getBeanInfo(objType);
        if (beanInfo == null)
            return null;

        for (EventSetDescriptor eventSetDescriptor : beanInfo.getEventSetDescriptors())
            if (eventSetDescriptor.getName().equals(eventName))
                return new BeanEvent(objType, eventSetDescriptor);

        return null;
    }

    @Override
    public void fillProperties(Class<?> objType, Object obj, MutablePropertyMap propertyMap) {
        BeanInfo beanInfo = getBeanInfo(objType);
        if (beanInfo == null)
            return;

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors())
            if (!propertyMap.containsProperty(propertyDescriptor.getName())) {
                BeanProperty beanProperty = new BeanProperty(objType, propertyDescriptor);
                propertyMap.addProperty(beanProperty);
            }
    }

    @Override
    public void fillMethods(Class<?> objType, Object obj, MutableMethodMap methodMap) {
        BeanInfo beanInfo = getBeanInfo(objType);
        if (beanInfo == null)
            return;

        for (MethodDescriptor methodDescriptor : beanInfo.getMethodDescriptors()) {
            MethodSignature signature = new MethodSignature(methodDescriptor.getMethod());
            if (!methodMap.containsMethod(signature)) {
                BeanMethod beanMethod = new BeanMethod(methodDescriptor);
                methodMap.addMethod(beanMethod);
            }
        }
    }

    @Override
    public void fillEventMap(Class<?> objType, Object obj, MutableEventMap eventMap) {
        BeanInfo beanInfo = getBeanInfo(objType);
        if (beanInfo == null)
            return;

        for (EventSetDescriptor eventSetDescriptor : beanInfo.getEventSetDescriptors())
            if (!eventMap.containsEvent(eventSetDescriptor.getName())) {
                BeanEvent beanEvent = new BeanEvent(objType, eventSetDescriptor);
                eventMap.addEvent(beanEvent);
            }
    }

}
