package net.bodz.bas.c.javax.vecmath;

public interface I_Vector3f_
        extends I_Vector3f, I_Tuple3f_ {

    /**
     * Sets this vector to be the vector cross product of vectors v1 and v2.
     * 
     * @param v1
     *            the first vector
     * @param v2
     *            the second vector
     */
    I_Vector3f_ cross_(javax.vecmath.Vector3f v1, javax.vecmath.Vector3f v2);

    /**
     * Normalizes this vector in place.
     */
    I_Vector3f_ normalize_();

}
