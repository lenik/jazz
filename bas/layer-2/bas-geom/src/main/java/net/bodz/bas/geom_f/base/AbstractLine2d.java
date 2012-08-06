package net.bodz.bas.geom_f.base;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.bas.geom_f.api.AbstractShape2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.api.PickResult2d;
import net.bodz.bas.geom_f.api.PositiveHalfPlane;

public abstract class AbstractLine2d
        extends AbstractShape2d
        implements ILine2d {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractLine2d clone();

    @Override
    public Line2d snapshot() {
        return new Line2d(getPoint0().snapshot(), getPoint1().snapshot());
    }

    @Override
    public Line2d snapshotConst() {
        return new Line2d(getPoint0(), getPoint1());
    }

    @Override
    public boolean isValid() {
        Point2d point0 = getPoint0();
        Point2d point1 = getPoint1();
        if (point0.equals(point1, EPSILON))
            return false;
        else
            return true;
    }

    @Override
    public Point2d degenerate() {
        Point2d point0 = getPoint0();
        Point2d point1 = getPoint1();
        if (point0.equals(point1, EPSILON))
            return point0.snapshot();
        else
            return null;
    }

    // -o IShapeAmount2d

    @Override
    public float getLength() {
        float w = getWidth();
        float h = getHeight();
        return (float) Math.sqrt(w * w + h * h);
    }

    @Override
    public float getArea() {
        float area = getWidth() * getHeight();
        return Math.abs(area);
    }

    //

    @Override
    public float getWidth() {
        return getX1() - getX0();
    }

    @Override
    public float getHeight() {
        return getY1() - getY0();
    }

    @Override
    public float getXaxisA() {
        float k = getSlope();
        return -getYaxisB() / k;
    }

    @Override
    public float getYaxisB() {
        float k = getSlope();
        return getY0() - k * getX0();
    }

    @Override
    public float getSlope() {
        return getHeight() / getWidth();
    }

    @Override
    public float getArg() {
        return toVector().arg();
    }

    public float index(float x, float y) {
        float w = getWidth();
        float h = getHeight();
        if (Math.abs(w) >= Math.abs(h))
            return indexOfX(x);
        else
            return indexOfY(y);
    }

    @Override
    public float indexOfX(float x) {
        return (x - getX0()) / (getX1() - getX0());
    }

    @Override
    public float indexOfY(float y) {
        return (y - getY0()) / (getY1() - getY0());
    }

    public Point2d getPointLinear(float index) {
        float x = getX0() + (getX1() - getX0()) * index;
        float y = getY0() + (getY1() - getY0()) * index;
        return new Point2d(x, y);
    }

    @Override
    public Point2d getIntersectionExtended(Line2d line) {
        float k1 = getSlope();
        float b1 = getYaxisB();
        float k2 = line.getSlope();
        float b2 = line.getYaxisB();
        float D = k2 - k1;
        float Dx = b1 - b2;
        float Dy = k1 * b2 - k2 * b1;
        float x = Dx / D;
        float y = -Dy / D;
        return new Point2d(x, y);
    }

    @Override
    public Point2d getIntersection(Line2d line) {
        Point2d point = getIntersectionExtended(line);
        float d = distance(point);
        if (d < EPSILON)
            return point;
        else
            return null;
    }

    @Override
    public Vector2f toVector() {
        return getPoint0().vectorTo(getPoint1());
    }

    public Vector2f getNormal() {
        return toVector().ccw90_();
    }

    /*
     * Most point-implementation should have a point as itself. This pointCount/pointRef is just for
     * simplifize of create new point type.
     */
    @Override
    public int getPointCount() {
        return 2;
    }

    // -o IPickable2d

    @Override
    public PickResult2d _pick(Point2d point) {
        // the foot of perpendicular (H) may be not on the line.
        Vector2f me = toVector();
        Vector2f _p = getPoint0().vectorTo(point);
        Vector2f p_ = point.vectorTo(getPoint1());

        if (me.dot(_p) <= 0) {
            // index(H) < 0
            Point2d p0 = getPoint0();
            return new PickResult2d(p0, p0.distance(point));
        } else if (me.dot(p_) <= 0) {
            // index(H) > 1
            Point2d p1 = getPoint1();
            return new PickResult2d(p1, p1.distance(point));
        } else {
            float dist = Math.abs(distanceExtended(point));
            return new PickResult2d(this, dist);
        }
    }

    @Override
    public final float distanceExtended(float x, float y) {
        return distanceExtended(new Point2d(x, y));
    }

    @Override
    public float distanceExtended(Point2d point) {
        /*
         * | Ax + By + C | ----------------- SQRT(A^2 + B^2)
         * 
         * A = H B = -W C = -H x1 + W y1
         * 
         * so SQRT(A^2 + B^2) = L Ax + By + C = Hx - Wy - H x1 + W y1
         */

        float W = getWidth();
        float H = getHeight();

        float L = (float) Math.sqrt(W * W + H * H);
        if (L == 0)
            return getPoint0().distance(point);

        float dist = (H * point.x - W * point.y - H * getX0() - W * getY0()) / L;

        // dist < 0 : left to the line
        // dist = 0 : on the line
        // dist > 0 : right to the line
        return -dist; // conver to Left-Hand system
    }

    @Override
    public float distance(Point2d point) {
        PickResult2d result = _pick(point);
        return result.getDistance();
    }

    // -o ICroppable2d

    @Override
    public IShape2d crop(PositiveHalfPlane php) {
        Point2d P = getPoint0();
        Point2d Q = getPoint1();
        Vector2f normal = getNormal();
        float num = normal.dot(php.getPoint0().vectorTo(P));
        float den = normal.dot(P.vectorTo(Q));

        if (den == 0) {
            // Line PQ is parallel to vector@base,
            // so just determine the entire line is visible or invisible
            if (num >= 0)
                return this; // entire visible
            else
                return null; // entire insivible
        }

        // P + (num / den) (Q - P)
        float t = -num / den;
        Vector2f PQ = P.vectorTo(Q);
        Point2d cut = P.add_(PQ.scale_(t));
        if (den > 0) {
            // PQ enters into
            if (t <= 0)
                return this;
            else if (t > 1)
                return null;
            else
                return new Line2d(cut, Q);
        } else {
            // PQ leaves out
            if (t < 0)
                return null;
            else if (t >= 1)
                return this;
            else
                return new Line2d(P, cut);
        }
    }

    @Override
    public String toString() {
        return String.format("<Line x0='%f' y0='%f' x1='%f' y1='%f' />", getX0(), getY0(), getX1(), getY1());
    }

}
