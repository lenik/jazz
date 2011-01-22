package net.bodz.geom.shape;

import java.util.Iterator;
import java.util.List;

import net.bodz.geom.shape.base.Point2f;

public interface PointSet2f {

    int pointCount();

    Point2f pointRef(int index);

    /**
     * some shapes using interpolated points, which needs dynamic generate the
     * control points. then the iterator would reduce times of generation.
     */

    Iterator<Point2f> pointRefIterator();

    Point2f[] toPointRefsArray(boolean copy);

    List<Point2f> toPointRefsList(boolean copy);

    Point2f.Static point(int index);

    float pointX(int index);

    float pointY(int index);

    Iterator<Point2f.Static> pointIterator();

    Point2f.Static[] toPointsArray(boolean copy);

    List<Point2f.Static> toPointsList(boolean copy);

}
