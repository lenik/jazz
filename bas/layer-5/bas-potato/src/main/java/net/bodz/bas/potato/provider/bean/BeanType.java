package net.bodz.bas.potato.provider.bean;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.*;
import net.bodz.bas.potato.provider.reflect.ReflectModifiers;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class BeanType
        extends AbstractType {

    private BeanDescriptor beanDescriptor;
    private Class<?> beanClass;

    private MutablePropertyMap propertyMap = new MutablePropertyMap();
    private MutableMethodMap methodMap = new MutableMethodMap();
    private IConstructorMap constructorMap = NullConstructorMap.getInstance();
    private MutableEventMap eventMap = new MutableEventMap();

    private final int verboseLevel;

    public BeanType(BeanInfo beanInfo, int infoset, ClassDoc classDoc) {
        super(beanInfo.getBeanDescriptor().getName());

        beanDescriptor = beanInfo.getBeanDescriptor();
        beanClass = beanDescriptor.getBeanClass();

        if ((infoset & ITypeProvider.PROPERTIES) != 0) {
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

                MethodDoc xetterDoc = null;
                if (classDoc != null) {
                    Method xetter = propertyDescriptor.getReadMethod();
                    if (xetter == null)
                        xetter = propertyDescriptor.getWriteMethod();
                    if (xetter != null) {
                        MethodId xetterId = new MethodId(xetter);
                        xetterDoc = classDoc.getMethodDoc(xetterId);
                    }
                }

                BeanProperty beanProperty = new BeanProperty(beanClass, propertyDescriptor, xetterDoc);
                propertyMap.addProperty(beanProperty);
            }
        }

        if ((infoset & ITypeProvider.METHODS) != 0) {
            MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
            for (MethodDescriptor methodDescriptor : methodDescriptors) {

                MethodDoc methodDoc = null;
                if (classDoc != null) {
                    Method method = methodDescriptor.getMethod();
                    MethodId methodId = new MethodId(method);
                    methodDoc = classDoc.getMethodDoc(methodId);
                }

                BeanMethod beanMethod = new BeanMethod(methodDescriptor, methodDoc);
                methodMap.addMethod(beanMethod);
            }
        }

        if ((infoset & ITypeProvider.EVENTS) != 0) {
            EventSetDescriptor[] eventSetDescriptors = beanInfo.getEventSetDescriptors();
            for (EventSetDescriptor eventSetDescriptor : eventSetDescriptors) {

                IJavaElementDoc eventDoc = null;
                if (classDoc != null) {
                    // TODO Event xjdoc..
                }

                BeanEvent beanEvent = new BeanEvent(beanClass, eventSetDescriptor, eventDoc);
                eventMap.addEvent(beanEvent);
            }
        }

        int _modifiers = beanClass.getModifiers();
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);

        setXjdoc(classDoc);
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

    // -o IElement

    @Override
    public int getModifiers() {
        return beanClass.getModifiers();
    }

}
