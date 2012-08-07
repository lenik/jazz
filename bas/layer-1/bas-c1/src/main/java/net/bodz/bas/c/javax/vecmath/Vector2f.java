package net.bodz.bas.c.javax.vecmath;

import javax.vecmath.Tuple2d;
import javax.vecmath.Tuple2f;

public class Vector2f
        extends javax.vecmath.Vector2f
        implements Cloneable {

    private static final long serialVersionUID = 1L;

    public Vector2f() {
    }

    public Vector2f(float x, float y) {
        super(x, y);
    }

    public Vector2f(float init[]) {
        super(init);
    }

    public Vector2f(Tuple2f init) {
        super(init);
    }

    public Vector2f(Tuple2d init) {
        super(init);
    }

    @Override
    public Vector2f clone() {
        return new Vector2f(x, y);
    }

    public final void product(Vector2f v) {
        float _x = x * v.y;
        float _y = -y * v.x;
        x = _x;
        y = _y;
    }

    public final Vector2f product_(Vector2f v) {
        return new Vector2f(x * v.y, -y * v.x);
    }

    public final float angle() {
        return (float) Math.atan2(y, x);
    }

    public final void angle(float angle) {
        float l = length();
        // if (l == 0.0f) return;
        float S = (float) Math.sin(angle);
        float C = (float) Math.cos(angle);
        x = C * l;
        y = S * l;
    }

    public final float angle(Vector2f vector) {
        assert vector != null;
        float a = angle();
        float b = vector.angle();
        b -= a;
        if (b <= -Math.PI)
            b += Math.PI * 2;
        else if (b > Math.PI)
            b -= Math.PI * 2;
        return b;
    }

    public final void rotate(float angle) {
        float S = (float) Math.sin(angle);
        float C = (float) Math.cos(angle);
        float _x = C * x - S * y;
        float _y = S * x + C * y;
        x = _x;
        y = _y;
    }

    public final Vector2f rotate_(float angle) {
        rotate(angle);
        return this;
    }

    public final Vector2f getNormal() {
        return clone().rotate_((float) (Math.PI / 2f));
    }

    public final Vector2f getNormalOrig() {
        return clone().rotate_((float) (-Math.PI / 2f));
    }

    // ------------------- xxx_(...) for method chaining ------------------------

    /**
     * Sets the value of this tuple to the negation of tuple t1.
     * 
     * @param t1
     *            the source tuple
     */
    public final Vector2f negate_(Tuple2f t1) {
        super.negate(t1);
        return this;
    }

    /**
     * Negates the value of this vector in place.
     */
    public final Vector2f negate_() {
        super.negate();
        return this;
    }

    /**
     * Sets the value of this tuple to the scalar multiplication of tuple t1.
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the source tuple
     */
    public final Vector2f scale_(float s, Tuple2f t1) {
        super.scale(s, t1);
        return this;
    }

    /**
     * Sets the value of this tuple to the scalar multiplication of itself.
     * 
     * @param s
     *            the scalar value
     */
    public final Vector2f scale_(float s) {
        super.scale(s);
        return this;
    }

    /**
     * Sets the value of this tuple to the scalar multiplication of tuple t1 and then adds tuple t2
     * (this = s*t1 + t2).
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the tuple to be multipled
     * @param t2
     *            the tuple to be added
     */
    public final Vector2f scaleAdd_(float s, Tuple2f t1, Tuple2f t2) {
        super.scaleAdd(s, t1, t2);
        return this;
    }

    /**
     * Sets the value of this tuple to the scalar multiplication of itself and then adds tuple t1
     * (this = s*this + t1).
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the tuple to be added
     */
    public final Vector2f scaleAdd_(float s, Tuple2f t1) {
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
    public final Vector2f clamp_(float min, float max, Tuple2f t) {
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
    public final Vector2f clampMin_(float min, Tuple2f t) {
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
    public final Vector2f clampMax_(float max, Tuple2f t) {
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
    public final Vector2f absolute_(Tuple2f t) {
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
    public final Vector2f clamp_(float min, float max) {
        super.clamp(min, max);
        return this;
    }

    /**
     * Clamps the minimum value of this tuple to the min parameter.
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     */
    public final Vector2f clampMin_(float min) {
        super.clampMin(min);
        return this;
    }

    /**
     * Clamps the maximum value of this tuple to the max parameter.
     * 
     * @param max
     *            the highest value in the tuple after clamping
     */
    public final Vector2f clampMax_(float max) {
        super.clampMax(max);
        return this;
    }

    /**
     * Sets each component of this tuple to its absolute value.
     */
    public final Vector2f absolute_() {
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
    public final Vector2f interpolate_(Tuple2f t1, Tuple2f t2, float alpha) {
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
    public final Vector2f interpolate_(Tuple2f t1, float alpha) {
        super.interpolate(t1, alpha);
        return this;
    }

}
