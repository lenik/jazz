package net.bodz.bas.c.type;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

import net.bodz.bas.c.string.Strings;

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

    /**
     * Get a user-friendly simple name of a type. The name suffix (like "List" in "ArrayList") is
     * auto removed, to make the result name shorter.
     * 
     * @param type
     *            Get friendly name from this type. <code>type</code>.
     * @return Hyphenatized simple name.
     */
    public static String friendly_name(Class<?> type) {
        return friendly_name(type, false);
    }

    /**
     * Get a user-friendly simple name of a type. The name suffix (like "List" in "ArrayList") is
     * auto removed, to make the result name shorter.
     * 
     * @param type
     *            Get friendly name from this type. <code>type</code>.
     * @param findShortest
     *            Whether or not to find the shortest form.
     * @return Hyphenatized simple name.
     */
    public static String friendly_name(Class<?> type, boolean findShortest) {
        Class<?> parent = type.getSuperclass();
        String shortest = null;
        while (parent != null) {
            int mod = parent.getModifiers();
            if (Modifier.isAbstract(mod)) {
                String name = friendly_name(type, parent);
                if (!findShortest) {
                    shortest = name;
                    break;
                }
                if (shortest == null || name.length() < shortest.length())
                    shortest = name;
            }
            parent = parent.getSuperclass();
        }
        return shortest;
    }

    /**
     * Get a user-friendly simple name of a type. The name suffix (like "List" in "ArrayList") is
     * auto removed, to make the result name shorter.
     * 
     * @param type
     *            Get friendly name from this type.
     * @param baseType
     *            An abstract of interface type, whose name is used as a suffix to the
     *            <code>type</code>. Maybe <code>null</code>.
     * @return Hyphenatized simple name.
     */
    public static String friendly_name(Class<?> type, Class<?> baseType) {
        String name = type.getSimpleName();
        while (name.startsWith("_"))
            name = name.substring(1);

        if (baseType != null) {
            int mod = baseType.getModifiers();
            String baseName = baseType.getSimpleName();

            while (baseName.startsWith("_"))
                baseName = baseName.substring(1);

            if (Modifier.isAbstract(mod)) // Remove Abstract... from abstract class.
                if (baseName.startsWith("Abstract"))
                    if (baseName.length() > 8 && Character.isUpperCase(baseName.charAt(8)))
                        baseName = baseName.substring(8);

            if (Modifier.isInterface(mod)) // Remove I... from interface type.
                if (baseName.startsWith("I"))
                    if (baseName.length() > 1 && Character.isUpperCase(baseName.charAt(1)))
                        baseName = baseName.substring(1);

            int nsuffix = baseName.length();
            if (name.endsWith(baseName))
                if (name.length() > nsuffix && Character.isUpperCase(name.charAt(nsuffix)))
                    name = name.substring(0, name.length() - nsuffix);
        }

        // name = Strings.lcfirst(name);
        return Strings.hyphenatize(name);
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
