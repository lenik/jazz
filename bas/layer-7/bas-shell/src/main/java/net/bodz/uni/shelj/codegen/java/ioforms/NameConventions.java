package net.bodz.uni.shelj.codegen.java.ioforms;

import net.bodz.bas.c.string.Strings;

public class NameConventions {

    public static String getMethodName(Class<?> type) {
        if (type.isArray())
            return getMethodName(type.getComponentType()) + "Array";
        if (type.isPrimitive()) {
            String name = type.getName();
            return Strings.ucfirst(name);
        }
        return type.getSimpleName();
    }

}
