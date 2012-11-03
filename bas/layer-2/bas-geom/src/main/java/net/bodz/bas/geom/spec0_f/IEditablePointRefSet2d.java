package net.bodz.bas.geom.spec0_f;

import net.bodz.bas.geom.spec1_f.IPointRef2d;

public interface IEditablePointRefSet2d
        extends IPointSet2d {

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */
    void setPoint(int index, IPointRef2d point);

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */

    void addPoint(IPointRef2d point);

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */
    void addPoint(int index, IPointRef2d point);

    /**
     * @throws IndexOutOfBoundsException
     *             If the index is out of bound.
     */
    IPointRef2d removePoint(int index);

}
