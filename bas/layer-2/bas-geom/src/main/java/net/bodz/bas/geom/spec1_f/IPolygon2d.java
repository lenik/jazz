package net.bodz.bas.geom.spec1_f;

import java.util.List;

import net.bodz.bas.geom.spec0_f.CurveDirection;
import net.bodz.bas.geom.spec0_f.IMutablePointRefSet2d;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.IShapeAmount2d;

public interface IPolygon2d
        extends IPrimitive2d, IShapeAmount2d, IMutablePointRefSet2d {

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IMutablePointSet2d}. */
    ;

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPrimitive2d}. */
    ;

    /** ⇱ Implementation Of {@link ISnapShot}. */
    ;

    @Override
    Polygon2d snap();

    @Override
    IPolygon2d shot();

    @Override
    Polygon2d snapshot();

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IShapeAmount2d}. */
    ;

    /**
     * Open/close is only affect to following operations: addCross draw
     */
    boolean isOpened();

    boolean isClosed();

    void open();

    void close();

    // CurveOrientation getOrientation();

    CurveDirection getDirection();

    void reverse();

    Line2d getEdge(int index);

    Link2d getLink(int index);

    Line2d line(int index1, int index2);

    Link2d link(int index1, int index2);

    /**
     * Get interpolated point at specified index.
     * 
     * @return point[index] if index is integer, or new created point by linear-interpolation.
     */
    Point2d getPointLinear(float index);

    boolean isIntersected(Line2d line);

    List<Point2d> getIntersectionLinear(Line2d line);

    List<Float> getIntersectionIndexesLinear(Line2d line);

    /**
     * Get interpolated point at specified index, create/insert when necessary.
     * 
     * @param index
     *            Float index to be smoothed.
     * @return point[index] if index is integer, or new created point by linear-interpolation.
     */
    Point2d refineLinear(float index);

    /**
     * Insert interpolated points at points intersected by the specified line.
     * 
     * @param crossLine
     *            The line to cross the polygon.
     * @return List of points (may be existing ones and new created ones) intersected by the cross
     *         line.
     */
    List<Point2d> refineLinear(Line2d crossLine);

    void planarize();

    Triangle2d[] toTriangleArray();

}
