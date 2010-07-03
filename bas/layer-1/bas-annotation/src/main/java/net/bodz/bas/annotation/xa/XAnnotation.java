package net.bodz.bas.annotation.xa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class XAnnotation {

    public static <A extends Annotation> A getAnnotation(Class<?> type, Class<A> annotationType) {
        XClass xClass = XClass.getXClass(type);
        return xClass.getAnnotation(annotationType);
    }

    public static <A extends Annotation> A getAnnotation(Field field, Class<A> annotationType) {
        XClass xClass = XClass.getXClass(field.getDeclaringClass());
        return xClass.getFieldAnnotation(field, annotationType);
    }

    public static <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
        XClass xClass = XClass.getXClass(method.getDeclaringClass());
        return xClass.getMethodAnnotation(method, annotationType);
    }

}
