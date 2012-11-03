package net.bodz.bas.geom.spec0_f;

import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.geom.spec1_f.Triangle2d;

public interface ICroppable2d {

    /**
     * Remove contents of this shape outside the given half plane.
     * 
     * @param php
     *            The crop half plane. Must be non-<code>null</code>.
     * @return Maybe not the shape of the same type, if it is a reduced crop.
     */
    IPrimitive2d crop(PositiveHalfPlane2d php, boolean detached);

    /**
     * Remove contents of this shape outside the given rectangle.
     * 
     * @param rectangle
     *            The crop rectangle. Must be non-<code>null</code>.
     * @return Maybe not the shape of the same type, if it is a reduced crop.
     */
    IPrimitive2d crop(Rectangle2d rectangle, boolean detached);

    /**
     * Remove contents of this shape outside the given triangle.
     * 
     * @param triangle
     *            The crop triangle. Must be non-<code>null</code>.
     * @return Maybe not the shape of the same type, if it is a reduced crop.
     */
    IPrimitive2d crop(Triangle2d triangle, boolean detached);

    /**
     * Remove contents of this shape outside the given polygon.
     * 
     * @param convexPolygon
     *            A convex polygon. The crop result is undefined if the polygon is not convex (i.e.,
     *            concave). Must be non-<code>null</code>.
     * @return Maybe not the shape of the same type, if it is a reduced crop.
     */
    IPrimitive2d crop(Polygon2d convexPolygon, boolean detached);

}
