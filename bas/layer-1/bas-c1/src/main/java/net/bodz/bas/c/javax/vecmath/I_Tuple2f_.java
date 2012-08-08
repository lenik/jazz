package net.bodz.bas.c.javax.vecmath;

import javax.vecmath.Tuple2f;

public interface I_Tuple2f_
        extends I_Tuple2f {

    /**
     * Sets the value of this tuple to the vector sum of itself and tuple t1.
     * 
     * @param t1
     *            the other tuple
     */
    I_Tuple2f_ add_(Tuple2f t1);

    /**
     * Sets the value of this tuple to the vector difference of itself and tuple t1 (this = this -
     * t1).
     * 
     * @param t1
     *            the other tuple
     */
    I_Tuple2f_ sub_(Tuple2f t1);

    /**
     * Sets the value of this tuple to the negation of tuple t1.
     * 
     * @param t1
     *            the source tuple
     */
    I_Tuple2f_ negate_(Tuple2f t1);

    /**
     * Negates the value of this vector in place.
     */
    I_Tuple2f_ negate_();

    /**
     * Sets the value of this tuple to the scalar multiplication of itself.
     * 
     * @param s
     *            the scalar value
     */
    I_Tuple2f_ scale_(float s);

    /**
     * Sets the value of this tuple to the scalar multiplication of itself and then adds tuple t1
     * (this = s*this + t1).
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the tuple to be added
     */
    I_Tuple2f_ scaleAdd_(float s, Tuple2f t1);

    /**
     * Sets each component of the tuple parameter to its absolute value and places the modified
     * values into this tuple.
     * 
     * @param t
     *            the source tuple, which will not be modified
     */
    I_Tuple2f_ absolute_(Tuple2f t);

    /**
     * Clamps this tuple to the range [low, high].
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     * @param max
     *            the highest value in this tuple after clamping
     */
    I_Tuple2f_ clamp_(float min, float max);

    /**
     * Clamps the minimum value of this tuple to the min parameter.
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     */
    I_Tuple2f_ clampMin_(float min);

    /**
     * Clamps the maximum value of this tuple to the max parameter.
     * 
     * @param max
     *            the highest value in the tuple after clamping
     */
    I_Tuple2f_ clampMax_(float max);

    /**
     * Sets each component of this tuple to its absolute value.
     */
    I_Tuple2f_ absolute_();

    /**
     * Linearly interpolates between this tuple and tuple t1 and places the result into this tuple:
     * this = (1-alpha)*this + alpha*t1.
     * 
     * @param t1
     *            the first tuple
     * @param alpha
     *            the alpha interpolation parameter
     */
    I_Tuple2f_ interpolate_(Tuple2f t1, float alpha);

}
