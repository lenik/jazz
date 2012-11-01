package net.bodz.bas.geom_f.api;

import net.bodz.bas.geom_f.base.Polygon2d;
import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.geom_f.base.Triangle2d;

public interface ICroppable2d {

    /**
     * Remove contents of this shape outside the given half plane.
     * 
     * @param php
     *            The crop half plane. Must be non-<code>null</code>.
     * @return Maybe not the shape of the same type, if it is a reduced crop.
     */
    IShape2d crop(PositiveHalfPlane php, boolean detached);

    /**
     * Remove contents of this shape outside the given rectangle.
     * 
     * @param rectangle
     *            The crop rectangle. Must be non-<code>null</code>.
     * @return Maybe not the shape of the same type, if it is a reduced crop.
     */
    IShape2d crop(Rectangle2d rectangle, boolean detached);

    /**
     * Remove contents of this shape outside the given triangle.
     * 
     * @param triangle
     *            The crop triangle. Must be non-<code>null</code>.
     * @return Maybe not the shape of the same type, if it is a reduced crop.
     */
    IShape2d crop(Triangle2d triangle, boolean detached);

    /**
     * Remove contents of this shape outside the given polygon.
     * 
     * @param convexPolygon
     *            A convex polygon. The crop result is undefined if the polygon is not convex (i.e.,
     *            concave). Must be non-<code>null</code>.
     * @return Maybe not the shape of the same type, if it is a reduced crop.
     */
    IShape2d crop(Polygon2d convexPolygon, boolean detached);

}
