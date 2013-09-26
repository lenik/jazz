package net.bodz.bas.geom.spec1_f;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom.spec0_f.IMutablePointSet2d;
import net.bodz.bas.gui.draw_f.dc.DrawException;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawContext2d;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawable2d;
import net.bodz.bas.t.object.ISnapShot;

public class Polygon2d
        extends AbstractPolygon2d
        implements IMutablePointSet2d {

    private static final long serialVersionUID = 1L;

    private List<Point2d> points;

    public Polygon2d(List<Point2d> pointList) {
        if (pointList == null)
            throw new NullPointerException("pointList");
        this.points = pointList;
    }

    public Polygon2d(Point2d... pointArray) {
        int n = pointArray.length;
        this.points = new ArrayList<Point2d>(n);
        for (int i = 0; i < n; i++) {
            Point2d point = pointArray[i];
            if (point == null)
                throw new NullPointerException("point[" + i + "]");
            this.points.add(point);
        }
    }

    public static Polygon2d fromXY(float x[], float y[], int offset, int count) {
        List<Point2d> points = new ArrayList<Point2d>(count);

        int end = offset + count;
        for (int i = offset; i < end; i++) {
            Point2d point = new Point2d(x[i], y[i]);
            points.add(point);
        }

        return new Polygon2d(points);
    }

    public static Polygon2d fromXY(float x[], float y[]) {
        return fromXY(x, y, 0, x.length);
    }

    /** ⇱ Implementation Of {@link ISnapShot}. */
    /* _____________________________ */static section.iface __SNAPSHOT__;

    @Override
    public Polygon2d shot() {
        List<Point2d> sameList = new ArrayList<Point2d>(points);
        return new Polygon2d(sameList);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPointSet2d}. */
    /* _____________________________ */static section.iface __POINTS__;

    @Override
    public List<Point2d> getPoints() {
        return points;
    }

    @Override
    public int getPointCount() {
        return points.size();
    }

    @Override
    public Point2d getPoint(int index) {
        checkPointIndex(index);
        return points.get(index);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IMutablePointRefSet2d}. */
    /* _____________________________ */static section.iface __POINTREFS_MUTABLE__;

    @Override
    public void setPoint(int index, IPointRef2d point) {
        checkPointIndex(index);
        setPoint(index, point.snapshot());
    }

    @Override
    public void addPoint(IPointRef2d point) {
        addPoint(point.snapshot());
    }

    @Override
    public void addPoint(int index, IPointRef2d point) {
        addPoint(index, point.snapshot());
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IMutablePointSet2d}. */
    /* _____________________________ */static section.iface __POINTS_MUTABLE__;

    @Override
    public void setPoint(int index, float x, float y) {
        setPoint(index, new Point2d(x, y));
    }

    @Override
    public final void addPoint(int index, float x, float y) {
        addPoint(index, new Point2d(x, y));
    }

    @Override
    public final void addPoint(float x, float y) {
        int n = getPointCount();
        addPoint(n, new Point2d(x, y));
    }

    @Override
    public final void addPoint(Point2d point) {
        int n = getPointCount();
        addPoint(n, point);
    }

    @Override
    public void setPoint(int index, Point2d point) {
        points.set(index, point);
    }

    @Override
    public void addPoint(int index, Point2d point) {
        points.add(index, point);
    }

    @Override
    public Point2d removePoint(int index) {
        return points.remove(index);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPolygonizable2d}. */
    /* _____________________________ */static section.iface __POLYGONIZE__;

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        if (minSegments <= 1 && maxSegmentLength == null)
            return this;

        // Re-sampling.
        return this;
    }

    /** ⇱ Implementation Of {@link IBaseDrawable2d}. */
    /* _____________________________ */static section.iface __DRAW__;

    @Override
    public void draw(IBaseDrawContext2d ctx)
            throws DrawException {
        ctx.drawPolygon(this);
    }

}
