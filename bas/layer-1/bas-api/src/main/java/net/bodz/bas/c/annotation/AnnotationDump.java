package net.bodz.bas.c.annotation;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;

public class AnnotationDump {

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
        Annotation[] annotations;
        if (declaredOnly)
            annotations = clazz.getDeclaredAnnotations();
        else
            annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            Class<?> type = annotation.annotationType();
            out.write(type.getSimpleName() + ".class => " + annotation + "\n");
        }
    }

}
