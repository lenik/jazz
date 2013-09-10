package net.bodz.bas.text.rst;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.c.type.TypeArray;
import net.bodz.bas.c.type.TypeEnum;

public class ReflectRstWriter {

    public static void writeObject(IRstOutput out, Object obj)
            throws IOException {
        _dump(out, obj, obj.getClass());
    }

    static void _dump(IRstOutput out, Object obj, Class<?> clazz)
            throws IOException {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            _dump(out, obj, superclass);

        for (Field field : clazz.getDeclaredFields()) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
                continue;

            String name = field.getName();
            Object value;
            try {
                value = field.get(obj);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            if (value == null)
                continue;

            if (value instanceof IRstSerializable) {
                IRstSerializable child = (IRstSerializable) value;
                out.beginElement(name);
                child.writeObject(out);
                out.endElement();
                continue;
            }

            Class<?> type = field.getType();
            switch (TypeEnum.fromClass(type)) {
            case BYTE:
                out.attribute(name, (byte) value);
                break;

            case SHORT:
                out.attribute(name, (short) value);
                break;

            case INT:
                out.attribute(name, (int) value);
                break;

            case LONG:
                out.attribute(name, (long) value);
                break;

            case FLOAT:
                out.attribute(name, (float) value);
                break;

            case DOUBLE:
                out.attribute(name, (double) value);
                break;

            case BOOL:
                out.attribute(name, (boolean) value);
                break;

            case CHAR:
                out.attribute(name, (char) value);
                break;

            case STRING:
                out.attribute(name, (String) value);
                break;

            case CLASS:
                out.attribute(name, ((Class<?>) value).getCanonicalName());
                break;

            case BYTE_ARRAY:
                out.attribute(name, (byte[]) value);
                break;

            case SHORT_ARRAY:
                out.attribute(name, (short[]) value);
                break;

            case INT_ARRAY:
                out.attribute(name, (int[]) value);
                break;

            case LONG_ARRAY:
                out.attribute(name, (long[]) value);
                break;

            case FLOAT_ARRAY:
                out.attribute(name, (float[]) value);
                break;

            case DOUBLE_ARRAY:
                out.attribute(name, (double[]) value);
                break;

            case BOOL_ARRAY:
                out.attribute(name, (boolean[]) value);
                break;

            case CHAR_ARRAY:
                out.attribute(name, (char[]) value);
                break;

            case STRING_ARRAY:
                out.attribute(name, (String[]) value);
                break;

            case CLASS_ARRAY:
                out.attribute(name, TypeArray.getCanonicalNames((Class<?>[]) value));
                break;

            case OBJECT:
            default:
                out.attribute(name, value.toString());
            }
        } // for field
    }

}
