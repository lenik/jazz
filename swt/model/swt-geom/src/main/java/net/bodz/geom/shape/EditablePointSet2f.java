package net.bodz.geom.shape;

import java.util.Iterator;

import net.bodz.geom.shape.base.IPoint2f;

public interface EditablePointSet2f
        extends IPointSet2f {

    int pointCount();

    IPoint2f pointRef(int index);

    Iterator<IPoint2f> pointRefIterator();

    void setPoint(int index, IPoint2f point);

    void setPoint(int index, float x, float y);

    void addPoint(float x, float y);

    void addPoint(IPoint2f point);

    void addPoint(int index, float x, float y);

    void addPoint(int index, IPoint2f point);

    void removePoint(int index);

}
