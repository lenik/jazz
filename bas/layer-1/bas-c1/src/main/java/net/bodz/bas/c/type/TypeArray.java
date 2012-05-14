package net.bodz.bas.c.type;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TypeArray {

    /**
     * Get object class of each component in the arg array.
     * 
     * @throws NullPointerException
     *             If <code>args</code> is <code>null</code>.
     * @return Class array each is the class of the corresponding arg.
     */
    public static Class<?>[] getClasses(Object... args) {
        if (args == null)
            throw new NullPointerException("args");
        Class<?>[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null)
                classes[i] = Object.class;
            else
                classes[i] = args[i].getClass();
        }
        return classes;
    }

    /**
     * @return <code>false</code> If the two array of different size, or any element in the array
     *         isn't assignable from rv to lv.
     */
    public static boolean isAssignableFrom(Class<?>[] lv, Class<?>[] rv) {
        return isAssignableFrom(lv, rv, false);
    }

    /**
     * @param moreOrLess
     *            <code>true</code> if rv may contains more parameters then lv.
     */
    public static boolean isAssignableFrom(Class<?>[] lv, Class<?>[] rv, boolean moreOrLess) {
        if (lv.length != rv.length && !moreOrLess)
            return false;

        for (int i = 0; i < lv.length; i++)
            if (!lv[i].isAssignableFrom(rv[i]))
                return false;

        return true;
    }

    public static Map<String, Method> getAssignableMethodMap(Class<?> clazz, Class<?>[] parameterTypes) {
        Map<String, Method> map = new HashMap<String, Method>();

        for (Method method : clazz.getMethods()) {
            if (!isAssignableFrom(parameterTypes, method.getParameterTypes()))
                continue;

            Method existing = map.get(method.getName());
            if (existing != null) {
                int prev = TypeDistance.dist(parameterTypes, existing.getParameterTypes());
                int dist = TypeDistance.dist(parameterTypes, method.getParameterTypes());
                // if (dist==prev) throw new ambiguous;
                if (dist < prev)
                    map.put(method.getName(), method);
                else
                    continue; // skip more unlike ones.
            }
            map.put(method.getName(), method);
        }

        return map;
    }

}
