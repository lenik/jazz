package net.bodz.bas.geom_f.base;

import java.io.Serializable;

public class Link2d
        extends AbstractLine2d
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int PT_0 = 0;
    public static final int PT_1 = 1;

    IPointRef2d p0;
    IPointRef2d p1;

    public Link2d(IPointRef2d p0, IPointRef2d p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    @Override
    public Link2d clone() {
        return new Link2d(p0, p1);
    }

    @Override
    public Line2d snapshot() {
        return new Line2d(p0.snapshot(), p1.snapshot());
    }

    @Override
    public Point2d getPoint(int index) {
        IPointRef2d pointRef = getPointRef(index);
        return pointRef.snapshot();
    }

    @Override
    public IPointRef2d getPointRef(int index) {
        switch (index) {
        case PT_0:
            return p0;
        case PT_1:
            return p1;
        }
        return null;
    }

    @Override
    public Point2d getPoint0() {
        return p0.snapshot();
    }

    @Override
    public void setPoint0(Point2d p0) {
        this.p0.set(p0.x, p0.y);
    }

    @Override
    public Point2d getPoint1() {
        return p1.snapshot();
    }

    @Override
    public void setPoint1(Point2d p1) {
        this.p1.set(p1.x, p1.y);
    }

    @Override
    public IPointRef2d getPointRef0() {
        return p0;
    }

    @Override
    public IPointRef2d getPointRef1() {
        return p1;
    }

    @Override
    public float getX0() {
        return p0.getX();
    }

    @Override
    public void setX0(float x0) {
        p0.setX(x0);
    }

    @Override
    public float getY0() {
        return p0.getY();
    }

    @Override
    public void setY0(float y0) {
        p0.setY(y0);
    }

    @Override
    public float getX1() {
        return p1.getX();
    }

    @Override
    public void setX1(float x1) {
        p1.setX(x1);
    }

    @Override
    public float getY1() {
        return p1.getY();
    }

    @Override
    public void setY1(float y1) {
        p1.setY(y1);
    }

}
