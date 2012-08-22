package net.bodz.bas.geom_f.base;

import net.bodz.bas.geom_f.api.AbstractShape2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.api.PickResult2d;
import net.bodz.bas.geom_f.api.PositiveHalfPlane;

public abstract class AbstractRectangle2d
        extends AbstractShape2d
        implements IRectangle2d {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractRectangle2d clone();

    @Override
    public Rectangle2d snapshot() {
        return new Rectangle2d(getPoint0().snapshot(), getPoint2().snapshot());
    }

    @Override
    public Rectangle2d snapshotConst() {
        return new Rectangle2d(getPoint0(), getPoint2());
    }

    @Override
    public boolean isValid() {
        if (getWidth() <= 0.0f)
            return false;
        if (getHeight() <= 0.0f)
            return false;
        return true;
    }

    @Override
    public Point2d degenerate() {
        Point2d point0 = getPoint0();
        Point2d point2 = getPoint2();
        if (point0.equals(point2, EPSILON))
            return point0.snapshot();
        else
            return null;
    }

    // -o IShapeAmount2d

    @Override
    public float getLength() {
        return Math.abs((getWidth() + getHeight()) * 2);
    }

    @Override
    public float getArea() {
        return Math.abs(getWidth() * getHeight());
    }

    // -o IExtendable2d

    @Override
    public boolean include(Point2d point) {
        if (contains(point))
            return false;

        float x = point.x;
        float y = point.y;
        boolean dirty = false;

        if (x < getX0()) {
            setX0(x);
            dirty = true;
        }
        if (x > getX1()) {
            setX2(x);
            dirty = true;
        }
        if (y < getY0()) {
            setY0(y);
            dirty = true;
        }
        if (y > getY1()) {
            setY1(y);
            dirty = true;
        }
        return dirty;
    }

    //

    @Override
    public float getWidth() {
        return getX2() - getX0();
    }

    @Override
    public float getHeight() {
        return getY2() - getY0();
    }

    @Override
    public void setWidth(float width) {
        setX2(getX0() + width);
    }

    @Override
    public void setHeight(float height) {
        setY2(getY0() + height);
    }

    @Override
    public boolean isPositive() {
        return getWidth() >= 0 && getHeight() >= 0;
    }

    @Override
    public void positize() {
        float x0 = getX0();
        float x2 = getX2();
        float y0 = getY0();
        float y2 = getY2();
        if (x0 > x2) {
            setX0(x2);
            setX2(x0);
        }
        if (y0 > y2) {
            setY0(y2);
            setY2(y0);
        }
    }

    @Override
    public void normalize() {
        float width = getWidth();
        float height = getHeight();
        if (width == 0.0f && height == 0.0f)
            throw new RuntimeException("Cannot normalize a rectangle with zero-width/height. ");
        float k;
        float w = Math.abs(width);
        float h = Math.abs(height);
        if (w > h)
            k = 1.0f / w;
        else
            k = 1.0f / h;
        width = k * w;
        height = k * h;
    }

    @Override
    public final int getOutcode(float x, float y) {
        return getOutcode(new Point2d(x, y));
    }

    @Override
    public int getOutcode(Point2d point) {
        IRectangle2d positive = clone();
        positive.positize();

        int out = 0;

        if (positive.getWidth() <= 0) {
            out |= OUT_LEFT | OUT_RIGHT;
        } else if (point.x < positive.getX0()) {
            out |= OUT_LEFT;
        } else if (point.x > positive.getX2()) {
            out |= OUT_RIGHT;
        }

        if (positive.getHeight() <= 0) {
            out |= OUT_TOP | OUT_BOTTOM;
        } else if (point.y < positive.getY0()) {
            out |= OUT_TOP;
        } else if (point.y > positive.getY2()) {
            out |= OUT_BOTTOM;
        }
        return out;
    }

    // -o IPickable2d

    @Override
    public PickResult2d _pick(Point2d point) {
        Rectangle2d pos = snapshot();
        pos.positize();
        Point2d c = pos.getCenterPoint();
        int Hsel = point.x < c.x ? 0 : 1;
        int Vsel = point.y < c.y ? 0 : 2;
        float dx, dy;

        switch (Hsel + Vsel) {
        case 0: // H0 V0
            dx = getX0() - point.x;
            dy = getY0() - point.y;
            if (dx > dy) {
                // H0, bottom-right
                // TODO this: cannot use new Ln0() because don't know the
                // direction
                // is left/right ?
                // For left H0 = Ln0, V0 = Ln1, H1 = Ln2, V1 = Ln3
                // For right H0 = Ln1, V0 = Ln0, H1 = Ln3, V1 = Ln2
                return new PickResult2d(this, dy);
            } else {
                // V0, top-left
                return new PickResult2d(this, dx);
            }

        case 1: // H1 V0
            dx = getX0() - point.x;
            dy = point.y - getY1();
            if (dx > -dy) {
                // H1, top-right
                return new PickResult2d(this, dy);
            } else {
                // V0, bottom-left
                return new PickResult2d(this, dx);
            }

        case 2: // H0 V1
            dx = point.x - getX1();
            dy = getY0() - point.y;
            if (dx > -dy) {
                // V1, top-right
                return new PickResult2d(this, dx);
            } else {
                // H0, bottom-left
                return new PickResult2d(this, dy);
            }

        case 3:
        default: // H1 V1
            dx = point.x - getX1();
            dy = point.y - getY1();
            if (dx > dy) {
                // V1, bottom-right
                return new PickResult2d(this, dx);
            } else {
                // H1, top-left
                return new PickResult2d(this, dy);
            }
        }
    }

    @Override
    public float distance(Point2d point) {
        Point2d c = getCenterPoint();
        float dx = Math.abs(point.x - c.x);
        float dy = Math.abs(point.y - c.y);
        dx -= getWidth() / 2;
        dy -= getHeight() / 2;
        return Math.max(dx, dy);
    }

    // -o IPolygonizable2d

    @Override
    public Polygon2d polygonize() {
        Polygon2d polygon = new Polygon2d(getPoint0(), getPoint1(), getPoint2(), getPoint3());
        polygon.close();
        return polygon;
    }

    // -o ICroppable2d

    @Override
    public IShape2d crop(PositiveHalfPlane php, boolean detached) {
        boolean intersected = false;
        for (Point2d p : getPoints())
            if (php.contains(p)) {
                intersected = true;
                break;
            }

        if (!intersected)
            return this;

        // return toPolygon().crop(hp);
        return null;
    }

    @Override
    public IShape2d crop(Rectangle2d rectangle, boolean detached) {
        Rectangle2d result = snapshot();
        result.positize();

        Rectangle2d o = rectangle.snapshot();
        Point2d p0 = getPoint0();
        Point2d p2 = getPoint2();
        if (o.p0.x > p2.x || o.p2.x < p0.x)
            return null;
        if (o.p0.y > p2.y || o.p2.y < p0.y)
            return null;

        p0.x = Math.max(p0.x, o.p0.x);
        p0.y = Math.max(p0.y, o.p0.y);
        p2.x = Math.min(p2.x, o.p2.x);
        p2.y = Math.min(p2.y, o.p2.y);

        return new Rectangle2d(p0, p2);
    }

    @Override
    public String toString() {
        return String.format("<Rectangle x0='%f' y0='%f' x2='%f' y2='%f' />", getX0(), getY0(), getX1(), getY1());
    }

}
