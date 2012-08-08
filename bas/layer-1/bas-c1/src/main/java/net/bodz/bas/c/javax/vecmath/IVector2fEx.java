package net.bodz.bas.c.javax.vecmath;

public interface IVector2fEx
        extends I_Vector2f_ {

    void product(javax.vecmath.Vector2f v);

    IVector2fEx product_(javax.vecmath.Vector2f v);

    float angle(Vector2f vector);

    float arg();

    void arg(float angle);

    IVector2fEx arg_(float angle);

    void rotate(float angle);

    IVector2fEx rotate_(float angle);

    IVector2fEx ccw90_();

    IVector2fEx cw90_();

}
