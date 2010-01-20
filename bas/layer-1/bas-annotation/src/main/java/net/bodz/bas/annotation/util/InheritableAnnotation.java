package net.bodz.bas.annotation.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.reflect.util.MethodKey;

public class InheritableAnnotation {

    /**
     * @return Object returned by value() of annotation instance
     */
    public static <A extends Annotation> Object getValue(Class<?> clazz, Class<A> annotationClass, boolean inherits) {
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
            return valuef.invoke(annotation);
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Exception e) {
            throw new Error(e.getMessage(), e);
        }
    }

    /**
     * @return Object returned by value() of annotation instance
     */
    public static <A extends Annotation> Object getValue(Class<?> clazz, Class<A> annotationClass) {
        return getValue(clazz, annotationClass, false);
    }

    @SuppressWarnings("unchecked")
    public static <T, A extends Annotation> T _getValue(Class<?> clazz, Class<A> annotationClass, boolean inherits) {
        return (T) getValue(clazz, annotationClass, inherits);
    }

    @SuppressWarnings("unchecked")
    public static <T, A extends Annotation> T _getValue(Class<?> clazz, Class<A> annotationClass) {
        return (T) getValue(clazz, annotationClass, false);
    }

    public static <A extends Annotation> A getFieldAnnotation(Class<?> clazz, String fieldName, Class<A> annotationClass) {
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

    public static <A extends Annotation> A getMethodAnnotation(Class<?> clazz, MethodKey mkey, Class<A> annotationClass) {
        assert clazz != null;
        assert mkey != null;
        assert annotationClass != null;

        if (mkey.isConstructor())
            return getConstructorAnnotation(clazz, mkey, annotationClass);

        try { // fast lookup
            Method method = mkey.getMethod(clazz);
            A annotation = method.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
        } catch (Exception e) {
        }

        for (Method method : mkey.matchAllMethods(clazz)) {
            A annotation = method.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
        }
        return null;
    }

    public static <A extends Annotation> A getConstructorAnnotation(Class<?> clazz, MethodKey mkey,
            Class<A> annotationClass) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (mkey == null)
            throw new NullPointerException("mkey");
        if (annotationClass == null)
            throw new NullPointerException("annotationClass");
        assert mkey.isConstructor() : "Not a constructor method key";

        for (Constructor<?> ctor : mkey.matchConstructors(clazz)) {
            A annotation = ctor.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
        }
        return null;
    }

}
