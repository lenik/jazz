package net.bodz.mda.parsers;

import java.lang.reflect.Field;

import net.bodz.bas.lang.Predicate;
import net.bodz.bas.lang.ref.Ref;
import net.bodz.bas.lang.ref.SimpleRef;

public class ReflectUtil {

    public static void findField(Class<?> clazz, Object object,
            Object fieldValue, Predicate<Field> pred) {
        for (Field field : clazz.getFields()) {
            try {
                Object value = field.get(object);
                if (value != fieldValue) {
                    if (fieldValue == null || !fieldValue.equals(value))
                        continue;
                }
                if (!pred.eval(field))
                    break;
            } catch (IllegalAccessException e) {
                // continue find?
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    public static void findField(Object object, Object fieldValue,
            Predicate<Field> pred) {
        findField(object.getClass(), object, fieldValue, pred);
    }

    public static Field getFirstField(Class<?> clazz, Object object,
            Object fieldValue) {
        final Ref<Field> first = new SimpleRef<Field>();
        findField(clazz, object, fieldValue, new Predicate<Field>() {
            @Override
            public boolean eval(Field field) {
                first.set(field);
                return false;
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
