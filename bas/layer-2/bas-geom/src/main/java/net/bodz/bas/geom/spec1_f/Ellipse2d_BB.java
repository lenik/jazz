package net.bodz.bas.geom.spec1_f;

/**
 * In-BoundingBox
 */
public class Ellipse2d_BB
        extends AbstractEllipse2d {

    private static final long serialVersionUID = 1L;

    public static final int PT_0 = 0;
    public static final int PT_1 = 1;

    Point2d p0;
    Point2d p1;

    public Ellipse2d_BB(Point2d p0, Point2d p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    @Override
    public Ellipse2d_BB shot() {
        return new Ellipse2d_BB(p0.shot(), p1.shot());
    }

    @Override
    public Point2d getCenterPoint() {
        float cx = (p0.x + p1.x) / 2;
        float cy = (p0.y + p1.y) / 2;
        return new Point2d(cx, cy);
    }

    @Override
    public int getPointCount() {
        return 2;
    }

    @Override
    public Point2d getPoint(int index) {
        switch (index) {
        case 0:
            return p0;
        case 1:
            return p1;
        }
        return getBadPoint(index);
    }

    @Override
    public Point2d getCenter1() {
        return null;
    }

    @Override
    public void setCenter1(Point2d center1) {
    }

    @Override
    public Point2d getCenter2() {
        return null;
    }

    @Override
    public void setCenter2(Point2d center2) {
    }

    @Override
    public float getTransverseDiameter() {
        return (p1.x - p0.x);
    }

    @Override
    public float getConjugateDiameter() {
        return (p1.y - p0.y);
    }

}