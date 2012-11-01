package net.bodz.bas.geom_f.base;

public class Rectangle2d
        extends AbstractRectangle2d {

    private static final long serialVersionUID = 1L;

    public static final int PT_0 = 0;
    public static final int PT_2 = 1;

    Point2d p0;
    Point2d p2;

    public Rectangle2d(Point2d p0, Point2d p2) {
        this.p0 = p0;
        this.p2 = p2;
    }

    public Rectangle2d(float x0, float y0, float x2, float y2) {
        this(new Point2d(x0, y0), new Point2d(x2, y2));
    }

    public static Rectangle2d sized(Point2d at, float width, float height) {
        Point2d p2 = at.clone();
        p2.add(width, height);
        return new Rectangle2d(at, p2);
    }

    public static Rectangle2d sized(float x0, float y0, float width, float height) {
        float x2 = x0 + width;
        float y2 = y0 + height;
        return new Rectangle2d(x0, y0, x2, y2);
    }

    public static Rectangle2d sized(float x0, float y0, javax.vecmath.Vector2f size) {
        float x2 = x0 + size.x;
        float y2 = y0 + size.y;
        return new Rectangle2d(x0, y0, x2, y2);
    }

    @Override
    public Rectangle2d clone() {
        return new Rectangle2d(p0, p2);
    }

    @Override
    public Rectangle2d snapshot() {
        return new Rectangle2d(p0.snapshot(), p2.snapshot());
    }

    @Override
    public Rectangle2d snapshotConst() {
        return this;
    }

    /**
     * <pre>
     * P0(x0,y0)   P1(x2,y0)
     *    +----------+
     *    |          |
     *    +----------+
     * P3(x0,y2)   P2(x2,y2)
     * </pre>
     */
    @Override
    public Point2d getPoint0() {
        return p0;
    }

    @Override
    public void setPoint0(Point2d point) {
        this.p0 = point;
    }

    @Override
    public Point2d getPoint1() {
        return new Point2d(p2.x, p0.y);
    }

    @Override
    public void setPoint1(Point2d point) {
        p2.x = point.x;
        p0.y = point.y;
    }

    @Override
    public Point2d getPoint2() {
        return p2;
    }

    @Override
    public void setPoint2(Point2d point) {
        this.p2 = point;
    }

    @Override
    public Point2d getPoint3() {
        return new Point2d(p0.x, p2.y);
    }

    @Override
    public void setPoint3(Point2d point) {
        p0.x = point.x;
        p2.y = point.y;
    }

    @Override
    public float getX0() {
        return p0.x;
    }

    @Override
    public void setX0(float x0) {
        p0.x = x0;
    }

    @Override
    public float getY0() {
        return p0.y;
    }

    @Override
    public void setY0(float y0) {
        p0.y = y0;
    }

    @Override
    public float getX1() {
        return p2.x;
    }

    @Override
    public void setX1(float x1) {
        p2.x = x1;
    }

    @Override
    public float getY1() {
        return p0.y;
    }

    @Override
    public void setY1(float y1) {
        p0.y = y1;
    }

    @Override
    public float getX2() {
        return p2.x;
    }

    @Override
    public void setX2(float x2) {
        p2.x = x2;
    }

    @Override
    public float getY2() {
        return p2.y;
    }

    @Override
    public void setY2(float y2) {
        p2.y = y2;
    }

    @Override
    public float getX3() {
        return p0.x;
    }

    @Override
    public void setX3(float x3) {
        p0.x = x3;
    }

    @Override
    public float getY3() {
        return p2.y;
    }

    @Override
    public void setY3(float y3) {
        p2.y = y3;
    }

    @Override
    public float getWidth() {
        return Math.abs(p2.x - p0.x);
    }

    @Override
    public void setWidth(float width) {
        p2.x = p0.x + width;
    }

    @Override
    public float getHeight() {
        return Math.abs(p2.y - p0.y);
    }

    @Override
    public void setHeight(float height) {
        p2.y = p0.y + height;
    }

    @Override
    public int getPointCount() {
        return 4;
    }

    @Override
    public Point2d getPoint(int index) {
        switch (index) {
        case PT_0:
            return p0;
        case PT_2:
            return p2;
        }
        return getBadPoint(index);
    }

}
