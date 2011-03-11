package net.bodz.bas.valtype.util;

import java.util.Arrays;
import java.util.Collection;

public class TypeName {

    public static String pretty(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        String name = type.getCanonicalName();
        if (type.isInterface())
            return "-o " + name;
        if (type.isAnnotation())
            return "@" + name;
        if (type.isEnum())
            return "#" + name;
        if (type.isSynthetic())
            return "%" + name;
        return name;
    }

    public static String join(String delim, boolean simpleNames, Collection<Class<?>> types) {
        StringBuilder b = null;
        for (Class<?> t : types) {
            if (b == null)
                b = new StringBuilder(types.size() * 30);
            else
                b.append(delim);
            String n;
            if (simpleNames)
                // || t.getCanonicalName().startsWith("java.lang."))
                n = t.getSimpleName();
            else
                n = t.getName();
            b.append(n);
        }
        return b == null ? "" : b.toString();
    }

    public static String join(String delim, boolean simpleNames, Class<?>... types) {
        return join(delim, simpleNames, Arrays.asList(types));
    }

    public static String join(String delim, Class<?>... types) {
        return join(delim, false, types);
    }

    public static String join(Class<?>... types) {
        return join(", ", types);
    }

}
