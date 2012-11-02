package net.bodz.bas.geom_f.base;

import net.bodz.bas.c.javax.vecmath.Vector2f;

/**
 * 3-Point Circle
 */
public class Circle2d_PPP
        extends AbstractCircle2d {

    private static final long serialVersionUID = 1L;

    public static final int PT_0 = 0;
    public static final int PT_1 = 1;
    public static final int PT_2 = 2;

    Point2d p0;
    Point2d p1;
    Point2d p2;

    public Circle2d_PPP(Point2d p0, Point2d p1, Point2d p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Circle2d_PPP(Point2d pt[]) {
        this.p0 = pt[0];
        this.p1 = pt[1];
        this.p2 = pt[2];
    }

    public Circle2d_PPP(float aX, float aY, float bX, float bY, float cX, float cY) {
        this(new Point2d(aX, aY), new Point2d(bX, bY), new Point2d(cX, cY));
    }

    public Circle2d_PPP(float x[], float y[]) {
        p0 = new Point2d(x[0], y[0]);
        p1 = new Point2d(x[1], y[1]);
        p2 = new Point2d(x[2], y[2]);
    }

    @Override
    public Circle2d_PPP shot() {
        return new Circle2d_PPP(p0.shot(), p1.shot(), p2.shot());
    }

    public int getPointCount() {
        return 3;
    }

    @Override
    public Point2d getPoint(int index) {
        switch (index) {
        case PT_0:
            return p0;
        case PT_1:
            return p1;
        case PT_2:
            return p2;
        }
        return getBadPoint(index);
    }

    /**
     * <pre>
     *      | x1 + x2 |     | y2 - y1 |   | xc |
     *  1/2 |         | + s |         | = |    |
     *      | y1 + y2 |     | x1 - x2 |   | yc |
     * </pre>
     */
    @Override
    public Point2d getCenterPoint() {
        float x0 = p0.x, y0 = p0.y;
        float x1 = p1.x, y1 = p1.y;
        float x2 = p2.x, y2 = p2.y;

        float s = 0.5f * ((x1 - x2) * (x0 - x2) - (y1 - y2) * (y2 - y0));
        float sUnder = (x0 - x1) * (y2 - y0) - (y1 - y0) * (x0 - x2);

        if (sUnder == 0)
            throw new IllegalStateException("Not a circle.");

        s /= sUnder;

        float xc = 0.5f * (x0 + x1) + s * (y1 - y0); // center x coordinate
        float yc = 0.5f * (y0 + y1) + s * (x0 - x1); // center y coordinate
        return new Point2d(xc, yc);
    }

    public void setCenterPoint(Point2d center) {
        Point2d oldCenter = getCenterPoint();
        Vector2f movement = oldCenter.vectorTo(center);
        translate(movement);
    }

    @Override
    public float getRadius() {
        Point2d center = getCenterPoint();
        Vector2f v0 = center.vectorTo(p0);
        return v0.length();
    }

    @Override
    public void setRadius(float radius) {
        float oldRadius = getRadius();
        float scale = radius / oldRadius;
        Point2d center = getCenterPoint();
        translate(-center.x, -center.y);
        scale(scale);
        translate(center.x, center.y);
    }

}
