package net.bodz.bas.geom_f.base;

import net.bodz.bas.geom_f.api.AbstractShape2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.api.PickResult2d;
import net.bodz.bas.geom_f.api.PositiveHalfPlane;

public abstract class AbstractEllipse2d
        extends AbstractShape2d
        implements IEllipse2d {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractEllipse2d shot();

    @Override
    public Ellipse2d snapshot() {
        Point2d center1 = getCenter1();
        Point2d center2 = getCenter2();
        float a = getTransverseDiameter();
        float b = getConjugateDiameter();
        return new Ellipse2d(center1.snapshot(), center2.snapshot(), a, b);
    }

    @Override
    public Ellipse2d snap() {
        Point2d center1 = getCenter1();
        Point2d center2 = getCenter2();
        float a = getTransverseDiameter();
        float b = getConjugateDiameter();
        return new Ellipse2d(center1, center2, a, b);
    }

    @Override
    public Point2d degenerate() {
        float td = getTransverseDiameter();
        float cd = getConjugateDiameter();
        if (td < 0)
            return getCenterPoint();
        if (cd < 0)
            return getCenterPoint();
        return null;
    }

    // -o IShapeAmount2d

    @Override
    public float getArea() {
        float a = getTransverseDiameter();
        float b = getConjugateDiameter();
        double area = Math.PI * a * b;
        return (float) area;
    }

    @Override
    public float getLength() {
        float a = getTransverseDiameter();
        float b = getConjugateDiameter();
        double q = a + b;
        double h = Math.pow((a - b) / (a + b), 2.0);
        double m = 22 / (7 * Math.PI) - 1.0;
        double n = Math.pow((a - b) / a, 33.697);
        double L8 = Math.PI * q //
                * (1 + 3 * h / (10 + Math.sqrt(4 - 3 * h))) //
                * (1 + m * n);
        return (float) L8;
    }

    //

    @Override
    public float getFocus() {
        float a = getTransverseDiameter();
        float b = getConjugateDiameter();
        double focus = Math.sqrt(a * a - b * b);
        return (float) focus;
    }

    @Override
    public float getEccentricity() {
        float a = getTransverseDiameter();
        float b = getConjugateDiameter();
        double e = Math.sqrt(1 - b * b / (a * a));
        return (float) e;
    }

    // -o IPickable2d

    @Override
    public PickResult2d _pick(Point2d point) {
        return null;
    }

    @Override
    public float distance(Point2d point) {
        float a = getTransverseDiameter();
        float d1 = getCenter1().distance(point);
        float d2 = getCenter2().distance(point);
        float d = d1 + d2;
        return d - (2 * a);
    }

    // -o IPolygonizable2d

    @Override
    public Polygon2d polygonize() {
        return polygonize(4, null);
    }

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        return null;
    }

    // -o ICroppable2d
    @Override
    public IShape2d crop(PositiveHalfPlane php, boolean detached) {
        return null;
    }

}
