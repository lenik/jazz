package net.bodz.bas.annotation.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.sio.ILineCharOut;

public class Annotations {

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

    public static <A extends Annotation> A getMethodAnnotation(Class<?> clazz, MethodKey msig,
            Class<A> annotationClass) {
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

    public static <A extends Annotation> A getConstructorAnnotation(Class<?> clazz, MethodKey msig,
            Class<A> annotationClass) {
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

    static boolean _accessorsInited;
    static Field Class_annotations;
    static Field Class_declaredAnnotations;

    static void initAccessors() {
        if (_accessorsInited)
            return;
        try {
            Class_annotations = Class.class.getDeclaredField(//
                    "annotations"); 
            Class_declaredAnnotations = Class.class.getDeclaredField(//
                    "declaredAnnotations"); 
            Class_annotations.setAccessible(true);
            Class_declaredAnnotations.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new Error(e.getMessage(), e);
        }
        _accessorsInited = true;
    }

    @SuppressWarnings("unchecked")
    public static void dumpAnnotationMap(Class<?> clazz, ILineCharOut out, String indent) {
        initAccessors();
        try {
            Map<Class<?>, Annotation> annotations = (Map<Class<?>, Annotation>) Class_annotations.get(clazz);
            for (Entry<Class<?>, Annotation> ent : annotations.entrySet())
                out.println(indent + ent.getKey() + " " + ent.getValue()); 
        } catch (IllegalAccessException e) {
            throw new Error(e.getMessage(), e);
        }
    }

    public static void dumpAnnotationMap(Class<?> clazz, ILineCharOut out) {
        dumpAnnotationMap(clazz, out, ""); 
    }

}
