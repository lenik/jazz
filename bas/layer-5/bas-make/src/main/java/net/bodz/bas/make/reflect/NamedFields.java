package net.bodz.bas.make.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.make.util.INamed;

public class NamedFields {

    public static void injectNames(Class<?> clazz) {
        InjectNameOptions options = new InjectNameOptions();
        injectNames(clazz, null, options);
    }

    public static void injectNames(Object instance) {
        InjectNameOptions options = new InjectNameOptions();
        injectNames(instance, options);
    }

    public static void injectNames(Class<?> clazz, Object instance, InjectNameOptions options) {
        injectNames(clazz.getDeclaredFields(), instance, options);
    }

    public static void injectNames(Object instance, InjectNameOptions options) {
        injectNames(instance.getClass().getFields(), instance, options);
    }

    static void injectNames(Field[] fields, Object instance, InjectNameOptions options) {
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                if (instance != null)
                    continue;
            } else { // non-static
                if (instance == null)
                    continue;
            }

            boolean nonPublic = !Modifier.isPublic(modifiers);
            if (nonPublic && !options.isIncludeNonPublicFields())
                continue;

            if (!INamed.class.isAssignableFrom(field.getType()))
                continue;

            if (nonPublic)
                field.setAccessible(true);

            INamed element;
            try {
                element = (INamed) field.get(instance);
            } catch (IllegalAccessException e) {
                throw new UnexpectedException(e);
            }

            String name = field.getName();
            if (options.isQualified())
                if (options.isFullQualified())
                    name = field.getDeclaringClass().getName() + "::" + name;
                else
                    name = field.getDeclaringClass().getSimpleName() + "::" + name;
            element.setName(name);
        }
    }

}
