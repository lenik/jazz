package net.bodz.bas.c.javax.vecmath;

import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;

public class Vector3f
        extends javax.vecmath.Vector3f {

    private static final long serialVersionUID = 1L;

    public Vector3f() {
        super();
    }

    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    public Vector3f(float[] init) {
        super(init);
    }

    public Vector3f(Tuple3d init) {
        super(init);
    }

    public Vector3f(Tuple3f init) {
        super(init);
    }

    public Vector3f(Vector3d init) {
        super(init);
    }

    public Vector3f(javax.vecmath.Vector3f init) {
        super(init);
    }

    public Vector3f(Vector2f vector2f) {
        super(vector2f.x, vector2f.y, 1.0f);
    }

    @Override
    public Vector3f clone() {
        return new Vector3f(this);
    }

    // ------------------- xxx_(...) for method chaining ------------------------

    /**
     * Sets the value of this tuple to the negation of tuple t1.
     * 
     * @param t1
     *            the source tuple
     */
    public final Vector3f negate_(Tuple3f t1) {
        super.negate(t1);
        return this;
    }

    /**
     * Negates the value of this tuple in place.
     */
    public final Vector3f negate_() {
        super.negate();
        return this;
    }

    /**
     * Sets the value of this vector to the scalar multiplication of tuple t1.
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the source tuple
     */
    public final Vector3f scale_(float s, Tuple3f t1) {
        super.scale(s, t1);
        return this;
    }

    /**
     * Sets the value of this tuple to the scalar multiplication of the scale factor with this.
     * 
     * @param s
     *            the scalar value
     */
    public final Vector3f scale_(float s) {
        super.scale(s);
        return this;
    }

    /**
     * Sets the value of this tuple to the scalar multiplication of tuple t1 and then adds tuple t2
     * _(this = s*t1 + t2).
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the tuple to be scaled and added
     * @param t2
     *            the tuple to be added without a scale
     */
    public final Vector3f scaleAdd_(float s, Tuple3f t1, Tuple3f t2) {
        super.scaleAdd(s, t1, t2);
        return this;
    }

    /**
     * Sets the value of this tuple to the scalar multiplication of itself and then adds tuple t1
     * _(this = s*this + t1).
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the tuple to be added
     */
    public final Vector3f scaleAdd_(float s, Tuple3f t1) {
        super.scaleAdd(s, t1);
        return this;
    }

    /**
     * Clamps the tuple parameter to the range [low, high] and places the values into this tuple.
     * 
     * @param min
     *            the lowest value in the tuple after clamping
     * @param max
     *            the highest value in the tuple after clamping
     * @param t
     *            the source tuple, which will not be modified
     */
    public final Vector3f clamp_(float min, float max, Tuple3f t) {
        super.clamp(min, max, t);
        return this;
    }

    /**
     * Clamps the minimum value of the tuple parameter to the min parameter and places the values
     * into this tuple.
     * 
     * @param min
     *            the lowest value in the tuple after clamping
     * @param t
     *            the source tuple, which will not be modified
     */
    public final Vector3f clampMin_(float min, Tuple3f t) {
        super.clampMin(min, t);
        return this;
    }

    /**
     * Clamps the maximum value of the tuple parameter to the max parameter and places the values
     * into this tuple.
     * 
     * @param max
     *            the highest value in the tuple after clamping
     * @param t
     *            the source tuple, which will not be modified
     */
    public final Vector3f clampMax_(float max, Tuple3f t) {
        super.clampMax(max, t);
        return this;
    }

    /**
     * Sets each component of the tuple parameter to its absolute value and places the modified
     * values into this tuple.
     * 
     * @param t
     *            the source tuple, which will not be modified
     */
    public final Vector3f absolute_(Tuple3f t) {
        super.absolute(t);
        return this;
    }

    /**
     * Clamps this tuple to the range [low, high].
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     * @param max
     *            the highest value in this tuple after clamping
     */
    public final Vector3f clamp_(float min, float max) {
        super.clamp(min, max);
        return this;
    }

    /**
     * Clamps the minimum value of this tuple to the min parameter.
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     */
    public final Vector3f clampMin_(float min) {
        super.clampMin(min);
        return this;
    }

    /**
     * Clamps the maximum value of this tuple to the max parameter.
     * 
     * @param max
     *            the highest value in the tuple after clamping
     */
    public final Vector3f clampMax_(float max) {
        super.clampMax(max);
        return this;
    }

    /**
     * Sets each component of this tuple to its absolute value.
     */
    public final Vector3f absolute_() {
        super.absolute();
        return this;
    }

    /**
     * Linearly interpolates between tuples t1 and t2 and places the result into this tuple: this =
     * (1-alpha)*t1 + alpha*t2.
     * 
     * @param t1
     *            the first tuple
     * @param t2
     *            the second tuple
     * @param alpha
     *            the alpha interpolation parameter
     */
    public final Vector3f interpolate_(Tuple3f t1, Tuple3f t2, float alpha) {
        super.interpolate(t1, t2, alpha);
        return this;
    }

    /**
     * Linearly interpolates between this tuple and tuple t1 and places the result into this tuple:
     * this = (1-alpha)*this + alpha*t1.
     * 
     * @param t1
     *            the first tuple
     * @param alpha
     *            the alpha interpolation parameter
     */
    public final Vector3f interpolate_(Tuple3f t1, float alpha) {
        super.interpolate(t1, alpha);
        return this;
    }

    /**
     * Sets this vector to be the vector cross product of vectors v1 and v2.
     * 
     * @param v1
     *            the first vector
     * @param v2
     *            the second vector
     */
    public final Vector3f cross_(Vector3f v1, Vector3f v2) {
        super.cross(v1, v2);
        return this;
    }

    /**
     * Sets the value of this vector to the normalization of vector v1.
     * 
     * @param v1
     *            the un-normalized vector
     */
    public final Vector3f normalize_(Vector3f v1) {
        super.normalize(v1);
        return this;
    }

    /**
     * Normalizes this vector in place.
     */
    public final Vector3f normalize_() {
        super.normalize();
        return this;
    }

}
