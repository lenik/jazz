package net.bodz.bas.c.type;

public class TypeKind {

    public static boolean isClass(Class<?> clazz) {
        if (clazz.isArray() || clazz.isInterface() || clazz.isPrimitive())
            return false;
        return true;
    }

}
