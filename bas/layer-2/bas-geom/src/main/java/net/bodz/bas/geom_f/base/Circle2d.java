package net.bodz.bas.geom_f.base;

import java.io.Serializable;

import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.api.PositiveHalfPlane;

/**
 * Center-Radius
 */
public class Circle2d
        extends AbstractCircle2d
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int PT_CENTER = 0;

    Point2d center;
    float radius;

    public Circle2d(float centerX, float centerY, float radius) {
        this.center = new Point2d(centerX, centerY);
        this.radius = radius;
    }

    public Circle2d(Point2d center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Circle2d shot() {
        return new Circle2d(center.shot(), radius);
    }

    @Override
    public Circle2d snapshot() {
        return new Circle2d(center.snapshot(), radius);
    }

    @Override
    public Circle2d snap() {
        return this;
    }

    // -o IPointSet2d

    public int getPointCount() {
        return 1;
    }

    @Override
    public Point2d getPoint(int index) {
        switch (index) {
        case PT_CENTER:
            return center;
        }
        return getBadPoint(index);
    }

    @Override
    public Point2d getCenterPoint() {
        return center;
    }

    public void setCenterPoint(Point2d center) {
        if (center == null)
            throw new NullPointerException("center");
        this.center = center;
    }

    public float getCenterX() {
        return center.x;
    }

    public void setCenterX(float cx) {
        center.x = cx;
    }

    public float getCenterY() {
        return center.y;
    }

    public void setCenterY(float cy) {
        center.y = cy;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

    // -o IExtendable2d

    @Override
    public boolean include(Point2d point) {
        if (radius <= 0) {
            setCenterX(point.x);
            setCenterY(point.y);
            setRadius(0.0f);
            return true;
        }

        float cx = center.x;
        float cy = center.y;
        float dx = point.x - cx;
        float dy = point.y - cy;
        float dist2 = dx * dx + dy * dy;

        // already contains the point(x, y)
        if (dist2 <= radius * radius)
            return false;

        float dist = (float) Math.sqrt(dist2);
        float newRadius = (dist + radius) / 2;

        center.x = ((newRadius - radius) * cx + newRadius * point.x) / dist;
        center.y = ((newRadius - radius) * cy + newRadius * point.y) / dist;
        radius = newRadius;

        return true;
    }

    // -o IPolygonizable2d

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        return null;
    }

    // -o ICroppable2d

    @Override
    public IShape2d crop(PositiveHalfPlane php, boolean detached) {
        return null;
    }

}