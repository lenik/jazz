package net.bodz.bas.c.annotation;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

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

    public static String dumpAnnotationMap(Class<?> clazz) {
        return dumpAnnotationMap(clazz, false);
    }

    public static void dumpAnnotationMap(Class<?> clazz, PrintStream out) {
        dumpAnnotationMap(clazz, false, out);
    }

    public static void dumpAnnotationMap(Class<?> clazz, Writer out)
            throws IOException {
        dumpAnnotationMap(clazz, false, out);
    }

    public static String dumpAnnotationMap(Class<?> clazz, boolean declaredOnly) {
        StringWriter buffer = new StringWriter();
        try {
            dumpAnnotationMap(clazz, declaredOnly, buffer);
        } catch (IOException e) {
        }
        return buffer.toString();
    }

    public static void dumpAnnotationMap(Class<?> clazz, boolean declaredOnly, PrintStream out) {
        Writer w = new OutputStreamWriter(out);
        try {
            dumpAnnotationMap(clazz, declaredOnly, w);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected", e);
        }
    }

    public static void dumpAnnotationMap(Class<?> clazz, boolean declaredOnly, Writer out)
            throws IOException {
        Map<?, ?> annotations;
        try {
            if (declaredOnly)
                annotations = (Map<?, ?>) Class_declaredAnnotations.get(clazz);
            else
                annotations = (Map<?, ?>) Class_annotations.get(clazz);
        } catch (IllegalAccessException e) {
            throw new Error(e.getMessage(), e);
        }

        for (Entry<?, ?> entry : annotations.entrySet()) {
            Class<?> annotationClass = (Class<?>) entry.getKey();
            Annotation annotationImpl = (Annotation) entry.getValue();
            out.write(annotationClass.getSimpleName() + ".class => " + annotationImpl + "\n");
        }
    }

}
