package net.bodz.bas.geom_f.base;

public class Ellipse2d
        extends AbstractEllipse2d {

    private static final long serialVersionUID = 1L;

    public static final int PT_CENTER1 = 0;
    public static final int PT_CENTER2 = 1;

    Point2d center1;
    Point2d center2;
    float a;
    float b;

    public Ellipse2d(Point2d center1, Point2d center2, float a, float b) {
        assert center1 != null;
        assert center2 != null;
        this.center1 = center1;
        this.center2 = center2;
        this.a = a;
        this.b = b;
    }

    @Override
    public Ellipse2d clone() {
        return new Ellipse2d(center1.clone(), center2.clone(), a, b);
    }

    @Override
    public int getPointCount() {
        return 2;
    }

    @Override
    public Point2d getPoint(int index) {
        switch (index) {
        case PT_CENTER1:
            return center1;
        case PT_CENTER2:
            return center2;
        }
        return null;
    }

    @Override
    public Point2d getCenter1() {
        return center1;
    }

    @Override
    public void setCenter1(Point2d center1) {
        assert center1 != null;
        this.center1 = center1;
    }

    @Override
    public Point2d getCenter2() {
        return center2;
    }

    @Override
    public void setCenter2(Point2d center2) {
        assert center2 != null;
        this.center2 = center2;
    }

    @Override
    public float getTransverseDiameter() {
        return a + a;
    }

    @Override
    public float getConjugateDiameter() {
        return b + b;
    }

}
