package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.c.javax.vecmath.Vector2f;

/**
 * Center-PointOfBorder
 */
public class Circle2d_CP
        extends AbstractCircle2d {

    private static final long serialVersionUID = 1L;

    public static final int PT_CENTER = 0;
    public static final int PT_POINT1 = 1;

    Point2d center;
    Point2d point1;

    public Circle2d_CP(Point2d center, Point2d point1) {
        this.center = center;
        this.point1 = point1;
    }

    public Circle2d_CP(float centerX, float centerY, float x1, float y1) {
        this(new Point2d(centerX, centerY), new Point2d(x1, y1));
    }

    @Override
    public Circle2d_CP shot() {
        return new Circle2d_CP(center.shot(), point1.shot());
    }

    @Override
    public int getPointCount() {
        return 2;
    }

    @Override
    public Point2d getPoint(int index) {
        switch (index) {
        case PT_CENTER:
            return center;
        case PT_POINT1:
            return point1;
        }
        return getBadPoint(index);
    }

    @Override
    public Point2d getCenterPoint() {
        return center;
    }

    public void setCenterPoint(Point2d center) {
        assert center != null;
        this.center = center;
    }

    public float getCenterX() {
        return center.x;
    }

    public void setCenterX(float x) {
        center.x = x;
    }

    public float getCenterY() {
        return center.y;
    }

    public void setCenterY(float y) {
        center.y = y;
    }

    public Point2d getPoint1() {
        return point1;
    }

    public void setPoint1(Point2d point1) {
        assert point1 != null;
        this.point1 = point1;
    }

    @Override
    public float getRadius() {
        Vector2f v = center.vectorTo(point1);
        return v.length();
    }

    @Override
    public void setRadius(float radius) {
        Vector2f v = center.vectorTo(point1);
        float oldRadius = v.length();
        float scale = radius / oldRadius;
        v.scale(scale);
        point1 = center.shot().scale_(scale);
    }

}
