package net.bodz.bas.bean.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public interface IPropertyDescriptor
        extends
            IFeatureDescriptor,
            IDeclaredMember,
            AnnotatedElement {

    Class<?> getPropertyType();

    // lenik add
    Type getPropertyGenericType();

    Method getReadMethod();

    Method getWriteMethod();

    void setReadMethod(Method getter)
            throws IntrospectionException;

    void setWriteMethod(Method setter)
            throws IntrospectionException;

    boolean isConstrained();

    void setConstrained(boolean constrained);

    boolean isBound();

    void setBound(boolean bound);

    Class<?> getPropertyEditorClass();

    void setPropertyEditorClass(Class<?> propertyEditorClass);

    IPropertyEditor createPropertyEditor(Object bean);

    @Override
    default <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        Method getter = getReadMethod();
        if (getter != null) {
            T a = getter.getAnnotation(annotationClass);
            if (a != null)
                return a;
        }
        Method setter = getWriteMethod();
        if (setter != null) {
            T a = setter.getAnnotation(annotationClass);
            if (a != null)
                return a;
        }
        return null;
    }

    @Override
    default Annotation[] getAnnotations() {
        Method getter = getReadMethod();
        if (getter != null) {
            Annotation[] av = getter.getAnnotations();
            return av;
        }
        Method setter = getWriteMethod();
        if (setter != null) {
            Annotation[] av = setter.getAnnotations();
            return av;
        }
        return new Annotation[0];
    }

    @Override
    default Annotation[] getDeclaredAnnotations() {
        Method getter = getReadMethod();
        if (getter != null) {
            Annotation[] av = getter.getDeclaredAnnotations();
            return av;
        }
        Method setter = getWriteMethod();
        if (setter != null) {
            Annotation[] av = setter.getDeclaredAnnotations();
            return av;
        }
        return new Annotation[0];
    }

}