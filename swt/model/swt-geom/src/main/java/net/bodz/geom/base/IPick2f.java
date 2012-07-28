package net.bodz.geom.base;

import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.base.IPoint2f;

public interface IPick2f {

    PickInfo2f pickInfo(IPoint2f point);

    PickInfo2f pickInfo(float x, float y);

    IShape2f pick(IPoint2f point);

    IShape2f pick(float x, float y);

    /**
     * The nearest distance from point to the shape.
     * 
     * @param point
     *            test point
     * @return inside < 0 outside > 0 border == 0
     */
    float distance(float x, float y);

    float distance(IPoint2f point);

    boolean contains(float x, float y);

    boolean contains(IPoint2f point);

}
