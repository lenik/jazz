package net.bodz.geom.shape;

import java.util.Iterator;

import net.bodz.geom.shape.base.IPointRef2d;

public interface EditablePointSet2f
        extends IPointSet2f {

    int pointCount();

    IPointRef2d pointRef(int index);

    Iterator<IPointRef2d> pointRefIterator();

    void setPoint(int index, IPointRef2d point);

    void setPoint(int index, float x, float y);

    void addPoint(float x, float y);

    void addPoint(IPointRef2d point);

    void addPoint(int index, float x, float y);

    void addPoint(int index, IPointRef2d point);

    void removePoint(int index);

}
