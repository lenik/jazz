package net.bodz.bas.potato.provider.bean;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.i18n.dom1.IElement;
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

    private MutablePropertyMap propertyMap = new MutablePropertyMap(false);
    private MutableMethodMap methodMap = new MutableMethodMap(false);
    private IConstructorMap constructorMap = NullConstructorMap.getInstance();
    private MutableEventMap eventMap = new MutableEventMap(false);

    private final int verboseLevel;

    public BeanType(BeanInfo beanInfo, int infoset, ClassDoc classDoc) {
        super(beanInfo.getBeanDescriptor().getName());

        beanDescriptor = beanInfo.getBeanDescriptor();
        beanClass = beanDescriptor.getBeanClass();

        if ((infoset & ITypeProvider.PROPERTIES) != 0) {
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

                Method getter = propertyDescriptor.getReadMethod();
                Method setter = propertyDescriptor.getWriteMethod();
                if (getter == null && setter == null)
                    // TODO This could be an indexed property. Not supported, yet.
                    continue;

                MethodDoc propertyDoc = null;
                if (classDoc != null) {
                    if (getter != null) {
                        MethodId getterId = new MethodId(getter);
                        propertyDoc = classDoc.getMethodDoc(getterId);
                    } else if (setter != null) {
                        MethodId setterId = new MethodId(setter);
                        propertyDoc = classDoc.getMethodDoc(setterId);
                    }
                }

                BeanProperty beanProperty = new BeanProperty(beanClass, propertyDescriptor, propertyDoc);
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

    /** ⇱ Implementation Of {@link IType}. */
    /* _____________________________ */static section.iface __TYPE__;

    @Override
    public Class<?> getType() {
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

    /** ⇱ Implementaton Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __ANNOTATION__;

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return beanClass.isAnnotationPresent(annotationClass);
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return beanClass.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return beanClass.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return beanClass.getDeclaredAnnotations();
    }

    /** ⇱ Implementaton Of {@link IElement}. */
    /* _____________________________ */static section.iface __ELEMENT__;

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

    @Override
    public int getModifiers() {
        return beanClass.getModifiers();
    }

}
