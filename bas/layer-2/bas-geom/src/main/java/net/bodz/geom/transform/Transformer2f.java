package net.bodz.geom.transform;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.shape.base.IPointRef2d;

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
    void transform(IPointRef2d point);

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
    IPointRef2d transformTo(IPointRef2d point);

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
    void invert(IPointRef2d point);

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
    IPointRef2d invertTo(IPointRef2d point);

}
