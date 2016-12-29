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

    ENUM(Enum.class),

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

    // TODO The array type of sub-enums, don't have a `super`-class.
    ENUM_ARRAY(Enum[].class),

    CLASS_ARRAY(Class[].class),

    ;

    private final Class<?> baseType;

    private TypeEnum(Class<?> baseType) {
        this.baseType = baseType;
        Preload.typeMap.put(baseType, this);

        if (baseType.isPrimitive()) {
            Class<?> boxedType = Primitives.box(baseType);
            Preload.typeMap.put(boxedType, this);
        }
    }

    public Class<?> getBaseType() {
        return baseType;
    }

    public static TypeEnum forClass(Class<?> clazz) {
        while (clazz != null) {
            TypeEnum te = Preload.typeMap.get(clazz);
            if (te != null)
                return te;
            else
                clazz = clazz.getSuperclass();
        }
        return null;
    }

    static class Preload {
        static Map<Class<?>, TypeEnum> typeMap = new HashMap<Class<?>, TypeEnum>();
    }

}
