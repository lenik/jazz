package net.bodz.bas.geom.spec0_f.tr;

import net.bodz.bas.geom.spec1_f.Point2d;

public interface ITransformer2d {

    /**
     * Invert the transformmer self.
     */
    void invert();

    /**
     * Transform a vector into a new vector.
     * 
     * @param vector
     *            to be transformed.
     * @return The new transformed vector.
     */
    void transform(javax.vecmath.Vector2f vector);

    /**
     * Transform a point into a new point.
     * 
     * @param point
     *            to be transformed.
     * @return The new transformed point.
     */
    void transform(Point2d point);

    /**
     * Invert-Transform a vector into a new vector.
     * 
     * @param vector
     *            to be transformed.
     * @return The new transformed vector.
     */
    void inverse(javax.vecmath.Vector2f vector);

    /**
     * Invert-Transform a point into a new point.
     * 
     * @param point
     *            to be transformed.
     * @return The new transformed point.
     */
    void inverse(Point2d point);

}
