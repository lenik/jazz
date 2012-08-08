package net.bodz.bas.c.javax.vecmath;

import javax.vecmath.Tuple3f;

public interface I_Tuple3f_
        extends I_Tuple3f {

    /**
     * Sets the value of this tuple to the vector sum of itself and tuple t1.
     * 
     * @param t1
     *            the other tuple
     */
    I_Tuple3f_ add_(Tuple3f t1);

    /**
     * Sets the value of this tuple to the vector difference of itself and tuple t1 (this = this -
     * t1) .
     * 
     * @param t1
     *            the other tuple
     */
    I_Tuple3f_ sub_(Tuple3f t1);

    /**
     * Negates the value of this tuple in place.
     */
    I_Tuple3f_ negate_();

    /**
     * Sets the value of this tuple to the scalar multiplication of the scale factor with this.
     * 
     * @param s
     *            the scalar value
     */
    I_Tuple3f_ scale_(float s);

    /**
     * Sets the value of this tuple to the scalar multiplication of itself and then adds tuple t1
     * (this = s*this + t1).
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the tuple to be added
     */
    I_Tuple3f_ scaleAdd_(float s, Tuple3f t1);

    /**
     * Clamps this tuple to the range [low, high].
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     * @param max
     *            the highest value in this tuple after clamping
     */
    I_Tuple3f_ clamp_(float min, float max);

    /**
     * Clamps the minimum value of this tuple to the min parameter.
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     */
    I_Tuple3f_ clampMin_(float min);

    /**
     * Clamps the maximum value of this tuple to the max parameter.
     * 
     * @param max
     *            the highest value in the tuple after clamping
     */
    I_Tuple3f_ clampMax_(float max);

    /**
     * Sets each component of this tuple to its absolute value.
     */
    I_Tuple3f_ absolute_();

    /**
     * Linearly interpolates between this tuple and tuple t1 and places the result into this tuple:
     * this = (1-alpha)*this + alpha*t1.
     * 
     * @param t1
     *            the first tuple
     * @param alpha
     *            the alpha interpolation parameter
     */
    I_Tuple3f_ interpolate_(Tuple3f t1, float alpha);

}
