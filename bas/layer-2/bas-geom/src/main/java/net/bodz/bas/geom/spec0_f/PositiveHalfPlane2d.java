package net.bodz.bas.geom.spec0_f;

import net.bodz.bas.geom.spec1_f.Line2d;
import net.bodz.bas.geom.spec1_f.Point2d;

public class PositiveHalfPlane2d
        extends Line2d {

    private static final long serialVersionUID = 1L;

    public PositiveHalfPlane2d(float x0, float y0, float x1, float y1) {
        super(x0, y0, x1, y1);
    }

    public PositiveHalfPlane2d(Point2d p0, Point2d p1) {
        super(p0, p1);
    }

    @Override
    public PositiveHalfPlane2d shot() {
        return new PositiveHalfPlane2d(point0.shot(), point1.shot());
    }

    @Override
    public PositiveHalfPlane2d snapshot() {
        return new PositiveHalfPlane2d(point0.snapshot(), point1.snapshot());
    }

    public Point2d getBase() {
        return super.getPoint0();
    }

    /**
     * Is this point at left of a line specified by <base, normal>.
     */
    @Override
    public boolean contains(Point2d point) {
        CurveDirection direction = getDirection(point);
        return direction == CurveDirection.counterClockwise;
    }

}
