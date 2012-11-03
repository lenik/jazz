package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.c.javax.vecmath.IMyPoint2f;
import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.bas.geom.spec0_f.CurveOrientation;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.PositiveHalfPlane2d;

public interface IPointRef2d
        extends IPrimitive2d, IMyPoint2f {

    @Override
    Point2d snapshot();

    @Override
    Point2d snap();

    float getX();

    float getY();

    void setX(float x);

    void setY(float y);

    void add(float dx, float dy);

    void add(javax.vecmath.Vector2f dv);

    void sub(float dx, float dy);

    void sub(javax.vecmath.Vector2f dv);

    Vector2f vectorFrom(float startX, float startY);

    Vector2f vectorFrom(Point2d start);

    Vector2f vectorTo(float endX, float endY);

    Vector2f vectorTo(Point2d end);

    Link2d linkFrom(IPointRef2d start);

    Link2d linkTo(IPointRef2d end);

    Line2d lineFrom(Point2d start);

    Line2d lineTo(Point2d end);

    Line2d lineTo(javax.vecmath.Vector2f dv);

    /**
     * @see CurveOrientation#positive
     */
    PositiveHalfPlane2d positiveTo(Point2d end);

    /**
     * @see CurveOrientation#negative
     */
    PositiveHalfPlane2d negativeTo(Point2d end);

    /**
     * Computes the square of the distance between this point and point p1.
     * 
     * @param p1
     *            the other point
     */
    float distanceSquared(Point2d p1);

    /**
     * Computes the distance between this point and point p1.
     * 
     * @param p1
     *            the other point
     */
    float distance(Point2d p1);

    /**
     * Computes the L-1 (Manhattan) distance between this point and point p1. The L-1 distance is
     * equal to abs(x1-x2) + abs(y1-y2).
     * 
     * @param p1
     *            the other point
     */
    float distanceL1(Point2d p1);

    /**
     * Computes the L-infinite distance between this point and point p1. The L-infinite distance is
     * equal to MAX[abs(x1-x2), abs(y1-y2)].
     * 
     * @param p1
     *            the other point
     */
    float distanceLinf(Point2d p1);

}
