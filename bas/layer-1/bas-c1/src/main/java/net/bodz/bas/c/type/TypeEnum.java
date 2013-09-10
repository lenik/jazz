package net.bodz.bas.c.type;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.primitive.Primitives;

public enum TypeEnum {

    OBJECT(Object.class),

    INT(int.class),

    BYTE(byte.class),

    SHORT(short.class),

    LONG(long.class),

    FLOAT(float.class),

    DOUBLE(double.class),

    BOOL(boolean.class),

    CHAR(char.class),

    STRING(String.class),

    // iSTRING(iString.class),

    CLASS(Class.class),

    INT_ARRAY(int[].class),

    BYTE_ARRAY(byte[].class),

    SHORT_ARRAY(short[].class),

    LONG_ARRAY(long[].class),

    FLOAT_ARRAY(float[].class),

    DOUBLE_ARRAY(double[].class),

    BOOL_ARRAY(boolean[].class),

    CHAR_ARRAY(char[].class),

    STRING_ARRAY(String[].class),

    // iSTRING_ARRAY(iString[].class),

    CLASS_ARRAY(Class[].class),

    ;

    private final Class<?> type;

    private TypeEnum(Class<?> type) {
        this.type = type;
        TypeEnumMap.map.put(type, this);

        if (type.isPrimitive()) {
            Class<?> boxedType = Primitives.box(type);
            TypeEnumMap.map.put(boxedType, this);
        }
    }

    public Class<?> getType() {
        return type;
    }

    public static TypeEnum fromClass(Class<?> clazz) {
        // Class<?> unboxed = Primitives.unbox(clazz);
        return TypeEnumMap.map.get(clazz);
    }

}

class TypeEnumMap {

    static Map<Class<?>, TypeEnum> map = new HashMap<Class<?>, TypeEnum>();

}