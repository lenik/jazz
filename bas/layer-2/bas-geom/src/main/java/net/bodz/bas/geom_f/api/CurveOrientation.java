package net.bodz.bas.geom_f.api;

public enum CurveOrientation {

    /**
     * A positively oriented curve is a planar simple closed curve (that is, a curve in the plane
     * whose starting point is also the end point and which has no other self-intersections) such
     * that when traveling on it one always has the curve interior to the left (and consequently,
     * the curve exterior to the right).
     * 
     * @see CurveDirection#counterClockwise
     */
    positive,

    /**
     * A positively oriented curve is a planar simple closed curve (that is, a curve in the plane
     * whose starting point is also the end point and which has no other self-intersections) such
     * that when traveling on it one always has the curve interior to the left (and consequently,
     * the curve exterior to the right).
     * 
     * @see CurveDirection#clockwise
     */
    negative,

    /**
     * If the polygon not planarized, ie. has any self-intersection.
     * 
     * If the polygon has 0, 1, 2 points, the orientation maybe either <code>null</code> or unknown.
     * 
     * @see CurveDirection#unknown
     */
    unknown,

}
