package net.bodz.bas.geom_f.base.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.geom_f.api.IPointSet2d;
import net.bodz.bas.geom_f.base.Point2d;

public class PointSet2dIterator
        implements Iterator<Point2d> {

    IPointSet2d points;
    int nextIndex = 0;

    public PointSet2dIterator(IPointSet2d points) {
        this.points = points;
    }

    @Override
    public boolean hasNext() {
        return nextIndex < points.getPointCount();
    }

    @Override
    public Point2d next() {
        if (nextIndex >= points.getPointCount())
            throw new NoSuchElementException();
        Point2d point = points.getPoint(nextIndex);
        nextIndex++;
        return point;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
