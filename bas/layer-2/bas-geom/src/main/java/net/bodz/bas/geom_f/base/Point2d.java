package net.bodz.bas.geom_f.base;

import javax.vecmath.Tuple2f;

import net.bodz.bas.c.javax.vecmath.Vector2f;

public class Point2d
        extends AbstractPointRef2d {

    private static final long serialVersionUID = 1L;

    public float x;
    public float y;

    public Point2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point2d(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Point2d(IPointRef2d point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public Point2d(Point2d point) {
        this.x = point.x;
        this.y = point.y;
    }

    @Override
    public final Point2d clone() {
        return new Point2d(x, y);
    }

    @Override
    public Point2d snapshot() {
        return clone();
    }

    public boolean equals(Point2d o, float epsilon) {
        if (o == null)
            return false;

        float dx = Math.abs(o.x - x);
        if (dx > epsilon)
            return false;

        float dy = Math.abs(o.y - y);
        if (dy > epsilon)
            return false;

        return true;
    }

    @Override
    public Point2d getPoint(int index) {
        switch (index) {
        case 0:
            return this;
        }
        return getBadPoint(index);
    }

    @Override
    public IPointRef2d getPointRef(int index) {
        return getPoint(index);
    }

    @Override
    public final float getX() {
        return x;
    }

    @Override
    public final void setX(float x) {
        this.x = x;
    }

    @Override
    public final float getY() {
        return y;
    }

    @Override
    public final void setY(float y) {
        this.y = y;
    }

    public float crossProduct(Point2d point) {
        float l = x * point.y - y * point.x;
        return l;
    }

    /**
     * Assume p=this, returns the length of pq x pr.
     */
    public float crossProduct(Point2d q, Point2d r) {
        float qx = q.x - x;
        float qy = q.y - y;
        float rx = r.x - x;
        float ry = r.y - y;
        float result = qx * ry - qy * rx;
        return result;
    }

    /**
     * Get the angle between vector this-p1 and this-p2.
     */
    public float angle(Point2d p1, Point2d p2) {
        Vector2f v1 = vectorTo(p1);
        Vector2f v2 = vectorTo(p2);
        float angle = v1.angle(v2);
        return angle;
    }

    // I_Point2f_

    @Override
    public final Point2d add_(Tuple2f t1) {
        this.add(t1);
        return this;
    }

    @Override
    public final Point2d sub_(Tuple2f t1) {
        this.sub(t1);
        return this;
    }

    @Override
    public final Point2d negate_(Tuple2f t1) {
        this.negate(t1);
        return this;
    }

    @Override
    public final Point2d negate_() {
        this.negate();
        return this;
    }

    @Override
    public final Point2d scale_(float s) {
        this.scale(s);
        return this;
    }

    @Override
    public final Point2d scaleAdd_(float s, Tuple2f t1) {
        this.scaleAdd(s, t1);
        return this;
    }

    @Override
    public final Point2d absolute_(Tuple2f t) {
        this.absolute(t);
        return this;
    }

    @Override
    public final Point2d clamp_(float min, float max) {
        this.clamp(min, max);
        return this;
    }

    @Override
    public final Point2d clampMin_(float min) {
        this.clampMin(min);
        return this;
    }

    @Override
    public final Point2d clampMax_(float max) {
        this.clampMax(max);
        return this;
    }

    @Override
    public final Point2d absolute_() {
        this.absolute();
        return this;
    }

    @Override
    public final Point2d interpolate_(Tuple2f t1, float alpha) {
        this.interpolate(t1, alpha);
        return this;
    }

}