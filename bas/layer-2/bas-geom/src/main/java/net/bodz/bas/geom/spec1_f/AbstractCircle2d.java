package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.geom.spec0_f.AbstractPrimitive2d;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.PickResult2d;
import net.bodz.bas.geom.spec0_f.PositiveHalfPlane2d;

public abstract class AbstractCircle2d
        extends AbstractPrimitive2d
        implements ICircle2d {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        Point2d center = getCenterPoint();
        return String.format("<Circle x='%f' y='%f' r='%f' />", center.x, center.y, getRadius());
    }

    /** ⇱ Implementation Of {@link IPrimitive2d}. */
    ;

    @Override
    public boolean isValid() {
        if (getRadius() < 0.0f)
            return false;
        else
            return true;
    }

    @Override
    public Point2d degenerate() {
        float radius = getRadius();
        if (radius < EPSILON)
            return getCenterPoint();
        else
            return null;
    }

    /** ⇱ Implementation Of {@link ISnapShot}. */
    ;

    @Override
    public abstract AbstractCircle2d shot();

    @Override
    public Circle2d snapshot() {
        return new Circle2d(getCenterPoint().snapshot(), getRadius());
    }

    @Override
    public Circle2d snap() {
        return new Circle2d(getCenterPoint(), getRadius());
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IShapeAmount2d}. */
    ;

    @Override
    public float getLength() {
        return (float) (Math.PI * getRadius() * 2);
    }

    @Override
    public float getArea() {
        return (float) (Math.PI * getRadius() * getRadius());
    }

    //

    @Override
    public float getDiameter() {
        return getRadius() * 2;
    }

    @Override
    public void setDiameter(float diameter) {
        float radius = diameter / 2;
        setRadius(radius);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IBoundingBox2d}. */
    ;

    @Override
    public Rectangle2d getBoundingBox() {
        Point2d center = getCenterPoint();
        float centerX = center.x;
        float centerY = center.y;
        float radius = getRadius();
        return new Rectangle2d(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    @Override
    public Circle2d getBoundingBall() {
        return snapshot();
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPickable2d}. */
    ;

    @Override
    public PickResult2d _pick(Point2d point) {
        return new PickResult2d(this, distance(point));
    }

    @Override
    public float distance(Point2d point) {
        Point2d center = getCenterPoint();
        float dr = center.distance(point);
        return dr - getRadius();
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPolygonizable2d}. */
    ;

    @Override
    public Polygon2d polygonize() {
        return polygonize(4, null);
    }

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        throw new NotImplementedException();
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.ICroppable2d}. */
    ;

    @Override
    public IPrimitive2d crop(PositiveHalfPlane2d php, boolean detached) {
        return null;
    }

}
