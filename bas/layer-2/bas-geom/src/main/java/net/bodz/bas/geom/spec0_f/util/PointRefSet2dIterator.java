package net.bodz.bas.geom.spec0_f.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.geom.spec0_f.IPointRefSet2d;
import net.bodz.bas.geom.spec1_f.IPointRef2d;

public class PointRefSet2dIterator
        implements Iterator<IPointRef2d> {

    IPointRefSet2d points;
    int nextIndex = 0;

    public PointRefSet2dIterator(IPointRefSet2d points) {
        this.points = points;
    }

    @Override
    public boolean hasNext() {
        return nextIndex < points.getPointCount();
    }

    @Override
    public IPointRef2d next() {
        if (nextIndex >= points.getPointCount())
            throw new NoSuchElementException();
        IPointRef2d point = points.getPointRef(nextIndex);
        nextIndex++;
        return point;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
