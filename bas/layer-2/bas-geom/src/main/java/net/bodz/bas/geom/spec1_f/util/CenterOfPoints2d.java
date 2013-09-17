package net.bodz.bas.geom.spec1_f.util;

import net.bodz.bas.geom.spec0_f.IPointSet2d;
import net.bodz.bas.geom.spec1_f.PointRef2d;

public class CenterOfPoints2d
        extends PointRef2d {

    private static final long serialVersionUID = 1L;

    private final IPointSet2d points;

    public CenterOfPoints2d(IPointSet2d points) {
        this.points = points;
    }

    @Override
    public CenterOfPoints2d shot() {
        return new CenterOfPoints2d(points);
    }

    @Override
    public float getX() {
        int n = points.getPointCount();
        if (n == 0)
            return 0.0f;
        float sum = 0.0f;
        for (int i = 0; i < n; i++)
            sum += points.getPointX(i);
        return sum / n;
    }

    @Override
    public float getY() {
        int n = points.getPointCount();
        if (n == 0)
            return 0.0f;
        float sum = 0.0f;
        for (int i = 0; i < n; i++)
            sum += points.getPointX(i);
        return sum / n;
    }

    @Override
    public void setX(float x) {
    }

    @Override
    public void setY(float y) {
    }

}
