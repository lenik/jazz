package net.bodz.bas.geom.spec1_f;

import java.io.Serializable;

import net.bodz.bas.gui.draw_f.dc.DrawException;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawContext2d;

public class Link2d
        extends AbstractLine2d
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int PT_0 = 0;
    public static final int PT_1 = 1;

    IPointRef2d point0;
    IPointRef2d point1;

    public Link2d(IPointRef2d p0, IPointRef2d p1) {
        this.point0 = p0;
        this.point1 = p1;
    }

    @Override
    public Link2d shot() {
        return new Link2d(point0, point1);
    }

    @Override
    public Line2d snapshot() {
        return new Line2d(point0.snapshot(), point1.snapshot());
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
            return point0;
        case PT_1:
            return point1;
        }
        return getBadPoint(index);
    }

    @Override
    public Point2d getPoint0() {
        return point0.snapshot();
    }

    @Override
    public void setPoint0(Point2d p0) {
        this.point0.set(p0.x, p0.y);
    }

    @Override
    public Point2d getPoint1() {
        return point1.snapshot();
    }

    @Override
    public void setPoint1(Point2d p1) {
        this.point1.set(p1.x, p1.y);
    }

    @Override
    public IPointRef2d getPointRef0() {
        return point0;
    }

    @Override
    public IPointRef2d getPointRef1() {
        return point1;
    }

    @Override
    public float getX0() {
        return point0.getX();
    }

    @Override
    public void setX0(float x0) {
        point0.setX(x0);
    }

    @Override
    public float getY0() {
        return point0.getY();
    }

    @Override
    public void setY0(float y0) {
        point0.setY(y0);
    }

    @Override
    public float getX1() {
        return point1.getX();
    }

    @Override
    public void setX1(float x1) {
        point1.setX(x1);
    }

    @Override
    public float getY1() {
        return point1.getY();
    }

    @Override
    public void setY1(float y1) {
        point1.setY(y1);
    }

    /** ⇱ Implementation Of {@link IBaseDrawable2d}. */
    ;

    @Override
    public void draw(IBaseDrawContext2d ctx)
            throws DrawException {
        ctx.drawLine(point0.getX(), point0.getY(), point1.getX(), point1.getY());
    }

}
