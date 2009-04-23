package net.bodz.geom.shape.base;

import java.io.Serializable;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.base.ReadOnlyException;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.geom.shape.Shape2f;
import net.bodz.math.mat.Vector2f;

public abstract class AbstractCircle2f extends AbstractShape2f implements Circle2f, Serializable {

    protected final class PtCenter extends AbstractPoint2f {

        static final long serialVersionUID = 2411011881244233842L;

        public PtCenter() {
        }

        @Override
        public AbstractPoint2f clone() {
            return new PtCenter();
        }

        public float x() {
            return centerX();
        }

        public float y() {
            return centerY();
        }

        @Override
        public void x(float x) {
            centerX(x);
        }

        @Override
        public void y(float y) {
            centerY(y);
        }

    }

    static final long serialVersionUID = -5944408962774434730L;

    public void centerX(float x) {
        throw new ReadOnlyException();
    }

    public void centerY(float y) {
        throw new ReadOnlyException();
    }

    public void radius(float radius) {
        throw new ReadOnlyException();
    }

    public Point2f.Static center() {
        return new Point2f.Static(new PtCenter());
    }

    public void center(Point2f point) {
        centerX(point.x());
        centerY(point.y());
    }

    @Override
    public Rectangle2f boundingBox() {
        float centerX = centerX();
        float centerY = centerY();
        float radius = radius();
        return new Rectangle2f.StaticLeft(centerX - radius, centerY - radius, centerX + radius,
                centerY + radius);
    }

    @Override
    public String toString() {
        return String.format("<Circle x='%f' y='%f' r='%f' />", centerX(), centerY(), radius());
    }

    // -o Pick

    @Override
    public PickInfo2f pickInfo(float x, float y) {
        return new PickInfo2f(this, distance(x, y));
    }

    @Override
    public float distance(float x, float y) {
        float dx = x - centerX();
        float dy = y - centerY();
        float dr = (float) Math.sqrt(dx * dx + dy * dy);
        return dr - radius();
    }

    // -o Shape

    @Override
    public Point2f spointRef(int id) {
        switch (id) {
        case SP_CENTER:
            return new PtCenter();
        }
        return super.spointRef(id);
    }

    @Override
    public Shape2f crop(Point2f baseHalfPlane, Vector2f normal) {
        // TODO
        return null;
    }

    public Polygon2f convertToPolygon() {
        // TODO
        return null;
    }

    @Override
    public abstract Circle2f snapshot();

    @Override
    public abstract AbstractCircle2f clone();

    public void draw(DrawTarget2f target) throws DrawException {
        target.drawEllipse(boundingBox());
    }

    // -o ShapeAmount

    public float length() {
        return (float) (Math.PI * radius() * 2);
    }

    public float area() {
        return (float) (Math.PI * radius() * radius());
    }

    // -o AutoExpand... ?
    /*
     * public boolean addWithScale(Shape2f shape) { assert shape != null; if
     * (shape.getClass().isAssignableFrom(Point2f.class)) return
     * addWithScale((Point2f)shape); if (contains(shape)) return false; return
     * false; }
     * 
     * public boolean addWithScale(float x, float y) { if (radius() <= 0) {
     * setCenterX(x); setCenterY(y); setRadius(Float.MIN_VALUE); return true; }
     * 
     * float cx = centerX(); float cy = centerY(); float radius = radius();
     * 
     * float dx = x - cx; float dy = y - cy; float D2 = dx * dx + dy * dy;
     * 
     * // already contains the point(x, y) if (D2 <= radius() * radius()) return
     * false;
     * 
     * float D = (float)Math.sqrt(D2); float R = (D + radius) / 2;
     * 
     * setCenterX(((R - radius) * cx + R * x) / D); setCenterY(((R - radius) *
     * cy + R * y) / D); setRadius(R); return true; }
     * 
     * public boolean addWithScale(Point2f point) { assert point != null; return
     * addWithScale(point.x(), point.y()); }
     */
}
