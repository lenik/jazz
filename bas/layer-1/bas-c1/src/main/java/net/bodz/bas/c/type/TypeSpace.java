package net.bodz.bas.c.type;

import net.bodz.bas.c.primitive.Primitives;

public class TypeSpace {

    private int distanceToUpper;

    private int distanceToNull;
    private int distanceToWrapper;

    public TypeSpace(int distanceToUpper, int distanceToNull, int distanceToWrapper) {
        this.distanceToUpper = distanceToUpper;
        this.distanceToNull = distanceToNull;
        this.distanceToWrapper = distanceToWrapper;
    }

    public int getDistanceToUpper() {
        return distanceToUpper;
    }

    public void setDistanceToUpper(int distanceToUpper) {
        this.distanceToUpper = distanceToUpper;
    }

    public int getDistanceToNull() {
        return distanceToNull;
    }

    public void setDistanceToNull(int distanceToNull) {
        this.distanceToNull = distanceToNull;
    }

    public int getDistanceToWrapper() {
        return distanceToWrapper;
    }

    public void setDistanceToWrapper(int distanceToWrapper) {
        this.distanceToWrapper = distanceToWrapper;
    }

    /**
     * Get the inheritance-distance from <code>declType</code> to <code>actualType</code>.
     * <p>
     * The distance is directed, i.e., <b>dist(a, b) != decl(b, a) with exception of a==b</b>.
     *
     * @param declType
     *            The "base" type
     * @param actualType
     *            The "child" type
     * @return -1 If declType isn't assignable from actualType.
     * @throws NullPointerException
     *             If <code>declType</code> or <code>actualType</code> is <code>null</code>.
     */
    public int dist(Class<?> declType, Class<?> actualType) {
        if (declType.isPrimitive()) {
            if (declType == actualType)
                return 0;
            if (actualType.isPrimitive())
                return -1;
            Class<?> unboxed = Primitives.unbox(actualType);
            return declType == unboxed ? distanceToWrapper : -1;
        } else {
            if (actualType.isPrimitive())
                return -1;
        }

        int dist;
        if (declType.isInterface())
            dist = TypeMath.getMinInterfaceExtendsCount(declType, actualType);
        else
            dist = TypeMath.getClassExtendsCount(declType, actualType);

        if (dist != -1)
            dist *= distanceToUpper;
        return dist;
    }

    /**
     * Calculate distance from one base type vector to another derived type vector.
     *
     * @param nullDistance
     *            The fallback distance from declType to <code>null</code>.
     * @return -1 If <code>actualTypes</code> is not compatible with <code>declTypes</code>. I.e.,
     *         they have different lengths, or any decl-type is not assignable from corresponding
     *         actual-type.
     * @throws NullPointerException
     *             If <code>declTypes</code> or <code>actualTypes</code> is <code>null</code>. Or,
     *             if <code>declTypes</code> contains <code>null</code> element.
     */
    public int dist(Class<?>[] declTypes, Class<?>[] actualTypes) {
        if (declTypes == null)
            throw new NullPointerException("declTypes");
        if (actualTypes == null)
            throw new NullPointerException("actualTypes");
        if (declTypes.length != actualTypes.length)
            return -1;

        int totalDistance = 0;

        for (int i = 0; i < declTypes.length; i++) {
            Class<?> declType = declTypes[i];
            Class<?> actualType = actualTypes[i];

            if (actualType == null) {
                totalDistance += distanceToNull;
                continue;
            }

            int distance = dist(declType, actualType);
            if (distance == -1)
                return -1;

            totalDistance += distance;
        }
        return totalDistance;
    }

    private static TypeSpace defaultTypeSpace = new TypeSpace(1, 1000, 1000);

    public static TypeSpace getDefault() {
        return defaultTypeSpace;
    }

}
