package net.bodz.bas.geom_f.api;

import net.bodz.bas.geom_f.base.Point2d;

public interface IEditablePointSet2d
        extends IPointSet2d {

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */
    void setPoint(int index, Point2d point);

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */
    void setPoint(int index, float x, float y);

    void addPoint(float x, float y);

    void addPoint(Point2d point);

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */
    void addPoint(int index, float x, float y);

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */
    void addPoint(int index, Point2d point);

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */
    Point2d removePoint(int index);

}
