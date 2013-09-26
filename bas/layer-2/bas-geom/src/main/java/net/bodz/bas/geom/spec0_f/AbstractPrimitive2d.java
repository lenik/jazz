package net.bodz.bas.geom.spec0_f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.geom.spec0_f.util.PointSet2dIterator;
import net.bodz.bas.geom.spec1_f.Circle2d;
import net.bodz.bas.geom.spec1_f.IPointRef2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.geom.spec1_f.Triangle2d;
import net.bodz.bas.sugar.Tooling;

public abstract class AbstractPrimitive2d
        implements IPrimitive2d {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        String simpleName = getClass().getSimpleName();
        buf.append('(');
        buf.append(simpleName);

        int n = getPointCount();
        for (int i = 0; i < n; i++) {
            Point2d point = getPoint(i);
            buf.append(' ');
            buf.append(point);
        }

        buf.append(')');
        return buf.toString();
    }

    /** ⇱ Implementation Of {@link IPrimitive2d}. */
    ;

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public IPrimitive2d reduce() {
        if (!isValid())
            return null;

        Point2d degeneratedPoint = degenerate();
        if (degeneratedPoint != null)
            return degeneratedPoint;
        else
            return this;
    }

    /** ⇱ Implementation Of {@link ISnapShot}. */
    ;

    @Override
    public abstract AbstractPrimitive2d shot();

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPointSet2d}. */
    ;

    /**
     * Check point index.
     * 
     * @throws IndexOutOfBoundsException
     *             If point index is out of bound.
     */
    protected Point2d getBadPoint(int index) {
        throw new IndexOutOfBoundsException("Point index " + index + " is out of bound.");
    }

    protected void checkPointIndex(int index) {
        if (index < 0 || index >= getPointCount())
            getBadPoint(index);
    }

    @Override
    public float getPointX(int index) {
        Point2d point = getPoint(index);
        if (point == null)
            throw new IndexOutOfBoundsException("index=" + index);
        return point.x;
    }

    @Override
    public float getPointY(int index) {
        Point2d point = getPoint(index);
        if (point == null)
            throw new IndexOutOfBoundsException("index=" + index);
        return point.y;
    }

    @Override
    public Iterable<Point2d> getPoints() {
        int n = getPointCount();
        Point2d[] points = new Point2d[n];
        for (int index = 0; index < n; index++)
            points[index] = getPoint(index);
        return Arrays.asList(points);
    }

    @Override
    public Iterator<Point2d> getPointIterator() {
        // return getPoints().iterator();
        return new PointSet2dIterator(this);
    }

    @Override
    public Point2d[] toPointArray(boolean copy) {
        int n = getPointCount();
        Point2d[] points = new Point2d[n];
        if (copy)
            for (int i = 0; i < n; i++)
                points[i] = getPoint(i).shot();
        else
            for (int i = 0; i < n; i++)
                points[i] = getPoint(i);
        return points;
    }

    @Override
    public List<Point2d> toPointList(boolean copy) {
        int n = getPointCount();
        List<Point2d> points = new ArrayList<Point2d>(n);
        if (copy)
            for (int i = 0; i < n; i++)
                points.add(getPoint(i).shot());
        else
            for (int i = 0; i < n; i++)
                points.add(getPoint(i));
        return points;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPointRefSet2d}. */
    ;

    @Override
    public Iterable<IPointRef2d> getPointRefs() {
        int n = getPointCount();
        IPointRef2d[] points = new IPointRef2d[n];
        for (int index = 0; index < n; index++)
            points[index] = getPointRef(index);
        return Arrays.asList(points);
    }

    @Override
    public IPointRef2d getPointRef(int index) {
        return getPoint(index);
    }

    @Override
    public Iterator<IPointRef2d> getPointRefIterator() {
        return getPointRefs().iterator();
    }

    @Override
    public IPointRef2d[] toPointRefArray() {
        int n = getPointCount();
        IPointRef2d[] points = new IPointRef2d[n];
        for (int i = 0; i < n; i++)
            points[i] = getPointRef(i);
        return points;
    }

    @Override
    public List<IPointRef2d> toPointRefList() {
        int n = getPointCount();
        List<IPointRef2d> points = new ArrayList<IPointRef2d>(n);
        for (int i = 0; i < n; i++)
            points.add(getPointRef(i));
        return points;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPositionAttributes2d}. */
    ;

    @Override
    public Point2d getCenterPoint() {
        int n = getPointCount();
        if (n == 0)
            return null;

        Point2d p0 = getPoint(0);
        if (n == 1)
            return p0;

        float xsum = p0.x;
        float ysum = p0.y;
        for (int i = 1; i < n; i++) {
            Point2d p = getPoint(i);
            xsum += p.x;
            ysum += p.y;
        }

        Point2d center = new Point2d(xsum / n, ysum / n);
        return center;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPickable2d}. */
    ;

    @Override
    public final PickResult2d _pick(float x, float y) {
        Point2d point = new Point2d(x, y);
        return _pick(point);
    }

    @Override
    public final IPrimitive2d pick(float x, float y) {
        Point2d point = new Point2d(x, y);
        return pick(point);
    }

    @Override
    public IPrimitive2d pick(Point2d point) {
        PickResult2d result = _pick(point);
        if (result.getDistance() <= 0)
            return result.getShape();
        return null;
    }

    @Override
    public final float distance(float x, float y) {
        Point2d point = new Point2d(x, y);
        return distance(point);
    }

    @Override
    public final boolean contains(float x, float y) {
        return contains(new Point2d(x, y));
    }

    @Override
    public boolean contains(Point2d point) {
        return distance(point) <= 0.0f;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IBoundingBox2d}. */
    ;

    @Override
    public Rectangle2d getBoundingBox() {
        int n = getPointCount();
        if (n == 0)
            return new Rectangle2d(0, 0, 0, 0);

        Point2d p0 = getPoint(0);
        Rectangle2d bbox = new Rectangle2d(p0, p0);

        for (int i = 1; i < n; i++)
            bbox.include(getPoint(i));

        return bbox;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IBoundingBall2d}. */
    ;

    @Override
    public Circle2d getBoundingBall() {
        int n = getPointCount();
        if (n == 0)
            return new Circle2d(0, 0, 0);

        Circle2d bball = new Circle2d(getPoint(0), Float.MIN_VALUE);

        for (int index = 1; index < n; index++)
            bball.include(getPoint(index));

        return bball;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.ITransformable2d}. */
    ;

    @Override
    public void translate(float dx, float dy) {
        for (IPointRef2d point : getPointRefs()) {
            point.translate(dx, dy);
        }
    }

    @Override
    public void translate(javax.vecmath.Vector2f dv) {
        for (IPointRef2d point : getPointRefs())
            point.translate(dv);
    }

    @Override
    public void scale(float k) {
        for (IPointRef2d point : getPointRefs())
            point.scale(k);
    }

    @Override
    public void scale(float kx, float ky) {
        for (IPointRef2d point : getPointRefs())
            point.scale(kx, ky);
    }

    @Override
    public void scaleAt(float k, Point2d basePoint) {
        for (IPointRef2d point : getPointRefs())
            point.scaleAt(k, basePoint);
    }

    @Override
    public void scaleAt(float kx, float ky, Point2d basePoint) {
        for (IPointRef2d point : getPointRefs())
            point.scaleAt(kx, ky, basePoint);
    }

    @Override
    public void rotate(float angle) {
        for (IPointRef2d point : getPointRefs())
            point.rotate(angle);
    }

    @Override
    public void rotateAt(float angle, Point2d basePoint) {
        for (IPointRef2d point : getPointRefs())
            point.rotateAt(angle, basePoint);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IExtendable2d}. */
    ;

    public boolean include(IPrimitive2d shape) {
        boolean dirty = false;

        if (shape == null)
            throw new NullPointerException("shape");

        for (Point2d point : shape.getPoints())
            dirty |= include(point);

        return dirty;
    }

    public final boolean include(float x, float y) {
        return include(new Point2d(x, y));
    }

    public boolean include(Point2d point) {
        throw new UnsupportedOperationException("Not extendable.");
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPolygonizable2d}. */
    ;

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        Polygon2d polygon = polygonize();
        if (polygon == null)
            return null;
        if (minSegments > 1 || maxSegmentLength != null)
            polygon = polygon.polygonize(minSegments, maxSegmentLength);
        return polygon;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.ICroppable2d}. */
    ;

    @Override
    public IPrimitive2d crop(Triangle2d triangle, boolean detached) {
        CurveDirection direction = triangle.getDirection();
        return crop_convex(triangle, direction, detached);
    }

    @Override
    public IPrimitive2d crop(Rectangle2d rectangle, boolean detached) {
        Rectangle2d pos = rectangle.snapshot();
        pos.positize();
        return crop_convex(pos, CurveDirection.clockwise, detached);
    }

    @Override
    public IPrimitive2d crop(Polygon2d convexPolygon, boolean detached) {
        CurveDirection direction = convexPolygon.getDirection();
        return crop_convex(convexPolygon, direction, detached);
    }

    IPrimitive2d crop_convex(IPointSet2d polygon, CurveDirection direction, boolean detached) {
        IPrimitive2d result = this;

        int n = polygon.getPointCount();
        Point2d prev = polygon.getPoint(0);
        for (int index = 1; index < n; index++) {
            Point2d point = polygon.getPoint(index);

            PositiveHalfPlane2d php = new PositiveHalfPlane2d(prev, point);

            result = result.crop(php, detached);

            if (result.isValid())
                return null;
        }
        return result;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.sugar.IToChain}. */
    ;

    @Override
    public <T> T to(Class<T> clazz) {
        return new Tooling(this).getWrapper(clazz);
    }

}
