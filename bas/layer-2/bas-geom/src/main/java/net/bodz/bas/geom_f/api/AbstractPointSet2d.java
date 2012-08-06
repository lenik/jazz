package net.bodz.bas.geom_f.api;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.geom_f.base.impl.PointSet2dIterator;

public abstract class AbstractPointSet2d
        implements IPointSet2d {

    @Override
    public float getPointX(int index) {
        Point2d point = getPoint(index);
        return point.x;
    }

    @Override
    public float getPointY(int index) {
        Point2d point = getPoint(index);
        return point.y;
    }

    @Override
    public Iterable<Point2d> getPoints() {
        return new Iterable<Point2d>() {
            @Override
            public Iterator<Point2d> iterator() {
                return getPointIterator();
            }
        };
    }

    @Override
    public Iterator<Point2d> getPointIterator() {
        return new PointSet2dIterator(this);
    }

    @Override
    public Point2d[] toPointArray(boolean copy) {
        int n = getPointCount();
        Point2d[] array = new Point2d[n];
        if (copy)
            for (int i = 0; i < n; i++)
                array[i] = new Point2d(getPoint(i));
        else
            for (int i = 0; i < n; i++)
                array[i] = getPoint(i);
        return array;
    }

    @Override
    public List<Point2d> toPointList(boolean copy) {
        Point2d[] pointArray = toPointArray(copy);
        return Arrays.asList(pointArray);
    }

}
