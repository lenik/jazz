package net.bodz.bas.type.util;

public class TypeDistance {

    /**
     * @return -1 If the declared typ is incompatible with the actual type.
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    public static int getExtendsDepth(Class<?> declType, Class<?> actualType) {
        if (declType == null)
            throw new NullPointerException("declType");
        if (actualType == null)
            throw new NullPointerException("actualType");

        int dist = -1;
        while (declType.isAssignableFrom(actualType)) {
            dist++;
            actualType = actualType.getSuperclass();
            if (actualType == null)
                break;
        }
        return dist;
    }

    /**
     * @return -1 If any of declared type is incompatible with the corresponding actual type
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    public static int sumExtendsDepth(Class<?>[] declTypes, Class<?>[] actualTypes) {
        if (declTypes == null)
            throw new NullPointerException("declTypes");
        if (actualTypes == null)
            throw new NullPointerException("actualTypes");
        if (declTypes.length != actualTypes.length)
            return -1;

        int distsum = 0;
        for (int i = 0; i < declTypes.length; i++) {
            if (actualTypes[i] == null) // option?
                continue;
            int dist = getExtendsDepth(declTypes[i], actualTypes[i]);
            if (dist < 0)
                return -1;
            distsum += dist;
        }
        return distsum;
    }

}
