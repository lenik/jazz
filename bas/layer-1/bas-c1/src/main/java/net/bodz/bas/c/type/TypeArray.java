package net.bodz.bas.c.type;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TypeArray {

    /**
     * Get class for each component in the object array.
     * 
     * @param nullClass
     *            Which class to be assumed for <code>null</code>-value in the object array.
     * @param objects
     *            Non-<code>null</code> object array, however, its element maybe <code>null</code>.
     * @return Non-<code>null</code> class array, each element is the class of the corresponding
     *         object in the object array, or <code>nullClass</code> if the object is
     *         <code>null</code>.
     */
    public static Class<?>[] getClasses(Class<?> nullClass, Object... objects) {
        if (objects == null)
            throw new NullPointerException("objects");
        Class<?>[] classes = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null)
                classes[i] = nullClass;
            else
                classes[i] = objects[i].getClass();
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
