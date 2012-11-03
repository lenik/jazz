package net.bodz.bas.geom.spec1_f;


public class LineInf2d
        extends Line2d
        implements ILineInf2d {

    private static final long serialVersionUID = 1L;

    public LineInf2d(float x0, float y0, float x1, float y1) {
        super(x0, y0, x1, y1);
    }

    public LineInf2d(Point2d p0, Point2d p1) {
        super(p0, p1);
    }

}
