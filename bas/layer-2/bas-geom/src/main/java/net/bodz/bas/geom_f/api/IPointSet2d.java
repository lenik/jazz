package net.bodz.bas.geom_f.api;

import java.util.Iterator;
import java.util.List;

import net.bodz.bas.geom_f.base.Point2d;

public interface IPointSet2d {

    int getPointCount();

    Point2d getPoint(int index);

    float getPointX(int index);

    float getPointY(int index);

    /**
     * some shapes using interpolated points, which needs dynamic generate the control points. then
     * the iterator would reduce times of generation.
     */
    Iterable<Point2d> getPoints();

    Iterator<Point2d> getPointIterator();

    /**
     * @param copy
     *            <code>true</code> to return point copies. However, <code>false</code> may also
     *            return copies.
     */
    Point2d[] toPointArray(boolean copy);

    /**
     * @param copy
     *            <code>true</code> to return point copies. However, <code>false</code> may also
     *            return copies.
     */
    List<Point2d> toPointList(boolean copy);

}