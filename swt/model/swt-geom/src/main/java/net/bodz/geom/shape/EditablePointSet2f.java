package net.bodz.geom.shape;

import java.util.Iterator;

import net.bodz.geom.shape.base.Point2f;

public interface EditablePointSet2f
        extends PointSet2f {

    int pointCount();

    Point2f pointRef(int index);

    Iterator<Point2f> pointRefIterator();

    void setPoint(int index, Point2f point);

    void setPoint(int index, float x, float y);

    void addPoint(float x, float y);

    void addPoint(Point2f point);

    void addPoint(int index, float x, float y);

    void addPoint(int index, Point2f point);

    void removePoint(int index);

}
