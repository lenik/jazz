package net.bodz.bas.geom_f.api;

import net.bodz.bas.geom_f.base.Line2d;
import net.bodz.bas.geom_f.base.Point2d;

public class PositiveHalfPlane
        extends Line2d {

    private static final long serialVersionUID = 1L;

    public PositiveHalfPlane(float x0, float y0, float x1, float y1) {
        super(x0, y0, x1, y1);
    }

    public PositiveHalfPlane(Point2d p0, Point2d p1) {
        super(p0, p1);
    }

    @Override
    public PositiveHalfPlane shot() {
        return new PositiveHalfPlane(p0.shot(), p1.shot());
    }

    @Override
    public PositiveHalfPlane snapshot() {
        return new PositiveHalfPlane(p0.snapshot(), p1.snapshot());
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
