package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.gui.draw_f.dc.DrawException;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawContext2d;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawable2d;

public class Triangle2d
        extends AbstractTriangle2d {

    private static final long serialVersionUID = 1L;

    public static final int PT_0 = 0;
    public static final int PT_1 = 1;
    public static final int PT_2 = 2;

    Point2d p0;
    Point2d p1;
    Point2d p2;

    public Triangle2d(float x0, float y0, float x1, float y1, float x2, float y2) {
        this.p0 = new Point2d(x0, y0);
        this.p1 = new Point2d(x1, y1);
        this.p2 = new Point2d(x2, y2);
    }

    public Triangle2d(Point2d p0, Point2d p1, Point2d p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPrimitive2d}. */
    /* _____________________________ */static section.iface __BASE__;

    @Override
    public Triangle2d snap() {
        return new Triangle2d(p0, p1, p2);
    }

    @Override
    public Triangle2d shot() {
        return new Triangle2d(p0, p1, p2);
    }

    @Override
    public Triangle2d snapshot() {
        return new Triangle2d(p0.snapshot(), p1.snapshot(), p2.snapshot());
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPointSet2d}. */
    /* _____________________________ */static section.iface __POINTS__;

    @Override
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

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec1_f.ITriangle2d}. */
    /* _____________________________ */static section.iface __TRIANGLE__;

    @Override
    public Point2d getPoint0() {
        return p0;
    }

    @Override
    public void setPoint0(Point2d p) {
        this.p0 = p;
    }

    @Override
    public Point2d getPoint1() {
        return p1;
    }

    @Override
    public void setPoint1(Point2d p) {
        this.p1 = p;
    }

    @Override
    public Point2d getPoint2() {
        return p2;
    }

    @Override
    public void setPoint2(Point2d p) {
        this.p2 = p;
    }

    @Override
    public float getX0() {
        return p0.x;
    }

    @Override
    public void setX0(float x) {
        p0.x = x;
    }

    @Override
    public float getY0() {
        return p0.y;
    }

    @Override
    public void setY0(float y) {
        p0.y = y;
    }

    @Override
    public float getX1() {
        return p1.x;
    }

    @Override
    public void setX1(float x) {
        p1.x = x;
    }

    @Override
    public float getY1() {
        return p1.y;
    }

    @Override
    public void setY1(float y) {
        p1.y = y;
    }

    @Override
    public float getX2() {
        return p2.x;
    }

    @Override
    public void setX2(float x) {
        p2.x = x;
    }

    @Override
    public float getY2() {
        return p2.y;
    }

    @Override
    public void setY2(float y) {
        p2.y = y;
    }

    /** ⇱ Implementation Of {@link IBaseDrawable2d}. */
    /* _____________________________ */static section.iface __DRAW__;

    @Override
    public void draw(IBaseDrawContext2d ctx)
            throws DrawException {
        ctx.drawTriangle(this);
    }

}