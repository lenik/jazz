package net.bodz.bas.geom_f.base;

import javax.vecmath.Tuple2d;
import javax.vecmath.Tuple2f;

import net.bodz.bas.c.javax.vecmath.I_Tuple2f_;
import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.bas.geom_f.api.AbstractShape2d;
import net.bodz.bas.geom_f.api.PickResult2d;
import net.bodz.bas.geom_f.api.PositiveHalfPlane;

public abstract class AbstractPointRef2d
        extends AbstractShape2d
        implements IPointRef2d {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractPointRef2d clone();

    @Override
    public Point2d snapshot() {
        return new Point2d(getX(), getY());
    }

    @Override
    public Point2d snapshotConst() {
        return snapshot();
    }

    @Override
    public Point2d degenerate() {
        return snapshot();
    }

    // -o IPointSet2d

    /*
     * Most point-implementation should have a point as itself. This pointCount/pointRef is just for
     * simplifize of create new point type.
     */
    @Override
    public int getPointCount() {
        return 1;
    }

    @Override
    public Point2d getPoint(int index) {
        if (index == 0)
            return snapshot();
        else
            return null;
    }

    @Override
    public IPointRef2d getPointRef(int index) {
        if (index == 0)
            return this;
        else
            return null;
    }

    @Override
    public void add(float dx, float dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    @Override
    public void add(javax.vecmath.Vector2f dv) {
        setX(getX() + dv.x);
        setY(getY() + dv.y);
    }

    @Override
    public void sub(float dx, float dy) {
        setX(getX() - dx);
        setY(getY() - dy);
    }

    @Override
    public void sub(javax.vecmath.Vector2f dv) {
        setX(getX() - dv.x);
        setY(getY() - dv.y);
    }

    // public float crossProduct(Point2d point) {
    // float l = getX() * point.y - getY() * point.x;
    // return l;
    // }

    @Override
    public Vector2f vectorFrom(float x0, float y0) {
        return new Vector2f(getX() - x0, getY() - y0);
    }

    @Override
    public Vector2f vectorFrom(Point2d start) {
        assert start != null;
        return vectorFrom(start.getX(), start.getY());
    }

    @Override
    public Vector2f vectorTo(float x1, float y1) {
        return new Vector2f(x1 - getX(), y1 - getY());
    }

    @Override
    public Vector2f vectorTo(Point2d end) {
        assert end != null;
        return vectorTo(end.getX(), end.getY());
    }

    @Override
    public Link2d linkFrom(IPointRef2d start) {
        assert start != null;
        return new Link2d(start, this);
    }

    @Override
    public Link2d linkTo(IPointRef2d end) {
        assert end != null;
        return new Link2d(this, end);
    }

    @Override
    public Line2d lineFrom(Point2d start) {
        assert start != null;
        return new Line2d(start.getX(), start.getY(), getX(), getY());
    }

    @Override
    public Line2d lineTo(Point2d end) {
        assert end != null;
        return new Line2d(getX(), getY(), end.getX(), end.getY());
    }

    @Override
    public Line2d lineTo(javax.vecmath.Vector2f dv) {
        assert dv != null;
        return new Line2d(getX(), getY(), getX() + dv.x, getY() + dv.y);
    }

    @Override
    public PositiveHalfPlane positiveTo(Point2d end) {
        return new PositiveHalfPlane(snapshot(), end);
    }

    @Override
    public PositiveHalfPlane negativeTo(Point2d end) {
        return new PositiveHalfPlane(end, snapshot());
    }

    // -o IPickable2d

    @Override
    public PickResult2d _pick(Point2d point) {
        return new PickResult2d(this, distance(point));
    }

    @Override
    public float distance(Point2d point) {
        return vectorTo(point).length();
    }

    @Override
    public float distanceSquared(Point2d point) {
        return distance(point.getX(), point.getY());
    }

    @Override
    public float distanceSquared(float x, float y) {
        float dx = x - getX();
        float dy = y - getY();
        return dx * dx + dy * dy;
    }

    // -o ITransformable2d

    @Override
    public void translate(float dx, float dy) {
        add(dx, dy);
    }

    @Override
    public void translate(javax.vecmath.Vector2f dv) {
        add(dv);
    }

    @Override
    public void scale(float k) {
        setX(getX() * k);
        setY(getY() * k);
    }

    @Override
    public void scale(float kx, float ky) {
        setX(getX() * kx);
        setY(getY() * ky);
    }

    @Override
    public void scaleAt(float k, Point2d basePoint) {
        scaleAt(k, k, basePoint);
    }

    @Override
    public void scaleAt(float kx, float ky, Point2d base) {
        float x = getX();
        float y = getY();
        setX(kx * (x - base.x) + base.x);
        setY(ky * (y - base.y) + base.y);
    }

    @Override
    public void rotate(float angle) {
        Vector2f v = new Vector2f(getX(), getY());
        v.rotate(angle);
        setX(v.x);
        setY(v.y);
    }

    @Override
    public void rotateAt(float angle, Point2d base) {
        Vector2f v = new Vector2f(getX(), getY());
        v.x -= base.x;
        v.y -= base.y;
        v.rotate(angle);
        v.x += base.x;
        v.y += base.y;
        setX(v.x);
        setY(v.y);
    }

    // -o ICroppable2d

    @Override
    public Point2d crop(PositiveHalfPlane php, boolean detached) {
        Point2d point = this.snapshot();
        if (php.contains(point))
            return point;
        else
            return null;
    }

    @Override
    public Point2d crop(Rectangle2d rectangle, boolean detached) {
        Point2d point = this.snapshot();
        if (rectangle.contains(point))
            return point;
        else
            return null;
    }

    @Override
    public Point2d crop(Triangle2d triangle, boolean detached) {
        Point2d point = this.snapshot();
        if (triangle.contains(point))
            return point;
        else
            return null;
    }

    @Override
    public Point2d crop(Polygon2d polygon, boolean detached) {
        Point2d point = this.snapshot();
        if (polygon.contains(point))
            return point;
        else
            return null;
    }

    // -o IPolygonizable2d

    @Override
    public Polygon2d polygonize() {
        return null;
    }

    // I_Tuple2f

    @Override
    public void set(float x, float y) {
        setX(x);
        setY(y);
    }

    @Override
    public void set(float[] t) {
        setX(t[0]);
        setY(t[1]);
    }

    @Override
    public void set(Tuple2f t1) {
        setX(t1.x);
        setY(t1.y);
    }

    @Override
    public void set(Tuple2d t1) {
        setX((float) t1.x);
        setY((float) t1.y);
    }

    @Override
    public void get(float[] t) {
        t[0] = getX();
        t[1] = getY();
    }

    @Override
    public void add(Tuple2f t1, Tuple2f t2) {
        setX(t1.x + t2.x);
        setY(t1.y + t2.y);
    }

    @Override
    public void add(Tuple2f t1) {
        setX(getX() + t1.x);
        setY(getY() + t1.y);
    }

    @Override
    public void sub(Tuple2f t1, Tuple2f t2) {
        setX(t1.x - t2.x);
        setY(t1.y - t2.y);
    }

    @Override
    public void sub(Tuple2f t1) {
        setX(getX() - t1.x);
        setY(getY() - t1.y);
    }

    @Override
    public void negate(Tuple2f t1) {
        setX(-t1.x);
        setY(-t1.y);
    }

    @Override
    public void negate() {
        setX(-getX());
        setY(-getY());
    }

    @Override
    public void scale(float s, Tuple2f t1) {
        setX(s * t1.x);
        setY(s * t1.y);
    }

    @Override
    public void scaleAdd(float s, Tuple2f t1, Tuple2f t2) {
        setX(s * t1.x + t2.x);
        setY(s * t1.y + t2.y);
    }

    @Override
    public void scaleAdd(float s, Tuple2f t1) {
        setX(s * getX() + t1.x);
        setY(s * getY() + t1.y);
    }

    @Override
    public boolean epsilonEquals(Tuple2f t1, float epsilon) {
        float d = Math.abs(getX() - t1.x) + Math.abs(getY() - t1.y);
        return d <= epsilon;
    }

    @Override
    public void clamp(float min, float max, Tuple2f t) {
        float cx = t.x < min ? min : (t.x > max ? max : t.x);
        float cy = t.y < min ? min : (t.y > max ? max : t.y);
        setX(cx);
        setY(cy);
    }

    @Override
    public void clampMin(float min, Tuple2f t) {
        float cx = t.x;
        float cy = t.y;
        if (cx < min)
            cx = min;
        if (cy < min)
            cy = min;
        setX(cx);
        setY(cy);
    }

    @Override
    public void clampMax(float max, Tuple2f t) {
        float cx = t.x;
        float cy = t.y;
        if (cx > max)
            cx = max;
        if (cy > max)
            cy = max;
        setX(cx);
        setY(cy);
    }

    @Override
    public void absolute(Tuple2f t) {
        setX(Math.abs(t.x));
        setY(Math.abs(t.y));
    }

    @Override
    public void clamp(float min, float max) {
        float cx = getX();
        float cy = getY();
        cx = cx < min ? min : (cx > max ? max : cx);
        cy = cy < min ? min : (cy > max ? max : cy);
        setX(cx);
        setY(cy);
    }

    @Override
    public void clampMin(float min) {
        float cx = getX();
        float cy = getY();
        if (cx < min)
            cx = min;
        if (cy < min)
            cy = min;
        setX(cx);
        setY(cy);
    }

    @Override
    public void clampMax(float max) {
        float cx = getX();
        float cy = getY();
        if (cx > max)
            cx = max;
        if (cy > max)
            cy = max;
        setX(cx);
        setY(cy);
    }

    @Override
    public void absolute() {
        setX(Math.abs(getX()));
        setY(Math.abs(getY()));
    }

    @Override
    public void interpolate(Tuple2f t1, Tuple2f t2, float alpha) {
        float v = 1 - alpha;
        float mx = v * t1.x + alpha * t2.x;
        float my = v * t1.y + alpha * t2.y;
        setX(mx);
        setY(my);
    }

    @Override
    public void interpolate(Tuple2f t1, float alpha) {
        float v = 1 - alpha;
        float mx = v * getX() + alpha * t1.x;
        float my = v * getY() + alpha * t1.y;
        setX(mx);
        setY(my);
    }

    // I_Tuple2f_

    @Override
    public I_Tuple2f_ add_(Tuple2f t1) {
        add(t1);
        return this;
    }

    @Override
    public I_Tuple2f_ sub_(Tuple2f t1) {
        sub(t1);
        return this;
    }

    @Override
    public I_Tuple2f_ negate_(Tuple2f t1) {
        negate(t1);
        return this;
    }

    @Override
    public I_Tuple2f_ negate_() {
        negate();
        return this;
    }

    @Override
    public I_Tuple2f_ scale_(float s) {
        scale(s);
        return this;
    }

    @Override
    public I_Tuple2f_ scaleAdd_(float s, Tuple2f t1) {
        scaleAdd(s, t1);
        return this;
    }

    @Override
    public I_Tuple2f_ absolute_(Tuple2f t) {
        absolute(t);
        return this;
    }

    @Override
    public I_Tuple2f_ clamp_(float min, float max) {
        clamp(min, max);
        return this;
    }

    @Override
    public I_Tuple2f_ clampMin_(float min) {
        clampMin(min);
        return this;
    }

    @Override
    public I_Tuple2f_ clampMax_(float max) {
        clampMax(max);
        return this;
    }

    @Override
    public I_Tuple2f_ absolute_() {
        absolute();
        return this;
    }

    @Override
    public I_Tuple2f_ interpolate_(Tuple2f t1, float alpha) {
        interpolate(t1, alpha);
        return this;
    }

    // IMyPoint2f

    @Override
    public final float distanceL1(float x, float y) {
        return distanceL1(new Point2d(x, y));
    }

    @Override
    public float distanceL1(Point2d p1) {
        float d = Math.abs(getX() - p1.x) + Math.abs(getY() - p1.y);
        return d;
    }

    @Override
    public final float distanceLinf(float x, float y) {
        return distanceLinf(new Point2d(x, y));
    }

    @Override
    public float distanceLinf(Point2d p1) {
        float d = Math.max(Math.abs(getX() - p1.x), Math.abs(getY() - p1.y));
        return d;
    }

    @Override
    public String toString() {
        return String.format("<Point x='%f' y='%f' />", getX(), getY());
    }

}
