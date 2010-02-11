package net.bodz.bas.annotation.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.sio.ILineCharOut;

public class AnnotationDump {

    static final Field Class_annotations;
    static final Field Class_declaredAnnotations;

    static Field _getDeclaredField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    static {
        Class_annotations = _getDeclaredField(Class.class, "annotations");
        Class_annotations.setAccessible(true);
        Class_declaredAnnotations = _getDeclaredField(Class.class, "declaredAnnotations");
        Class_declaredAnnotations.setAccessible(true);
    }

    public static void dumpAnnotationMap(Class<?> clazz, ILineCharOut out) {
        dumpAnnotationMap(clazz, false, out);
    }

    public static void dumpAnnotationMap(Class<?> clazz, boolean declaredOnly, ILineCharOut out) {
        Map<?, ?> annotations;
        try {
            annotations = (Map<?, ?>) Class_annotations.get(clazz);
        } catch (IllegalAccessException e) {
            throw new Error(e.getMessage(), e);
        }

        for (Entry<?, ?> entry : annotations.entrySet()) {
            Class<?> annotationClass = (Class<?>) entry.getKey();
            Annotation annotationImpl = (Annotation) entry.getValue();
            out.println(annotationClass + " " + annotationImpl);
        }
    }

}
