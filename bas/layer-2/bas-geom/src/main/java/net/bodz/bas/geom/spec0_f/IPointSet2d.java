package net.bodz.bas.geom.spec0_f;

import java.util.Iterator;
import java.util.List;

import net.bodz.bas.geom.spec1_f.Point2d;

public interface IPointSet2d {

    /**
     * Get the total number of points.
     * 
     * @return Point count.
     */
    int getPointCount();

    /**
     * Get a point.
     * 
     * @param index
     *            The point index.
     * @return The point at the specific index.
     * @throws IndexOutOfBoundsException
     *             If index is out of bound.
     */
    Point2d getPoint(int index);

    /**
     * Get the X coordinate of a point.
     * 
     * @param index
     *            The point index.
     * @throws IndexOutOfBoundsException
     *             If index is out of bound.
     */
    float getPointX(int index);

    /**
     * Get the Y coordinate of a point.
     * 
     * @param index
     *            The point index.
     * @throws IndexOutOfBoundsException
     *             If index is out of bound.
     */
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
