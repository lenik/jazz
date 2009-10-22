package net.bodz.geom.shape.base;

import java.io.Serializable;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.geom.shape.Shape2f;
import net.bodz.math.mat.Vector2f;

public abstract class AbstractRectangle2f extends AbstractShape2f implements Rectangle2f,
        Serializable {

    private static final long serialVersionUID = -7287160250280344587L;

    protected final class Pt0 extends AbstractPoint2f {

        static final long serialVersionUID = 3939555233256621425L;

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

        static final long serialVersionUID = 7514099476956931597L;

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

        static final long serialVersionUID = -6682169763583517485L;

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

    protected final class Pt3 extends AbstractPoint2f {

        static final long serialVersionUID = -2008571296592886269L;

        public Pt3() {
        }

        @Override
        public Pt3 clone() {
            return new Pt3();
        }

        @Override
        public void x(float x) {
            x3(x);
        }

        @Override
        public void y(float y) {
            y3(y);
        }

        public float x() {
            return x3();
        }

        public float y() {
            return y3();
        }

    }

    protected final class Ln0 extends AbstractLine2f {

        static final long serialVersionUID = -3392192599555791759L;

        @Override
        public float x0() {
            return AbstractRectangle2f.this.x0();
        }

        @Override
        public float y0() {
            return AbstractRectangle2f.this.y0();
        }

        @Override
        public float x1() {
            return AbstractRectangle2f.this.x1();
        }

        @Override
        public float y1() {
            return AbstractRectangle2f.this.y1();
        }

        @Override
        public void x0(float x0) {
            AbstractRectangle2f.this.x0(x0);
        }

        @Override
        public void y0(float y0) {
            AbstractRectangle2f.this.y0(y0);
        }

        @Override
        public void x1(float x1) {
            AbstractRectangle2f.this.x1(x1);
        }

        @Override
        public void y1(float y1) {
            AbstractRectangle2f.this.y1(y1);
        }

        @Override
        public AbstractLine2f clone() {
            return new Ln0();
        }

    }

    protected final class Ln1 extends AbstractLine2f {

        static final long serialVersionUID = -3392192599555791759L;

        @Override
        public float x0() {
            return AbstractRectangle2f.this.x1();
        }

        @Override
        public float y0() {
            return AbstractRectangle2f.this.y1();
        }

        @Override
        public float x1() {
            return AbstractRectangle2f.this.x2();
        }

        @Override
        public float y1() {
            return AbstractRectangle2f.this.y2();
        }

        @Override
        public void x0(float x0) {
            AbstractRectangle2f.this.x1(x0);
        }

        @Override
        public void y0(float y0) {
            AbstractRectangle2f.this.y1(y0);
        }

        @Override
        public void x1(float x1) {
            AbstractRectangle2f.this.x2(x1);
        }

        @Override
        public void y1(float y1) {
            AbstractRectangle2f.this.y2(y1);
        }

        @Override
        public AbstractLine2f clone() {
            return new Ln1();
        }

    }

    protected final class Ln2 extends AbstractLine2f {

        static final long serialVersionUID = -3392192599555791759L;

        @Override
        public float x0() {
            return AbstractRectangle2f.this.x2();
        }

        @Override
        public float y0() {
            return AbstractRectangle2f.this.y2();
        }

        @Override
        public float x1() {
            return AbstractRectangle2f.this.x3();
        }

        @Override
        public float y1() {
            return AbstractRectangle2f.this.y3();
        }

        @Override
        public void x0(float x0) {
            AbstractRectangle2f.this.x2(x0);
        }

        @Override
        public void y0(float y0) {
            AbstractRectangle2f.this.y2(y0);
        }

        @Override
        public void x1(float x1) {
            AbstractRectangle2f.this.x3(x1);
        }

        @Override
        public void y1(float y1) {
            AbstractRectangle2f.this.y3(y1);
        }

        @Override
        public AbstractLine2f clone() {
            return new Ln2();
        }

    }

    protected final class Ln3 extends AbstractLine2f {

        static final long serialVersionUID = -3392192599555791759L;

        @Override
        public float x0() {
            return AbstractRectangle2f.this.x3();
        }

        @Override
        public float y0() {
            return AbstractRectangle2f.this.y3();
        }

        @Override
        public float x1() {
            return AbstractRectangle2f.this.x0();
        }

        @Override
        public float y1() {
            return AbstractRectangle2f.this.y0();
        }

        @Override
        public void x0(float x0) {
            AbstractRectangle2f.this.x3(x0);
        }

        @Override
        public void y0(float y0) {
            AbstractRectangle2f.this.y3(y0);
        }

        @Override
        public void x1(float x1) {
            AbstractRectangle2f.this.x0(x1);
        }

        @Override
        public void y1(float y1) {
            AbstractRectangle2f.this.y0(y1);
        }

        @Override
        public AbstractLine2f clone() {
            return new Ln3();
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

    public Point2f.Static p3() {
        return new Point2f.Static(x3(), y3());
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

    public void p3(Point2f p3) {
        x3(p3.x());
        y3(p3.y());
    }

    public float width() {
        return x2() - x0();
    }

    public float height() {
        return y2() - y0();
    }

    public void width(float width) {
        x2(x0() + width);
    }

    public void height(float height) {
        y2(y0() + height);
    }

    public boolean positive() {
        return width() >= 0 && height() >= 0;
    }

    public void positize() {
        float x0 = x0();
        float x2 = x2();
        float y0 = y0();
        float y2 = y2();
        if (x0 > x2) {
            x0(x2);
            x2(x0);
        }
        if (y0 > y2) {
            y0(y2);
            y2(y0);
        }
    }

    public void normalize() {
        if (width() == 0.0f && height() == 0.0f)
            throw new RuntimeException("Cannot normalize a rectangle with zero-width/height. ");
        float k;
        float w = Math.abs(width());
        float h = Math.abs(height());
        if (w > h)
            k = 1.0f / w;
        else
            k = 1.0f / h;
        width(k * w);
        height(k * h);
    }

    public int outcode(float x, float y) {
        Rectangle2f positive = clone();
        positive.positize();
        int out = 0;
        if (positive.width() <= 0) {
            out |= OUT_LEFT | OUT_RIGHT;
        } else if (x < positive.x0()) {
            out |= OUT_LEFT;
        } else if (x > positive.x2()) {
            out |= OUT_RIGHT;
        }
        if (positive.height() <= 0) {
            out |= OUT_TOP | OUT_BOTTOM;
        } else if (y < positive.y0()) {
            out |= OUT_TOP;
        } else if (y > positive.y2()) {
            out |= OUT_BOTTOM;
        }
        return out;
    }

    public int outcode(Point2f point) {
        assert point != null;
        return outcode(point.x(), point.y());
    }

    @Override
    public String toString() {
        return String.format("<Rectangle x0='%f' y0='%f' x2='%f' y2='%f' />", x0(), y0(), x1(),
                y1());
    }

    // -o Pick

    @Override
    public PickInfo2f pickInfo(float x, float y) {
        Rectangle2f.StaticLeft pos = new Rectangle2f.LeftPositive(this);
        Point2f c = pos.spointRef(SP_CENTER);
        int Hsel = x < c.x() ? 0 : 1;
        int Vsel = y < c.y() ? 0 : 2;
        float dx, dy;

        switch (Hsel + Vsel) {
        case 0: // H0 V0
            dx = x0() - x;
            dy = y0() - y;
            if (dx > dy) {
                // H0, bottom-right
                // TODO this: cannot use new Ln0() because don't know the
                // direction
                // is left/right ?
                // For left H0 = Ln0, V0 = Ln1, H1 = Ln2, V1 = Ln3
                // For right H0 = Ln1, V0 = Ln0, H1 = Ln3, V1 = Ln2
                return new PickInfo2f(this, dy);
            } else {
                // V0, top-left
                return new PickInfo2f(this, dx);
            }

        case 1: // H1 V0
            dx = x0() - x;
            dy = y - y1();
            if (dx > -dy) {
                // H1, top-right
                return new PickInfo2f(this, dy);
            } else {
                // V0, bottom-left
                return new PickInfo2f(this, dx);
            }

        case 2: // H0 V1
            dx = x - x1();
            dy = y0() - y;
            if (dx > -dy) {
                // V1, top-right
                return new PickInfo2f(this, dx);
            } else {
                // H0, bottom-left
                return new PickInfo2f(this, dy);
            }

        case 3:
        default: // H1 V1
            dx = x - x1();
            dy = y - y1();
            if (dx > dy) {
                // V1, bottom-right
                return new PickInfo2f(this, dx);
            } else {
                // H1, top-left
                return new PickInfo2f(this, dy);
            }
        }
    }

    @Override
    public float distance(float x, float y) {
        Point2f c = pointRef(SP_CENTER);
        float dx = Math.abs(x - c.x());
        float dy = Math.abs(y - c.y());
        dx -= width() / 2;
        dy -= height() / 2;
        return Math.max(dx, dy);
    }

    // -o Shape

    public int spointCount() {
        return 5;
    }

    @Override
    public Point2f spointRef(int id) {
        switch (id) {
        case SP_0:
            return new Pt0();
        case SP_1:
            return new Pt1();
        case SP_2:
            return new Pt2();
        case SP_3:
            return new Pt3();
        }
        return super.spointRef(id);
    }

    @Override
    public Shape2f crop(Point2f baseHalfPlane, Vector2f normal) {
        // TODO
        return null;
    }

    public Polygon2f convertToPolygon2f() {
        // TODO
        return null;
    }

    @Override
    public abstract Rectangle2f snapshot();

    @Override
    public abstract AbstractRectangle2f clone();

    public void draw(DrawTarget2f target) throws DrawException {
        target.drawLine(p0(), p1());
        target.drawLine(p1(), p2());
        target.drawLine(p2(), p3());
        target.drawLine(p3(), p0());
    }

    // -o ShapeAmount

    public float length() {
        return Math.abs((width() + height()) * 2);
    }

    public float area() {
        return Math.abs(width() * height());
    }

    // -o Auto Expand..

    public boolean addWithScale(float x, float y) {
        if (contains(x, y))
            return false;

        boolean b = false;
        if (x < x0()) {
            x0(x);
            b = true;
        }
        if (x > x1()) {
            x2(x);
            b = true;
        }
        if (y < y0()) {
            y0(y);
            b = true;
        }
        if (y > y1()) {
            y1(y);
            b = true;
        }
        return b;
    }

    public boolean addWithScale(Point2f point) {
        assert point != null;
        return addWithScale(point.x(), point.y());
    }

    public boolean addWithScale(Shape2f shape) {
        Rectangle2f bounds = shape.boundingBox();
        boolean b = false;
        b |= addWithScale(bounds.p0());
        b |= addWithScale(bounds.p1());
        b |= addWithScale(bounds.p2());
        b |= addWithScale(bounds.p3());
        return b;
    }
}
