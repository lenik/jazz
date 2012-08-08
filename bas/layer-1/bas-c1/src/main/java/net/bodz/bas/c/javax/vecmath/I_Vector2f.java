package net.bodz.bas.c.javax.vecmath;

public interface I_Vector2f
        extends I_Tuple2f {

    /**
     * Computes the dot product of the this vector and vector v1.
     * 
     * @param v1
     *            the other vector
     */
    float dot(javax.vecmath.Vector2f v1);

    /**
     * Returns the length of this vector.
     * 
     * @return the length of this vector
     */
    float length();

    /**
     * Returns the squared length of this vector.
     * 
     * @return the squared length of this vector
     */
    float lengthSquared();

    /**
     * Sets the value of this vector to the normalization of vector v1.
     * 
     * @param v1
     *            the un-normalized vector
     */
    void normalize(javax.vecmath.Vector2f v1);

    /**
     * Normalizes this vector in place.
     */
    void normalize();

    /**
     * Returns the angle in radians between this vector and the vector parameter; the return value
     * is constrained to the range [0,PI].
     * 
     * @param v1
     *            the other vector
     * @return the angle in radians in the range [0,PI]
     */
    float angle(javax.vecmath.Vector2f v1);

}
