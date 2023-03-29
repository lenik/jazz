package net.bodz.bas.data.struct;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.c.type.TypeEnum;

public class ReflectSizeComputer {

    private final Object obj;

    public ReflectSizeComputer(Object obj) {
        this.obj = obj;
    }

    public int sizeof() {
        return _sizeof(obj.getClass());
    }

    private int _sizeof(Class<?> clazz) {
        int sum = 0;

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            sum += _sizeof(superclass);

        for (Field field : clazz.getDeclaredFields()) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
                continue;

            boolean _final = Modifier.isFinal(field.getModifiers());
            Class<?> type = field.getType();

            Object value = null;
            int multiplier = 1;
            if (_final) {
                try {
                    field.setAccessible(true);
                    value = field.get(obj);
                } catch (Exception e) {
                    throw new RuntimeException("failed to read field " + field.getName(), e);
                }

                if (value instanceof IDataStruct) {
                    sum += ((IDataStruct) value).dataSize();
                    continue;
                }

                else if (type.isArray()) {
                    multiplier = Array.getLength(value);
                    type = type.getComponentType();
                }
            }

            TypeEnum typeEnum = TypeEnum.forClass(type);
            if (typeEnum == null)
                continue;

            int size = 0;
            switch (typeEnum) {
            case BYTE:
            case BYTE_ARRAY:
                size = 1;
                break;

            case SHORT:
            case SHORT_ARRAY:
                size = 2;
                break;

            case INT:
            case INT_ARRAY:
                size = 4;
                break;

            case LONG:
            case LONG_ARRAY:
                size = 8;
                break;

            case FLOAT:
            case FLOAT_ARRAY:
                size = 4;
                break;

            case DOUBLE:
            case DOUBLE_ARRAY:
                size = 8;
                break;

            case BOOL:
            case BOOL_ARRAY:
                size = 1;
                break;

            case CHAR:
            case CHAR_ARRAY:
                size = 2; // 1...n ??? various
                break;

            case STRING:
            case STRING_ARRAY:
                // ??? various
                break;

            case CLASS:
            case CLASS_ARRAY:
                // ??? various
                break;

            case OBJECT:
            default:
            }

            size *= multiplier;
            sum += size;
        } // for field
        return sum;
    }

}
