package net.bodz.bas.geom_f.api;

import java.util.Iterator;
import java.util.List;

import net.bodz.bas.geom_f.base.IPointRef2d;

public interface IPointRefSet2d {

    /**
     * Get the total number of point references.
     * 
     * @return Point reference count.
     */
    int getPointCount();

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
     * Get a point reference.
     * 
     * @param index
     *            The point index.
     * @return The point reference at the specific index.
     * @throws IndexOutOfBoundsException
     *             If index is out of bound.
     */
    IPointRef2d getPointRef(int index);

    /**
     * some shapes using interpolated points, which needs dynamic generate the control points. then
     * the iterator would reduce times of generation.
     */
    Iterable<IPointRef2d> getPointRefs();

    Iterator<IPointRef2d> getPointRefIterator();

    IPointRef2d[] toPointRefArray();

    List<IPointRef2d> toPointRefList();

}
