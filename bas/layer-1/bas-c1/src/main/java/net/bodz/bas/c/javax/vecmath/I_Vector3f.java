package net.bodz.bas.c.javax.vecmath;

public interface I_Vector3f
        extends I_Tuple3f {

    /**
     * Returns the squared length of this vector.
     * 
     * @return the squared length of this vector
     */
    float lengthSquared();

    /**
     * Returns the length of this vector.
     * 
     * @return the length of this vector
     */
    float length();

    /**
     * Sets this vector to be the vector cross product of vectors v1 and v2.
     * 
     * @param v1
     *            the first vector
     * @param v2
     *            the second vector
     */
    void cross(javax.vecmath.Vector3f v1, javax.vecmath.Vector3f v2);

    /**
     * Computes the dot product of this vector and vector v1.
     * 
     * @param v1
     *            the other vector
     * @return the dot product of this vector and v1
     */
    float dot(javax.vecmath.Vector3f v1);

    /**
     * Sets the value of this vector to the normalization of vector v1.
     * 
     * @param v1
     *            the un-normalized vector
     */
    void normalize(javax.vecmath.Vector3f v1);

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
    float angle(javax.vecmath.Vector3f v1);

}
