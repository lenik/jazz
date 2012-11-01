package net.bodz.bas.geom_f.base;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom_f.api.IEditablePointSet2d;

public class Polygon2d
        extends AbstractPolygon2d
        implements IEditablePointSet2d {

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

    @Override
    public Polygon2d clone() {
        List<Point2d> sameList = new ArrayList<Point2d>(points);
        return new Polygon2d(sameList);
    }

    // -o IPointSet2d

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

    // -o IEditablePointRefSet2d

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

    // -o IEditablePointSet2d

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

    // -o IPolygonizable2d

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        if (minSegments <= 1 && maxSegmentLength == null)
            return this;

        // Re-sampling.
        return this;
    }

}
