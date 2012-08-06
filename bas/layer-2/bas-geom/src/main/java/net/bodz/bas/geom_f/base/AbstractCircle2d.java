package net.bodz.bas.geom_f.base;

import net.bodz.bas.geom_f.api.AbstractShape2d;
import net.bodz.bas.geom_f.api.PickResult2d;

public abstract class AbstractCircle2d
        extends AbstractShape2d
        implements ICircle2d {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractCircle2d clone();

    @Override
    public Circle2d snapshot() {
        return new Circle2d(getCenterPoint().snapshot(), getRadius());
    }

    @Override
    public Circle2d snapshotConst() {
        return new Circle2d(getCenterPoint(), getRadius());
    }

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

    // -o IShapeAmount2d

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

    // -o IBoundingBox2d

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

    // -o IPickable2d

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

    @Override
    public String toString() {
        Point2d center = getCenterPoint();
        return String.format("<Circle x='%f' y='%f' r='%f' />", center.x, center.y, getRadius());
    }

}
