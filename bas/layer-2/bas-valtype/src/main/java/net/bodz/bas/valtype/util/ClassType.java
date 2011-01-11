package net.bodz.bas.valtype.util;

public class ClassType {

    public static boolean isClass(Class<?> clazz) {
        if (clazz.isArray() || clazz.isInterface() || clazz.isPrimitive())
            return false;
        return true;
    }

}
