package net.bodz.geom.shape.base;

import java.io.Serializable;

import javax.vecmath.Point2f;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.ReadOnlyException;

public class AbstractPoint2f
        extends AbstractShape2f
        implements IPoint2f, Serializable {

    private static final long serialVersionUID = 7812541709920528959L;

    @Override
    public void scaleAt(float k, Point2f basePoint) {
    }

    @Override
    public void scaleAt(float kx, float ky, Point2f basePoint) {
    }

    @Override
    public void scaleAt(javax.vecmath.Vector2f kv, Point2f basePoint) {
    }

    @Override
    public void rotateAt(float angle, Point2f basePoint) {
    }

    @Override
    public IShape2f crop(Point2f baseHalfPlane, javax.vecmath.Vector2f normal) {
        return null;
    }

    @Override
    public IShape2f crop(net.bodz.geom.shape.base.IPoint2f baseHalfPlane, javax.vecmath.Vector2f normal) {
        return null;
    }

    @Override
    public void x(float x) {
        throw new ReadOnlyException();
    }

    @Override
    public void y(float y) {
        throw new ReadOnlyException();
    }

    @Override
    public void add(float dx, float dy) {
        x(x() + dx);
        y(y() + dy);
    }

    @Override
    public void add(Vector2f dv) {
        x(x() + dv.x);
        y(y() + dv.y);
    }

    @Override
    public void sub(float dx, float dy) {
        x(x() - dx);
        y(y() - dy);
    }

    @Override
    public void sub(Vector2f dv) {
        x(x() - dv.x);
        y(y() - dv.y);
    }

    @Override
    public Point2f addCopy(float dx, float dy) {
        Point2f out = clone();
        out.addCopy(dx, dy);
        return out;
    }

    @Override
    public Point2f addCopy(Vector2f dv) {
        Point2f out = clone();
        out.addCopy(dv.x, dv.y);
        return out;
    }

    @Override
    public Point2f subCopy(float dx, float dy) {
        Point2f out = clone();
        out.subCopy(dx, dy);
        return out;
    }

    @Override
    public Point2f subCopy(Vector2f dv) {
        Point2f out = clone();
        out.subCopy(dv.x, dv.y);
        return out;
    }

    @Override
    public Vector2f vectorFrom(float x0, float y0) {
        return new Vector2f(x() - x0, y() - y0);
    }

    @Override
    public Vector2f vectorFrom(Point2f start) {
        assert start != null;
        return vectorFrom(start.x(), start.y());
    }

    @Override
    public Vector2f vectorTo(float x1, float y1) {
        return new Vector2f(x1 - x(), y1 - y());
    }

    @Override
    public Vector2f vectorTo(Point2f end) {
        assert end != null;
        return vectorTo(end.x(), end.y());
    }

    @Override
    public ILine2f lineFromRef(Point2f start) {
        assert start != null;
        return new ILine2f.Ref(start, this);
    }

    @Override
    public ILine2f lineToRef(Point2f end) {
        assert end != null;
        return new ILine2f.Ref(this, end);
    }

    @Override
    public ILine2f.Static lineFrom(Point2f start) {
        assert start != null;
        return new ILine2f.Static(start.x(), start.y(), x(), y());
    }

    @Override
    public ILine2f.Static lineTo(Point2f end) {
        assert end != null;
        return new ILine2f.Static(x(), y(), end.x(), end.y());
    }

    @Override
    public ILine2f.Static lineTo(Vector2f dv) {
        assert dv != null;
        return new ILine2f.Static(x(), y(), x() + dv.x, y() + dv.y);
    }

    @Override
    public ILine2f.Static halfPlane(Vector2f normal) {
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

    @Override
    public float distanceSquared(Point2f point) {
        return distance(point.x(), point.y());
    }

    @Override
    public float distanceSquared(float x, float y) {
        float dx = x - x();
        float dy = y - y();
        return dx * dx + dy * dy;
    }

    /**
     * Is this point at left of a line specified by <base, normal>.
     */
    @Override
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
    @Override
    public int pointCount() {
        return 1;
    }

    @Override
    public Point2f pointRef(int index) {
        if (index == 0)
            return this;
        return null;
    }

    @Override
    public StaticPoint2f point(int index) {
        if (index != 0)
            return null;
        return new StaticPoint2f(x(), y());
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
    public void scaleAt(float kx, float ky, float baseX, float baseY) {
        float x = x();
        float y = y();
        x(kx * (x - baseX) + baseX);
        y(ky * (y - baseY) + baseY);
    }

    @Override
    public void rotateAt(float angle, float baseX, float baseY) {
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

    @Override
    public void draw(DrawTarget2f target)
            throws DrawException {
        target.drawPixel(this);
    }

    @Override
    public StaticPoint2f snapshot() {
        return new StaticPoint2f(x(), y());
    }

    @Override
    public abstract AbstractPoint2f clone();

}
