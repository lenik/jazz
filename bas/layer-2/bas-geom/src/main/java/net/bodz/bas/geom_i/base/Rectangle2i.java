package net.bodz.bas.geom_i.base;

public class Rectangle2i {

    public Point2i point0;
    public Point2i point2;

    public Rectangle2i() {
        point0 = new Point2i();
        point2 = new Point2i();
    }

    public Rectangle2i(Point2i point0, Point2i point2) {
        if (point0 == null)
            throw new NullPointerException("point0");
        if (point2 == null)
            throw new NullPointerException("point2");
        this.point0 = point0;
        this.point2 = point2;
    }

    public Point2i getPoint0() {
        return point0;
    }

    public void setPoint0(Point2i point0) {
        this.point0 = point0;
    }

    public Point2i getPoint2() {
        return point2;
    }

    public void setPoint2(Point2i point2) {
        this.point2 = point2;
    }

}
