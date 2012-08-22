package net.bodz.bas.geom_f.api;

import net.bodz.bas.geom_f.base.Polygon2d;

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

    Polygon2d polygonize();

}
