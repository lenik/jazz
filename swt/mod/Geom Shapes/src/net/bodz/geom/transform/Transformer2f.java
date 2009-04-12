package net.bodz.geom.transform;

import net.bodz.geom.shape.base.Point2f;
import net.bodz.math.mat.Vector2f;

public interface Transformer2f {

    /**
     * Compose with an after-tranformer.
     * 
     * @param suffix
     */
    void mul(Transformer2f suffix);

    /**
     * Compose with a before-transformer.
     * 
     * @param prefix
     */
    void mulBy(Transformer2f prefix);

    /**
     * Invert the transformmer self.
     */
    void invert();

    /**
     * Transform a vector in-place.
     * 
     * @param vector
     *            to be transformed
     */
    void transform(javax.vecmath.Vector2f vector);

    /**
     * Transform a point in-place.
     * 
     * @param point
     *            to be transformed.
     */
    void transform(Point2f point);

    /**
     * Transform a vector into a new vector.
     * 
     * @param vector
     *            to be transformed.
     * @return The new transformed vector.
     */
    Vector2f transformTo(javax.vecmath.Vector2f vector);

    /**
     * Transform a point into a new point.
     * 
     * @param point
     *            to be transformed.
     * @return The new transformed point.
     */
    Point2f transformTo(Point2f point);

    /**
     * Invert-Transform a vector in-place.
     * 
     * @param vector
     *            to be transformed.
     */
    void invert(javax.vecmath.Vector2f vector);

    /**
     * Invert-Transform a point in-place.
     * 
     * @param point
     *            to be transformed.
     */
    void invert(Point2f point);

    /**
     * Invert-Transform a vector into a new vector.
     * 
     * @param vector
     *            to be transformed.
     * @return The new transformed vector.
     */
    Vector2f invertTo(javax.vecmath.Vector2f vector);

    /**
     * Invert-Transform a point into a new point.
     * 
     * @param point
     *            to be transformed.
     * @return The new transformed point.
     */
    Point2f invertTo(Point2f point);

}
