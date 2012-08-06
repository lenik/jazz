package net.bodz.bas.geom_f.api;

public enum CurveDirection {

    /**
     * @see CurveOrientation#negative
     */
    clockwise,

    /**
     * @see CurveOrientation#positive
     */
    counterClockwise,

    /**
     * If the polygon not planarized, ie. has any self-intersection.
     * 
     * If the polygon has 0, 1, 2 points, the direction maybe either <code>null</code> or unknown.
     * 
     * @see CurveOrientation#unknown
     */
    unknown,

}
