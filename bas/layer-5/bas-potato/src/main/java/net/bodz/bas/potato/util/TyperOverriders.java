package net.bodz.bas.potato.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.type.TypeIndex;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.meta.lang.TyperOverrider;

public class TyperOverriders {

    public static Class<? extends Annotation> getOverriderAnnotationClass(Class<?> typerClass) {
        return map.get(typerClass);
    }

    static Map<Class<?>, Class<? extends Annotation>> map = new HashMap<Class<?>, Class<? extends Annotation>>();

    static {
        try {
            load();
        } catch (Exception e) {
            throw new IllegalConfigException(e.getMessage(), e);
        }
    }

    static void load()
            throws ClassNotFoundException, IOException {
        // TODO ClassLoaders.getRuntimeClassLoader?
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        TypeIndex typeIndex = TypeIndex.getInstance(classLoader);

        for (Class<? extends Annotation> annotationClass : typeIndex.list(TyperOverrider.class, true)) {
            TyperOverrider aOverrider = annotationClass.getAnnotation(TyperOverrider.class);
            Class<?> typerClass = aOverrider.value();
            map.put(typerClass, annotationClass);
        }
    }

}
