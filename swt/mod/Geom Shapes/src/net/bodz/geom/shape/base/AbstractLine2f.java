package net.bodz.geom.shape.base;

import java.io.Serializable;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.base.ReadOnlyException;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.math.mat.Vector2f;

public abstract class AbstractLine2f extends AbstractShape2f implements Line2f, Serializable {

    private static final long serialVersionUID = 4549850040626555777L;

    protected final class PtCenter extends AbstractPoint2f {

        static final long serialVersionUID = -7110570553058787874L;

        @Override
        public PtCenter clone() {
            return new PtCenter();
        }

        public float x() {
            return (x1() - x0()) / 2;
        }

        public float y() {
            return (y1() - y0()) / 2;
        }
    }

    protected final class Pt0 extends AbstractPoint2f {

        static final long serialVersionUID = 1282191210622475524L;

        public Pt0() {
        }

        @Override
        public AbstractPoint2f clone() {
            return new Pt0();
        }

        public float x() {
            return x0();
        }

        public float y() {
            return y0();
        }

        @Override
        public void x(float x) {
            x0(x);
        }

        @Override
        public void y(float y) {
            y0(y);
        }
    }

    protected final class Pt1 extends AbstractPoint2f {

        static final long serialVersionUID = 9019584322455454914L;

        public Pt1() {
        }

        @Override
        public AbstractPoint2f clone() {
            return new Pt1();
        }

        public float x() {
            return x1();
        }

        public float y() {
            return y1();
        }

        @Override
        public void x(float x) {
            x1(x);
        }

        @Override
        public void y(float y) {
            y1(y);
        }
    }

    public void x0(float x0) {
        throw new ReadOnlyException(this, "x0");
    }

    public void y0(float y0) {
        throw new ReadOnlyException(this, "y0");
    }

    public void x1(float x1) {
        throw new ReadOnlyException(this, "x1");
    }

    public void y1(float y1) {
        throw new ReadOnlyException(this, "y1");
    }

    public Point2f.Static p0() {
        return new Point2f.Static(x0(), y0());
    }

    public Point2f.Static p1() {
        return new Point2f.Static(x1(), y1());
    }

    public void p0(Point2f p0) {
        x0(p0.x());
        y0(p0.y());
    }

    public void p1(Point2f p1) {
        x1(p1.x());
        y1(p1.y());
    }

    public float width() {
        return x1() - x0();
    }

    public float height() {
        return y1() - y0();
    }

    public float x() {
        float k = gradient();
        return -y() / k;
    }

    public float y() {
        float k = gradient();
        return y0() - k * x0();
    }

    public float gradient() {
        return height() / width();
    }

    public float angle() {
        return toVector().angle();
    }

    public float index(float x, float y) {
        float w = width();
        float h = height();
        if (Math.abs(w) >= Math.abs(h))
            return indexX(x);
        else
            return indexY(y);
    }

    public float index(Point2f point) {
        return index(point.x(), point.y());
    }

    public float indexX(float x) {
        return (x - x0()) / (x1() - x0());
    }

    public float indexY(float y) {
        return (y - y0()) / (y1() - y0());
    }

    public Point2f.Static point(float index) {
        return new Point2f.Static(pointX(index), pointY(index));
    }

    public float pointX(float index) {
        return x0() + (x1() - x0()) * index;
    }

    public float pointY(float index) {
        return y0() + (y1() - y0()) * index;
    }

    public Point2f.Static _intersectsAt(Line2f line) {
        float k1 = gradient();
        float b1 = y();
        float k2 = line.gradient();
        float b2 = line.y();
        float D = k2 - k1;
        float Dx = b1 - b2;
        float Dy = k1 * b2 - k2 * b1;
        float x = Dx / D;
        float y = -Dy / D;
        return new Point2f.Static(x, y);
    }

    public boolean _intersects(Line2f line) {
        return _intersectsAt(line) != null;
    }

    public Point2f.Static intersectsAt(Line2f line) {
        Point2f.Static point = _intersectsAt(line);
        float t = index(point);
        if (t < 0.0f || t > 1.0f)
            return null;
        t = line.index(point);
        if (t < 0.0f || t > 1.0f)
            return null;
        return point;
    }

    public boolean intersects(Line2f line) {
        return intersectsAt(line) != null;
    }

    public Vector2f toVector() {
        return p0().vectorTo(p1());
    }

    public Vector2f getNormal() {
        return toVector().getNormal();
    }

    public abstract float x0();

    public abstract float y0();

    public abstract float x1();

    public abstract float y1();

