package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.geom.spec0_f.AbstractPrimitive2d;
import net.bodz.bas.geom.spec0_f.CurveDirection;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.PickResult2d;
import net.bodz.bas.geom.spec0_f.PositiveHalfPlane2d;

public abstract class AbstractTriangle2d
        extends AbstractPrimitive2d
        implements ITriangle2d {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractTriangle2d shot();

    @Override
    public Triangle2d snapshot() {
        return new Triangle2d(//
                getPoint0().snapshot(), //
                getPoint1().snapshot(), //
                getPoint2().snapshot());
    }

    @Override
    public Triangle2d snap() {
        return new Triangle2d(//
                getPoint0(), //
                getPoint1(), //
                getPoint2());
    }

    @Override
    public Point2d degenerate() {
        Point2d point0 = getPoint0();
        Point2d point1 = getPoint1();
        Point2d point2 = getPoint2();
        if (!point0.equals(point1, EPSILON))
            return null;
        if (!point0.equals(point2, EPSILON))
            return null;
        return point0.snapshot();
    }

    // -o IShapeAmount2d

    @Override
    public float getLength() {
        float a = getPoint0().vectorTo(getPoint1()).length();
        float b = getPoint1().vectorTo(getPoint2()).length();
        float c = getPoint2().vectorTo(getPoint0()).length();
        return a + b + c;
    }

    @Override
    public float getArea() {
        float a = getPoint0().vectorTo(getPoint1()).length();
        float b = getPoint1().vectorTo(getPoint2()).length();
        float c = getPoint2().vectorTo(getPoint0()).length();
        float p = (a + b + c) / 2;
        float s = p * (p - a) * (p - b) * (p - c);
        return (float) Math.sqrt(s);
    }

    // -o IPointSet2d

    // -o IPickable2d

    @Override
    public PickResult2d _pick(Point2d point) {
        return new PickResult2d(this, distance(point));
    }

    @Override
    public float distance(Point2d point) {
        float a = getPoint0().lineTo(getPoint1()).distance(point);
        float b = getPoint1().lineTo(getPoint2()).distance(point);
        float c = getPoint2().lineTo(getPoint0()).distance(point);
        return Math.max(Math.max(a, b), c);
    }

    // TODO optim
    @Override
    public boolean contains(Point2d point) {
        return super.contains(point);
    }

    // -o IPolygonizable2d

    @Override
    public Polygon2d polygonize() {
        Polygon2d polygon = new Polygon2d(//
                getPoint0(), //
                getPoint1(), //
                getPoint2());
        polygon.close();
        return polygon;
    }

    // -o ICroppable2d

    @Override
    public IPrimitive2d crop(PositiveHalfPlane2d php, boolean detached) {
        // In most cases return another triangle.
        throw new NotImplementedException();
    }

    // -o ITriangle2d

    @Override
    public Vector2f getVector0() {
        Point2d point0 = getPoint0();
        Point2d point1 = getPoint1();
        return point0.vectorTo(point1);
    }

    @Override
    public Vector2f getVector1() {
        Point2d point1 = getPoint1();
        Point2d point2 = getPoint2();
        return point1.vectorTo(point2);
    }

    @Override
    public Vector2f getVector2() {
        Point2d point0 = getPoint0();
        Point2d point2 = getPoint2();
        return point2.vectorTo(point0);
    }

    @Override
    public float getLength0() {
        return getVector0().length();
    }

    @Override
    public float getLength1() {
        return getVector1().length();
    }

    @Override
    public float getLength2() {
        return getVector2().length();
    }

    @Override
    public float getAngle0() {
        Point2d p0 = getPoint0();
        Point2d p1 = getPoint1();
        Point2d p2 = getPoint2();
        return p0.angle(p2, p1);
    }

    @Override
    public float getAngle1() {
        Point2d p0 = getPoint0();
        Point2d p1 = getPoint1();
        Point2d p2 = getPoint2();
        return p1.angle(p0, p2);
    }

    @Override
    public float getAngle2() {
        Point2d p0 = getPoint0();
        Point2d p1 = getPoint1();
        Point2d p2 = getPoint2();
        return p2.angle(p1, p0);
    }

    @Override
    public CurveDirection getDirection() {
        Vector2f v0 = getPoint0().vectorTo(getPoint1());
        Vector2f v1 = getPoint1().vectorTo(getPoint2());
        // Vector2f v2 = p2().vectorTo(p0());
        float r0 = v0.angle(v1);
        // float r1 = v1.angle(v2);
        // float r2 = v2.angle(v0);
        // r0 += r1 + r2;
        if (r0 >= 0)
            return CurveDirection.counterClockwise;
        else
            return CurveDirection.clockwise;
    }

    @Override
    public Circle2d getIncircle() {
        // r = S/p = 4R sinA/2 sinB/2 sinC/2
        return null;
    }

    @Override
    public Circle2d getCircumcircle() {
        // R = abc/4S = a/2sinA
        return null;
    }

}
