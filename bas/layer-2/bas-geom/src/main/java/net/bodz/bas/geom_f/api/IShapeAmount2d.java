package net.bodz.bas.geom_f.api;

public interface IShapeAmount2d {

    float NOT_AVAILABLE = Float.NaN;

    /**
     * Get the length of the shape outline.
     * 
     * @return NOT_AVAILABLE if not implemented.
     */
    float getLength();

    /**
     * Get the area of the shape body.
     * 
     * @return NOT_AVAILABLE if not implemented.
     */
    float getArea();

}
