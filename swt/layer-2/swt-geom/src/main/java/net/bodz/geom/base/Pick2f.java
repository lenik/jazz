package net.bodz.geom.base;

import net.bodz.geom.shape.Shape2f;
import net.bodz.geom.shape.base.Point2f;

public interface Pick2f {

    PickInfo2f pickInfo(Point2f point);

    PickInfo2f pickInfo(float x, float y);

    Shape2f pick(Point2f point);

    Shape2f pick(float x, float y);

    /**
     * The nearest distance from point to the shape.
     * 
     * @param point
     *            test point
     * @return inside < 0 outside > 0 border == 0
     */
    float distance(float x, float y);

    float distance(Point2f point);

    boolean contains(float x, float y);

    boolean contains(Point2f point);

}
