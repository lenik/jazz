package net.bodz.bas.geom.spec0_f;

import net.bodz.bas.geom.spec1_f.Point2d;

public interface IPickable2d {

    PickResult2d _pick(float x, float y);

    PickResult2d _pick(Point2d point);

    IPrimitive2d pick(float x, float y);

    IPrimitive2d pick(Point2d point);

    /**
     * The nearest distance from point to the shape.
     * 
     * @param point
     *            test point
     * @return inside < 0 outside > 0 border == 0
     */
    float distance(float x, float y);

    float distance(Point2d point);

    boolean contains(float x, float y);

    boolean contains(Point2d point);

}
