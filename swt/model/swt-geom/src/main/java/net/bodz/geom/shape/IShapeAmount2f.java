package net.bodz.geom.shape;

public interface IShapeAmount2f {

    float NOT_AVAILABLE = Float.NaN;

    float length();

    /**
     * Calculate the shape area.
     * 
     * @return NOT_AVAILABLE if not implemented.
     */
    float area();

}
