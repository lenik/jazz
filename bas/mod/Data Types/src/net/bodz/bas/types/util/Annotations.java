package net.bodz.bas.types.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.lang.script.MethodSignature;

public class Annotations {

    /**
     * @return Object returned by value() of annotation instance
     */
    @SuppressWarnings("unchecked")
    public static <T, A extends Annotation> T getAnnotation(Class<?> clazz,
            Class<A> annotationClass, boolean inherits) {
        A annotation = null;
        do {
            annotation = clazz.getAnnotation(annotationClass);
            if (annotation != null)
                break;
        } while (inherits && (clazz = clazz.getSuperclass()) != null);

        if (annotation == null)
            return null;
        try {
            Method valuef = annotationClass.getMethod("value");
            return (T) valuef.invoke(annotation);
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Exception e) {
            throw new Error(e.getMessage(), e);
        }
    }

    /**
     * @return Object returned by value() of annotation instance
     */
    public static <T, A extends Annotation> T getAnnotation(Class<?> clazz,
            Class<A> annotationClass) {
        return getAnnotation(clazz, annotationClass, false);
    }

    public static <A extends Annotation> A getFieldAnnotation(Class<?> clazz,
            String fieldName, Class<A> annotationClass) {
        assert clazz != null;
        assert fieldName != null;
        assert annotationClass != null;

        try { // fast lookup
            Field field = clazz.getField(fieldName);
            A annotation = field.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
        } catch (Exception e) {
        }

        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (!fieldName.equals(field.getName()))
                    continue;
                A annotation = field.getAnnotation(annotationClass);
                if (annotation != null)
                    return annotation;
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    public static <A extends Annotation> A getMethodAnnotation(Class<?> clazz,
            MethodSignature msig, Class<A> annotationClass) {
        assert clazz != null;
        assert msig != null;
        assert annotationClass != null;

        if (msig.getName() == null)
            return getConstructorAnnotation(clazz, msig, annotationClass);

        try { // fast lookup
            Method method = msig.getMethod(clazz);
            A annotation = method.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
        } catch (Exception e) {
        }

        for (Method method : msig.getAllMethods(clazz)) {
            A annotation = method.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
        }
        return null;
    }

    public static <A extends Annotation> A getConstructorAnnotation(
            Class<?> clazz, MethodSignature msig, Class<A> annotationClass) {
        assert clazz != null;
        assert msig != null;
        assert annotationClass != null;

        for (Constructor<?> ctor : msig.getConstructors(clazz)) {
            A annotation = ctor.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
        }
        return null;
    }

}
