package net.bodz.mda.parsers;

import java.lang.reflect.Field;

import net.bodz.bas.lang.Closure;
import net.bodz.bas.lang.ref.Ref;
import net.bodz.bas.lang.ref.SimpleRef;

public class Util {

    public static void findField(Class<?> clazz, Object object,
            Object fieldValue, Closure<Field> c) {
    }

    public static void findField(Object object, Object fieldValue,
            Closure<Field> c) {
        findField(object.getClass(), object, fieldValue, c);
    }

    public static Field getFirstField(Class<?> clazz, Object object,
            Object fieldValue) {
        final Ref<Field> first = new SimpleRef<Field>();
        findField(clazz, object, fieldValue, new Closure<Field>() {
            @Override
            public void execute(Field field) {
                first.set(field);
            }
        });
        return first.get();
    }

    public static Field getFirstField(Object object, Object fieldValue) {
        return getFirstField(object.getClass(), object, fieldValue);
    }

    public static String getFirstFieldName(Class<?> clazz, Object object,
            Object fieldValue) {
        Field field = getFirstField(clazz, object, fieldValue);
        if (field == null)
            return null;
        return field.getName();
    }

    public static String getFirstFieldName(Object object, Object fieldValue) {
        return getFirstFieldName(object.getClass(), object, fieldValue);
    }

}
