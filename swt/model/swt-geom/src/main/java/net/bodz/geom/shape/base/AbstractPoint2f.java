package net.bodz.geom.shape.base;

import java.io.Serializable;

import javax.vecmath.Vector2f;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.geom.shape.ReadOnlyException;

public abstract class AbstractPoint2f
        extends AbstractShape2f
        implements Point2f, Serializable {

    private static final long serialVersionUID = 7812541709920528959L;

    public void x(float x) {
        throw new ReadOnlyException();
    }

    public void y(float y) {
        throw new ReadOnlyException();
    }

    public void add(float dx, float dy) {
        x(x() + dx);
        y(y() + dy);
    }

    public void add(Vector2f dv) {
        x(x() + dv.x);
        y(y() + dv.y);
    }

    public void sub(float dx, float dy) {
        x(x() - dx);
        y(y() - dy);
    }

    public void sub(Vector2f dv) {
        x(x() - dv.x);
        y(y() - dv.y);
    }

    public Point2f toAdd(float dx, float dy) {
        Point2f out = clone();
        out.toAdd(dx, dy);
        return out;
    }

    public Point2f toAdd(Vector2f dv) {
        Point2f out = clone();
        out.toAdd(dv.x, dv.y);
        return out;
    }

    public Point2f toSub(float dx, float dy) {
        Point2f out = clone();
        out.toSub(dx, dy);
        return out;
    }

    public Point2f toSub(Vector2f dv) {
        Point2f out = clone();
        out.toSub(dv.x, dv.y);
        return out;
    }

    public Vector2f vectorFrom(float x0, float y0) {
        return new Vector2f(x() - x0, y() - y0);
    }

    public Vector2f vectorFrom(Point2f start) {
        assert start != null;
        return vectorFrom(start.x(), start.y());
    }

    public Vector2f vectorTo(float x1, float y1) {
        return new Vector2f(x1 - x(), y1 - y());
    }

    public Vector2f vectorTo(Point2f end) {
        assert end != null;
        return vectorTo(end.x(), end.y());
    }

    public Line2f lineFromRef(Point2f start) {
        assert start != null;
        return new Line2f.Ref(start, this);
    }

    public Line2f lineToRef(Point2f end) {
        assert end != null;
        return new Line2f.Ref(this, end);
    }

    public Line2f.Static lineFrom(Point2f start) {
        assert start != null;
        return new Line2f.Static(start.x(), start.y(), x(), y());
    }

    public Line2f.Static lineTo(Point2f end) {
        assert end != null;
        return new Line2f.Static(x(), y(), end.x(), end.y());
    }

    public Line2f.Static lineTo(Vector2f dv) {
        assert dv != null;
        return new Line2f.Static(x(), y(), x() + dv.x, y() + dv.y);
    }

    public Line2f.Static halfPlane(Vector2f normal) {
        assert normal != null;
        Vector2f v = normal.getNormalOrig();
        return lineTo(v);
    }

    @Override
    public String toString() {
        return String.format("<Point x='%f' y='%f' />", x(), y());
    }

    // -o Pick

    @Override
    public PickInfo2f pickInfo(float x, float y) {
        return new PickInfo2f(this, distance(x, y));
    }

    @Override
    public float distance(float x, float y) {
        return vectorTo(x, y).length();
    }

    public float distanceSquared(Point2f point) {
        return distance(point.x(), point.y());
    }

    public float distanceSquared(float x, float y) {
        float dx = x - x();
        float dy = y - y();
        return dx * dx + dy * dy;
    }

    /**
     * Is this point at left of a line specified by <base, normal>.
     */
    public final boolean inside(Point2f base, Vector2f normal) {
        Vector2f BP = new Vector2f(x() - base.x(), y() - base.y());
        // so BP(<bx, by>) dot N == |BP| * |N| * cos a
        // where a is the angle of BP-N
        // cos a > 0 if BP at left side of N, == 0 if BP on the normal line
        float v = normal.dot(BP);
        return v > 0;
    }

    // -o Shape

    /*
     * Most point-implementation should have a point as itself. This pointCount/pointRef is just for
     * simplifize of create new point type.
     */
    public int pointCount() {
        return 1;
    }

    public Point2f pointRef(int index) {
        if (index == 0)
            return this;
        return null;
    }

    @Override
    public Point2f.Static point(int index) {
        if (index != 0)
            return null;
        return new Point2f.Static(x(), y());
    }

    @Override
    public Point2f spointRef(int id) {
        if (id == SP_CENTER)
            return this;
        return super.spointRef(id);
    }

    /*
     * void move(float dx, float dy); void scale(float kx, float ky); void rotate(float angle); void
     * scale(float kx, float ky, float baseX, float baseY); void rotate(float angle, float baseX,
     * float baseY);
     */

    @Override
    public void translate(float dx, float dy) {
        add(dx, dy);
    }

    @Override
    public void scale(float kx, float ky) {
        x(x() * kx);
        y(y() * ky);
    }

    @Override
    public void rotate(float angle) {
        Vector2f v = new Vector2f(x(), y());
        v.rotate(angle);
        x(v.x);
        y(v.y);
    }

    @Override
    public void scale(float kx, float ky, float baseX, float baseY) {
        float x = x();
        float y = y();
        x(kx * (x - baseX) + baseX);
        y(ky * (y - baseY) + baseY);
    }

    @Override
    public void rotate(float angle, float baseX, float baseY) {
        Vector2f v = new Vector2f(x(), y());
        v.x -= baseX;
        v.y -= baseY;
        v.rotate(angle);
        v.x += baseX;
        v.y += baseY;
        x(v.x);
        y(v.y);
    }

    @Override
    public Point2f crop(Point2f baseHalfPlane, Vector2f normal) {
        if (inside(baseHalfPlane, normal))
            return this;
        return null;
    }

    public Vector2f convertToVector2f() {
        return new Vector2f(x(), y());
    }

    public Point2f convertToPoint2f() {
        return clone();
    }

    public void draw(DrawTarget2f target)
            throws DrawException {
        target.drawPixel(this);
    }

    @Override
    public Point2f.Static snapshot() {
        return new Point2f.Static(x(), y());
    }

    @Override
    public abstract AbstractPoint2f clone();

}
