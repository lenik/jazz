package net.bodz.bas.geom_f.base;

import java.io.Serializable;

import net.bodz.bas.geom_f.api.CurveDirection;

public class Line2d
        extends AbstractLine2d
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int PT_0 = 0;
    public static final int PT_1 = 1;

    public Point2d p0;
    public Point2d p1;

    public Line2d(float x0, float y0, float x1, float y1) {
        this(new Point2d(x0, y0), new Point2d(x1, y1));
    }

    public Line2d(Point2d p0, Point2d p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    @Override
    public Line2d clone() {
        return new Line2d(p0, p1);
    }

    @Override
    public Line2d snapshot() {
        return new Line2d(p0.snapshot(), p1.snapshot());
    }

    @Override
    public Line2d snapshotConst() {
        return this;
    }

    //

    @Override
    public Point2d getPoint(int index) {
        switch (index) {
        case PT_0:
            return p0;
        case PT_1:
            return p1;
        }
        return null;
    }

    @Override
    public IPointRef2d getPointRef0() {
        return getPoint0();
    }

    @Override
    public IPointRef2d getPointRef1() {
        return getPoint1();
    }

    @Override
    public final float getX0() {
        return p0.x;
    }

    @Override
    public final void setX0(float x0) {
        p0.x = x0;
    }

    @Override
    public final float getY0() {
        return p0.y;
    }

    @Override
    public final void setY0(float y0) {
        p0.y = y0;
    }

    @Override
    public final float getX1() {
        return p1.x;
    }

    @Override
    public final void setX1(float x1) {
        p1.x = x1;
    }

    @Override
    public final float getY1() {
        return p1.y;
    }

    @Override
    public final void setY1(float y1) {
        p1.y = y1;
    }

    @Override
    public Point2d getPoint0() {
        return p0;
    }

    @Override
    public void setPoint0(Point2d p0) {
        this.p0 = p0;
    }

    @Override
    public Point2d getPoint1() {
        return p1;
    }

    @Override
    public void setPoint1(Point2d p1) {
        this.p1 = p1;
    }

    public CurveDirection getDirection(Point2d point) {
        float result = p0.crossProduct(p1, point);
        if (result > 0.0f)
            return CurveDirection.counterClockwise;
        else if (result < 0.0f)
            return CurveDirection.clockwise;
        else
            return CurveDirection.unknown;
    }

}