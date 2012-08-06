package net.bodz.bas.geom_f.c1;

import java.lang.reflect.Array;
import java.util.ArrayList;

import net.bodz.bas.geom_f.base.Point2d;

public class PointSet2ds {

    public static final Point2d[] convertToArray(Object data) {
        if (data == null)
            return null;
        if (data instanceof Point2d)
            return new Point2d[] { (Point2d) data, };
        if (data instanceof Point2d[])
            return (Point2d[]) data;

        Class<?> clazz = data.getClass();
        ArrayList<Point2d> list = new ArrayList<Point2d>();
        if (clazz.isArray()) {
            int length = Array.getLength(data);
            for (int i = 0; i < length; i++) {
                Object v = Array.get(data, i);
                if (v instanceof Point2d)
                    list.add((Point2d) v);
                else if (v instanceof Number && i + 1 < length) {
                    Object v2 = Array.get(data, i + 1);
                    assert v2 instanceof Number;
                    list.add(new Point2d(((Number) v).floatValue(), ((Number) v2).floatValue()));
                } else
                    throw new IllegalArgumentException("Invalid point in point-array-def");
            }
        } else {
            throw new IllegalArgumentException("Invalid points data representation");
        }

        Point2d[] pts = new Point2d[list.size()];
        list.toArray(pts);

        return pts;
    }

}
