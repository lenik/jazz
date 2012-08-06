package net.bodz.bas.geom_f.api;

import java.util.Iterator;
import java.util.List;

import net.bodz.bas.geom_f.base.IPointRef2d;

public interface IPointRefSet2d {

    int getPointCount();

    float getPointX(int index);

    float getPointY(int index);

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
