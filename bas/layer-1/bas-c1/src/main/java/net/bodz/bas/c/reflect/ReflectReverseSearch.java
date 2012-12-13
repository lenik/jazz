package net.bodz.bas.c.reflect;

import java.lang.reflect.Field;

import net.bodz.bas.fn.legacy.Pred1;

public class ReflectReverseSearch {

    public static void findField(Class<?> clazz, Object object, Object fieldValue, Pred1<Field> pred) {
        for (Field field : clazz.getFields()) {
            try {
                Object value = field.get(object);
                if (value != fieldValue) {
                    if (fieldValue == null || !fieldValue.equals(value))
                        continue;
                }
                if (!pred.test(field))
                    break;
            } catch (IllegalAccessException e) {
                // continue find?
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    public static void findField(Object object, Object fieldValue, Pred1<Field> pred) {
        findField(object.getClass(), object, fieldValue, pred);
    }

    public static Field getFirstField(Class<?> clazz, Object object, Object fieldValue) {
        final Field[] first = new Field[1];
        findField(clazz, object, fieldValue, new Pred1<Field>() {
            @Override
            public boolean test(Field field) {
                first[0] = field;
                return false;
            }
        });
        return first[0];
    }

    public static Field getFirstField(Object object, Object fieldValue) {
        return getFirstField(object.getClass(), object, fieldValue);
    }

    public static String getFirstFieldName(Class<?> clazz, Object object, Object fieldValue) {
        Field field = getFirstField(clazz, object, fieldValue);
        if (field == null)
            return null;
        return field.getName();
    }

    public static String getFirstFieldName(Object object, Object fieldValue) {
        return getFirstFieldName(object.getClass(), object, fieldValue);
    }

}
