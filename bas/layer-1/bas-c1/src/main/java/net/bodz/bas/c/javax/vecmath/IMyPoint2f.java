package net.bodz.bas.c.javax.vecmath;

public interface IMyPoint2f
        extends I_Point2f_ {

    /**
     * Computes the square of the distance between this point and point p1.
     * 
     * @param p1
     *            the other point
     */
    float distanceSquared(float x, float y);

    /**
     * Computes the distance between this point and point p1.
     * 
     * @param p1
     *            the other point
     */
    float distance(float x, float y);

    /**
     * Computes the L-1 (Manhattan) distance between this point and point p1. The L-1 distance is
     * equal to abs(x1-x2) + abs(y1-y2).
     * 
     * @param p1
     *            the other point
     */
    float distanceL1(float x, float y);

    /**
     * Computes the L-infinite distance between this point and point p1. The L-infinite distance is
     * equal to MAX[abs(x1-x2), abs(y1-y2)].
     * 
     * @param p1
     *            the other point
     */
    float distanceLinf(float x, float y);

}
