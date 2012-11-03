package net.bodz.bas.geom.spec0_f;

import net.bodz.bas.geom.spec1_f.Polygon2d;

public interface IPolygonizable2d {

    /**
     * Convert the shape to a polygon.
     * 
     * @param minSegments
     *            How many segments each line/arc will be divided into, at least.
     * @param maxSegmentLength
     *            The maximum segment length.
     * @return <code>null</code> If it's not supported.
     */
    Polygon2d polygonize(int minSegments, Float maxSegmentLength);

    /**
     * Convert the shape to a polygon.
     * 
     * @return <code>null</code> If it's not supported.
     */
    Polygon2d polygonize();

}
