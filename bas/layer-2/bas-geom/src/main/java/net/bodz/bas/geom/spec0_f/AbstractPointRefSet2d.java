package net.bodz.bas.geom.spec0_f;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.geom.spec0_f.util.PointRefSet2dIterator;
import net.bodz.bas.geom.spec1_f.IPointRef2d;

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
