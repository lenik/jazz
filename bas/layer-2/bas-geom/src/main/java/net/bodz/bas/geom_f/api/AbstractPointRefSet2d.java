package net.bodz.bas.geom_f.api;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.geom_f.base.IPointRef2d;
import net.bodz.bas.geom_f.base.impl.PointRefSet2dIterator;


public abstract class AbstractPointRefSet2d
        implements IPointRefSet2d {

    @Override
    public float getPointX(int index) {
        IPointRef2d point = getPointRef(index);
        return point.getX();
    }

    @Override
    public float getPointY(int index) {
        IPointRef2d point = getPointRef(index);
        return point.getY();
    }

    @Override
    public Iterable<IPointRef2d> getPointRefs() {
        return new Iterable<IPointRef2d>() {
            @Override
            public Iterator<IPointRef2d> iterator() {
                return getPointRefIterator();
            }
        };
    }

    @Override
    public Iterator<IPointRef2d> getPointRefIterator() {
        return new PointRefSet2dIterator(this);
    }

    @Override
    public IPointRef2d[] toPointRefArray() {
        int n = getPointCount();
        IPointRef2d[] array = new IPointRef2d[n];
        for (int i = 0; i < n; i++)
            array[i] = getPointRef(i);
        return array;
    }

    @Override
    public List<IPointRef2d> toPointRefList() {
        IPointRef2d[] pointArray = toPointRefArray();
        return Arrays.asList(pointArray);
    }

}
