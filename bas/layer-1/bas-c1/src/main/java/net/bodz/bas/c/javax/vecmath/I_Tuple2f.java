package net.bodz.bas.c.javax.vecmath;

import javax.vecmath.Tuple2d;
import javax.vecmath.Tuple2f;

public interface I_Tuple2f {

    /**
     * Get the <i>x</i> coordinate.
     * 
     * @return the <i>x</i> coordinate.
     * 
     * @since vecmath 1.5
     */
    float getX();

    /**
     * Set the <i>x</i> coordinate.
     * 
     * @param x
     *            value to <i>x</i> coordinate.
     * 
     * @since vecmath 1.5
     */
    void setX(float x);

    /**
     * Get the <i>y</i> coordinate.
     * 
     * @return the <i>y</i> coordinate.
     * 
     * @since vecmath 1.5
     */
    float getY();

    /**
     * Set the <i>y</i> coordinate.
     * 
     * @param y
     *            value to <i>y</i> coordinate.
     * 
     * @since vecmath 1.5
     */
    void setY(float y);

    /**
     * Sets the value of this tuple to the specified xy coordinates.
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    void set(float x, float y);

    /**
     * Sets the value of this tuple from the 2 values specified in the array.
     * 
     * @param t
     *            the array of length 2 containing xy in order
     */
    void set(float[] t);

    /**
     * Sets the value of this tuple to the value of the Tuple2f argument.
     * 
     * @param t1
     *            the tuple to be copied
     */
    void set(Tuple2f t1);

    /**
     * Sets the value of this tuple to the value of the Tuple2d argument.
     * 
     * @param t1
     *            the tuple to be copied
     */
    void set(Tuple2d t1);

    /**
     * Copies the value of the elements of this tuple into the array t.
     * 
     * @param t
     *            the array that will contain the values of the vector
     */
    void get(float[] t);

    /**
     * Sets the value of this tuple to the vector sum of tuples t1 and t2.
     * 
     * @param t1
     *            the first tuple
     * @param t2
     *            the second tuple
     */
    void add(Tuple2f t1, Tuple2f t2);

    /**
     * Sets the value of this tuple to the vector sum of itself and tuple t1.
     * 
     * @param t1
     *            the other tuple
     */
    void add(Tuple2f t1);

    /**
     * Sets the value of this tuple to the vector difference of tuple t1 and t2 (this = t1 - t2).
     * 
     * @param t1
     *            the first tuple
     * @param t2
     *            the second tuple
     */
    void sub(Tuple2f t1, Tuple2f t2);

    /**
     * Sets the value of this tuple to the vector difference of itself and tuple t1 (this = this -
     * t1).
     * 
     * @param t1
     *            the other tuple
     */
    void sub(Tuple2f t1);

    /**
     * Sets the value of this tuple to the negation of tuple t1.
     * 
     * @param t1
     *            the source tuple
     */
    void negate(Tuple2f t1);

    /**
     * Negates the value of this vector in place.
     */
    void negate();

    /**
     * Sets the value of this tuple to the scalar multiplication of tuple t1.
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the source tuple
     */
    void scale(float s, Tuple2f t1);

    /**
     * Sets the value of this tuple to the scalar multiplication of itself.
     * 
     * @param s
     *            the scalar value
     */
    void scale(float s);

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
    void scaleAdd(float s, Tuple2f t1, Tuple2f t2);

    /**
     * Sets the value of this tuple to the scalar multiplication of itself and then adds tuple t1
     * (this = s*this + t1).
     * 
     * @param s
     *            the scalar value
     * @param t1
     *            the tuple to be added
     */
    void scaleAdd(float s, Tuple2f t1);

    /**
     * Returns true if the L-infinite distance between this tuple and tuple t1 is less than or equal
     * to the epsilon parameter, otherwise returns false. The L-infinite distance is equal to
     * MAX[abs(x1-x2), abs(y1-y2)].
     * 
     * @param t1
     *            the tuple to be compared to this tuple
     * @param epsilon
     *            the threshold value
     * @return true or false
     */
    boolean epsilonEquals(Tuple2f t1, float epsilon);

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
    void clamp(float min, float max, Tuple2f t);

    /**
     * Clamps the minimum value of the tuple parameter to the min parameter and places the values
     * into this tuple.
     * 
     * @param min
     *            the lowest value in the tuple after clamping
     * @param t
     *            the source tuple, which will not be modified
     */
    void clampMin(float min, Tuple2f t);

    /**
     * Clamps the maximum value of the tuple parameter to the max parameter and places the values
     * into this tuple.
     * 
     * @param max
     *            the highest value in the tuple after clamping
     * @param t
     *            the source tuple, which will not be modified
     */
    void clampMax(float max, Tuple2f t);

    /**
     * Sets each component of the tuple parameter to its absolute value and places the modified
     * values into this tuple.
     * 
     * @param t
     *            the source tuple, which will not be modified
     */
    void absolute(Tuple2f t);

    /**
     * Clamps this tuple to the range [low, high].
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     * @param max
     *            the highest value in this tuple after clamping
     */
    void clamp(float min, float max);

    /**
     * Clamps the minimum value of this tuple to the min parameter.
     * 
     * @param min
     *            the lowest value in this tuple after clamping
     */
    void clampMin(float min);

    /**
     * Clamps the maximum value of this tuple to the max parameter.
     * 
     * @param max
     *            the highest value in the tuple after clamping
     */
    void clampMax(float max);

    /**
     * Sets each component of this tuple to its absolute value.
     */
    void absolute();

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
    void interpolate(Tuple2f t1, Tuple2f t2, float alpha);

    /**
     * Linearly interpolates between this tuple and tuple t1 and places the result into this tuple:
     * this = (1-alpha)*this + alpha*t1.
     * 
     * @param t1
     *            the first tuple
     * @param alpha
     *            the alpha interpolation parameter
     */
    void interpolate(Tuple2f t1, float alpha);

}
