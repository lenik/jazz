package net.bodz.bas.geom.spec1_f;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom.spec0_f.IMutablePointRefSet2d;

public class Polylink2d
        extends AbstractPolygon2d
        implements IMutablePointRefSet2d {

    private static final long serialVersionUID = 1L;

    List<IPointRef2d> points;

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

    @Override
    public Polylink2d shot() {
        List<IPointRef2d> newList = new ArrayList<IPointRef2d>(points);
        return new Polylink2d(newList);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPointSet2d}. */
    ;

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
    ;

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
    ;

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        if (minSegments <= 1 && maxSegmentLength == null)
            return snapshot();

        return snapshot();
    }

}
