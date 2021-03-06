package net.bodz.bas.geom.spec1_f;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom.spec0_f.IMutablePointRefSet2d;
import net.bodz.bas.gui.draw_f.dc.DrawException;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawContext2d;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawable2d;
import net.bodz.bas.t.object.ISnapShot;

public class Polylink2d
        extends AbstractPolygon2d
        implements IMutablePointRefSet2d {

    private static final long serialVersionUID = 1L;

    private List<IPointRef2d> points;

    public Polylink2d(List<IPointRef2d> pointList) {
        if (pointList == null)
            throw new NullPointerException("points");
        this.points = pointList;
    }

    /**
     * @throws NullPointerException
     *             If the array contains null point.
     */
    public Polylink2d(IPointRef2d... pointArray) {
        this.points = new ArrayList<IPointRef2d>(pointArray.length);
        for (int i = 0; i < pointArray.length; i++) {
            IPointRef2d point = pointArray[i];
            if (point == null)
                throw new NullPointerException("pointRef[" + i + "]");
            points.add(point);
        }
    }

    /** ⇱ Implementation Of {@link ISnapShot}. */
    /* _____________________________ */static section.iface __SNAPSHOT__;

    @Override
    public Polylink2d shot() {
        List<IPointRef2d> newList = new ArrayList<IPointRef2d>(points);
        return new Polylink2d(newList);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPointSet2d}. */
    /* _____________________________ */static section.iface __POINTS__;

    @Override
    public int getPointCount() {
        return points.size();
    }

    @Override
    public Point2d getPoint(int index) {
        checkPointIndex(index);
        IPointRef2d pointRef = getPointRef(index);
        return pointRef.snapshot();
    }

    @Override
    public IPointRef2d getPointRef(int index) {
        checkPointIndex(index);
        return points.get(index);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IMutablePointRefSet2d}. */
    /* _____________________________ */static section.iface __POINTREFS_MUTABLE__;

    @Override
    public void setPoint(int index, IPointRef2d point) {
        checkPointIndex(index);
        points.set(index, point);
    }

    @Override
    public void addPoint(IPointRef2d point) {
        points.add(point);

    }

    @Override
    public void addPoint(int index, IPointRef2d point) {
        points.add(index, point);
    }

    @Override
    public IPointRef2d removePoint(int index) {
        return points.remove(index);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPolygonizable2d}. */
    /* _____________________________ */static section.iface __POLYGONIZE__;

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        if (minSegments <= 1 && maxSegmentLength == null)
            return snapshot();

        return snapshot();
    }

    /** ⇱ Implementation Of {@link IBaseDrawable2d}. */
    /* _____________________________ */static section.iface __DRAW__;

    @Override
    public void draw(IBaseDrawContext2d ctx)
            throws DrawException {
        Polygon2d polygon = snapshot();
        ctx.drawPolygon(polygon);
    }

}
