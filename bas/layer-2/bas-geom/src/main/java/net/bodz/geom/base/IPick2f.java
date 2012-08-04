package net.bodz.geom.base;

import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.base.IPointRef2d;

public interface IPick2f {

    PickInfo2f pickInfo(IPointRef2d point);

    PickInfo2f pickInfo(float x, float y);

    IShape2f pick(IPointRef2d point);

    IShape2f pick(float x, float y);

    /**
     * The nearest distance from point to the shape.
     * 
     * @param point
     *            test point
     * @return inside < 0 outside > 0 border == 0
     */
    float distance(float x, float y);

    float distance(IPointRef2d point);

    boolean contains(float x, float y);

    boolean contains(IPointRef2d point);

}
