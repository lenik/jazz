package net.bodz.geom.shape.base;

import java.io.Serializable;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.geom.shape.Shape2f;
import net.bodz.math.mat.Vector2f;

public abstract class AbstractTriangle2f extends AbstractShape2f implements
        Triangle2f, Serializable {

    private static final long serialVersionUID = -1486259130919044343L;
    public static final int   CCW              = 1;
    public static final int   CW               = 2;

    protected final class Pt0 extends AbstractPoint2f {

        static final long serialVersionUID = -8178426466344450313L;

        public Pt0() {
        }

        @Override
        public Pt0 clone() {
            return new Pt0();
        }

        @Override
        public void x(float x) {
            x0(x);
        }

        @Override
        public void y(float y) {
            y0(y);
        }

        public float x() {
            return x0();
        }

        public float y() {
            return y0();
        }

    }

    protected final class Pt1 extends AbstractPoint2f {

        static final long serialVersionUID = 768186549440596071L;

        public Pt1() {
        }

        @Override
        public Pt1 clone() {
            return new Pt1();
        }

        @Override
        public void x(float x) {
            x1(x);
        }

        @Override
        public void y(float y) {
            y1(y);
        }

        public float x() {
            return x1();
        }

        public float y() {
            return y1();
        }

    }

    protected final class Pt2 extends AbstractPoint2f {

        static final long serialVersionUID = 7471895570005201370L;

        public Pt2() {
        }

        @Override
        public Pt2 clone() {
            return new Pt2();
        }

        @Override
        public void x(float x) {
            x2(x);
        }

        @Override
        public void y(float y) {
            y2(y);
        }

        public float x() {
            return x2();
        }

        public float y() {
            return y2();
        }

    }

    protected final class PtCenter extends AbstractPoint2f {

        static final long serialVersionUID = 7471895570005201370L;

        public PtCenter() {
        }

        @Override
        public PtCenter clone() {
            return new PtCenter();
        }

        public float x() {
            return centerX();
        }

        public float y() {
            return centerY();
        }

    }

    public Point2f.Static p0() {
        return new Point2f.Static(x0(), y0());
    }

    public Point2f.Static p1() {
        return new Point2f.Static(x1(), y1());
    }

    public Point2f.Static p2() {
        return new Point2f.Static(x2(), y2());
    }

    public void p0(Point2f p0) {
        x0(p0.x());
        y0(p0.y());
    }

    public void p1(Point2f p1) {
        x1(p1.x());
        y1(p1.y());
    }

    public void p2(Point2f p2) {
        x2(p2.x());
        y2(p2.y());
    }

    public float centerX() {
        return (x0() + x1() + x2()) / 3;
    }

    public float centerY() {
        return (y0() + y1() + y2()) / 3;
    }

    public Point2f center() {
        return new PtCenter();
    }

    public Circle2f cIn() {
        // r = S/p = 4R sinA/2 sinB/2 sinC/2
        return null;
    }

    public Circle2f cEx() {
        // R = abc/4S = a/2sinA
        return null;
    }

    public int direction() {
        Vector2f v0 = p0().vectorTo(p1());
        Vector2f v1 = p1().vectorTo(p2());
        // Vector2f v2 = p2().vectorTo(p0());
        float r0 = v0.angle(v1);
        // float r1 = v1.angle(v2);
        // float r2 = v2.angle(v0);
        // r0 += r1 + r2;
        if (r0 >= 0)
            return CCW;
        return CW;
    }

    // -o Pick

    @Override
    public PickInfo2f pickInfo(float x, float y) {
        return new PickInfo2f(this, distance(x, y));
    }

    @Override
    public float distance(float x, float y) {
        float a = p0().lineTo(p1()).distance(x, y);
        float b = p1().lineTo(p2()).distance(x, y);
        float c = p2().lineTo(p0()).distance(x, y);
        return Math.max(Math.max(a, b), c);
    }

    @Override
    public boolean contains(float x, float y) {
        return contains(new Point2f.Static(x, y));
    }

    @Override
    public boolean contains(Point2f point) {
        Vector2f v0 = point.vectorTo(p0());
        Vector2f v1 = point.vectorTo(p1());
        Vector2f v2 = point.vectorTo(p2());
        float r0 = v0.angle(v1);
        float r1 = v1.angle(v2);
        float r2 = v2.angle(v0);
        r0 += r1 + r2;
        if (r0 == 0.0f)
            return false;
        return true;
    }

    // -o Shape

    public abstract int pointCount();

    public abstract Point2f pointRef(int index);

    @Override
    public Point2f spointRef(int id) {
        switch (id) {
        case SP_CENTER:
            return new PtCenter();
        case SP_0:
            return new Pt0();
        case SP_1:
            return new Pt1();
        case SP_2:
            return new Pt2();
        }
        return super.spointRef(id);
    }

    @Override
    public Shape2f crop(Point2f baseHalfPlane, Vector2f normal) {
        // TODO
        return null;
    }

    @Override
    public Triangle2f.Static snapshot() {
        return new Triangle2f.Static(x0(), y0(), x1(), y1(), x2(), y2());
    }

    @Override
    public abstract AbstractTriangle2f clone();

    // @see REFM.kb
    public Polygon2f convertToPolygon2f() {
        Point2f[] points = new Point2f[] { new Pt0(), new Pt1(), new Pt2(), };
        return null; // new Polygon2f.FastInitArray(points);
    }

    public void draw(DrawTarget2f target) throws DrawException {
        target.drawLine(p0(), p1());
        target.drawLine(p1(), p2());
        target.drawLine(p2(), p0());
    }

    // -o ShapeAmount

    public float length() {
        float a = p0().vectorTo(p1()).length();
        float b = p1().vectorTo(p2()).length();
        float c = p2().vectorTo(p0()).length();
        return a + b + c;
    }

    public float area() {
        float a = p0().vectorTo(p1()).length();
        float b = p1().vectorTo(p2()).length();
        float c = p2().vectorTo(p0()).length();
        float p = (a + b + c) / 2;
        float s = p * (p - a) * (p - b) * (p - c);
        return (float) Math.sqrt(s);
    }

}