    /*
     * Most point-implementation should have a point as itself. This
     * pointCount/pointRef is just for simplifize of create new point type.
     */
    public int pointCount() {
        return 2;
    }

    public Point2f pointRef(int index) {
        switch (index) {
        case 0:
            return new Pt0();
        case 1:
            return new Pt1();
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("<Line x0='%f' y0='%f' x1='%f' y1='%f' />", x0(), y0(), x1(), y1());
    }

    // -o Pick

    @Override
    public PickInfo2f pickInfo(float x, float y) {
        // the foot of perpendicular (H) may be not on the line.
        Vector2f v = toVector();
        Vector2f v0 = p0().vectorTo(x, y);
        if (v.dot(v0) <= 0) {
            // index(H) < 0
            Point2f p0 = p0();
            return new PickInfo2f(p0, p0.distance(x, y));
        } else {
            Vector2f v1 = p1().vectorFrom(x, y);
            if (v.dot(v1) <= 0) {
                // index(H) > 1
                Point2f p1 = p1();
                return new PickInfo2f(p1, p1.distance(x, y));
            }
        }

        float dist = Math.abs(distanceExtended(x, y));
        return new PickInfo2f(this, dist);
    }

    public float distanceExtended(Point2f point) {
        return distanceExtended(point.x(), point.y());
    }

    public float distanceExtended(float x, float y) {
        /*
         * | Ax + By + C | ----------------- SQRT(A^2 + B^2)
         * 
         * A = H B = -W C = -H x1 + W y1
         * 
         * so SQRT(A^2 + B^2) = L Ax + By + C = Hx - Wy - H x1 + W y1
         */

        float W = width();
        float H = height();

        float L = (float) Math.sqrt(W * W + H * H);
        if (L == 0)
            return p0().distance(x, y);

        float dist = (H * x - W * y - H * x0() - W * y0()) / L;

        /*
         * dist < 0 : left to the line dist = 0 : on the line dist > 0 : right
         * to the line
         */
        return -dist; // conver to Left-Hand system
    }

    @Override
    public float distance(float x, float y) {
        PickInfo2f pickInfo = pickInfo(x, y);
        return pickInfo.distance;
    }

    public final boolean containsHP(Point2f point) {
        Vector2f N = toVector();
        N.normal();
        return point.inside(p0(), N);
    }

    // -o Shape

    @Override
    public Point2f spointRef(int id) {
        switch (id) {
        case SP_CENTER:
            return new PtCenter();
        case SP_0:
            return new Pt0();
        case SP_1:
            return new Pt1();
        }
        return super.spointRef(id);
    }

    @Override
    public Line2f crop(Point2f base, Vector2f normal) {
        assert base != null;
        assert normal != null;

        Point2f P = p0();
        Point2f Q = p1();
        float num = normal.dot(base.vectorTo(P));
        float den = normal.dot(P.vectorTo(Q));

        if (den == 0) {
            // Line PQ is parallel to vector@base,
            // so just determine the entire line is visible or invisible
            if (num >= 0)
                return this; // entire visible
            else
                return null; // entire insivible
        }

        // P + (num / den) (Q - P)
        float t = -num / den;
        Vector2f PQ = P.vectorTo(Q);
        Point2f cut = P.toAdd(PQ.toScale(t));
        if (den > 0) {
            // PQ enters into
            if (t <= 0)
                return this;
            else if (t > 1)
                return null;
            else
                return new Line2f.Static(cut, Q);
        } else {
            // PQ leaves out
            if (t < 0)
                return null;
            else if (t >= 1)
                return this;
            else
                return new Line2f.Static(P, cut);
        }
    }

    @Override
    public Line2f crop(Rectangle2f rect) {
        return (Line2f) cropConvexPolygon(rect);
    }

    @Override
    public Line2f crop(float x0, float y0, float x1, float y1) {
        return crop(new Rectangle2f.StaticLeft(x0, y0, x1, y1));
    }

    public Line2f convertToLine2f() {
        return null;
    }

    public Polygon2f convertToPolygon() {
        return null;
    }

    public void draw(DrawTarget2f target) throws DrawException {
        target.drawLine(this);
    }

    @Override
    public Rectangle2f boundingBox() {
        return new Rectangle2f.StaticLeft(x0(), y0(), x1(), y1());
    }

    @Override
    public Line2f.Static snapshot() {
        return new Line2f.Static(p0(), p1());
    }

    @Override
    public abstract AbstractLine2f clone();

    // -o ShapeAmount

    public float length() {
        float w = width();
        float h = height();
        return (float) Math.sqrt(w * w + h * h);
    }

    public float area() {
        float area = width() * height();
        return Math.abs(area);
    }

}
