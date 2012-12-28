package net.bodz.bas.potato.provider.bean;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;

import net.bodz.bas.potato.element.*;
import net.bodz.bas.potato.provider.reflect.ReflectModifiers;

public class BeanType
        extends AbstractType {

    private BeanDescriptor beanDescriptor;
    private Class<?> beanClass;

    private MutablePropertyMap propertyMap = new MutablePropertyMap();
    private MutableMethodMap methodMap = new MutableMethodMap();
    private IConstructorMap constructorMap = NullConstructorMap.getInstance();
    private MutableEventMap eventMap = new MutableEventMap();

    private final int verboseLevel;

    public BeanType(BeanInfo beanInfo) {
        super(beanInfo.getBeanDescriptor().getName());

        beanDescriptor = beanInfo.getBeanDescriptor();
        beanClass = beanDescriptor.getBeanClass();

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            BeanProperty beanProperty = new BeanProperty(beanClass, propertyDescriptor);
            propertyMap.addProperty(beanProperty);
        }

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            BeanMethod beanMethod = new BeanMethod(methodDescriptor);
            methodMap.addMethod(beanMethod);
        }

        EventSetDescriptor[] eventSetDescriptors = beanInfo.getEventSetDescriptors();
        for (EventSetDescriptor eventSetDescriptor : eventSetDescriptors) {
            BeanEvent beanEvent = new BeanEvent(beanClass, eventSetDescriptor);
            eventMap.addEvent(beanEvent);
        }

        int _modifiers = beanClass.getModifiers();
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);
    }

    public BeanDescriptor getBeanDescriptor() {
        return beanDescriptor;
    }

    public Class<?> getBeanClass() {
        return beanClass;
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

    // -o IElement

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

    // -o AnnotatedElement

    @Override
    public Annotation[] getAnnotations() {
        return beanClass.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return beanClass.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return beanClass.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return beanClass.isAnnotationPresent(annotationClass);
    }

    @Override
    public int getModifiers() {
        return beanClass.getModifiers();
    }

}
