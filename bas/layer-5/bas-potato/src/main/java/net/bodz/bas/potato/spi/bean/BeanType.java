package net.bodz.bas.potato.spi.bean;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.lang.annotation.Annotation;

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

    private BeanDescriptor beanDescriptor;
    private Class<?> beanClass;

    private IPropertyMap propertyMap;
    private IMethodMap methodMap;
    private IConstructorMap constructorMap;
    private IEventMap eventMap;

    public BeanType(BeanInfo beanInfo) {
        super(beanInfo.getBeanDescriptor().getName());

        beanDescriptor = beanInfo.getBeanDescriptor();
        beanClass = beanDescriptor.getBeanClass();

        propertyMap = new DefaultPropertyMap().addBeanProperties(beanInfo);
        methodMap = new DefaultMethodMap().addBeanMethods(beanInfo);
        constructorMap = NullConstructorMap.getInstance();
        eventMap = new DefaultEventMap().addBeanEvents(beanInfo);
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
