package net.bodz.bas.c.type;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.primitive.Primitives;

public class TypeArray {

    private final Class<?>[] array;
    private final int length;

    public TypeArray(Class<?>... array) {
        if (array == null)
            throw new NullPointerException("array");
        this.array = array;
        this.length = array.length;
    }

    public static TypeArray of(Class<?>... array) {
        return new TypeArray(array);
    }

    public static TypeArray fromObjects(Class<?> nullClass, Object... objects) {
        return new TypeArray(getClasses(nullClass, objects));
    }

    public Class<?>[] getArray() {
        return array;
    }

    public int getLength() {
        return length;
    }

    public Class<?> get(int parameterIndex) {
        return array[parameterIndex];
    }

    public void box() {
        for (int i = 0; i < array.length; i++)
            array[i] = Primitives.box(array[i]);
    }

    public void unbox() {
        for (int i = 0; i < array.length; i++)
            array[i] = Primitives.unbox(array[i]);
    }

    public String[] getNames() {
        String[] names = new String[length];
        for (int i = 0; i < length; i++)
            names[i] = array[i].getName();
        return names;
    }

    public String[] getCanonicalNames() {
        String[] names = new String[length];
        for (int i = 0; i < length; i++)
            names[i] = array[i].getCanonicalName();
        return names;
    }

    public String joinNames(String delim, boolean simpleNames) {
        StringBuilder b = null;
        for (Class<?> t : array) {
            if (b == null)
                b = new StringBuilder(length * 30);
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

    public String joinNames(String delim) {
        return joinNames(delim, false);
    }

    public String joinNames(Class<?>... types) {
        return joinNames(", ");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(array);
        result = prime * result + length;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TypeArray other = (TypeArray) obj;
        if (!Arrays.equals(array, other.array))
            return false;
        if (length != other.length)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return joinNames(",  ", true);
    }

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

    static TypeSpace typeSpace = TypeSpace.getDefault();

    public static Map<String, Method> getAssignableMethodMap(Class<?> clazz, Class<?>[] parameterTypes) {
        Map<String, Method> map = new HashMap<String, Method>();

        for (Method method : clazz.getMethods()) {
            if (!isAssignableFrom(parameterTypes, method.getParameterTypes()))
                continue;

            Method existing = map.get(method.getName());
            if (existing != null) {
                int prev = typeSpace.dist(parameterTypes, existing.getParameterTypes());
                int dist = typeSpace.dist(parameterTypes, method.getParameterTypes());
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
