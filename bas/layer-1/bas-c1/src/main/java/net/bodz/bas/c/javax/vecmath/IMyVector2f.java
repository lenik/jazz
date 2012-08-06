package net.bodz.bas.c.javax.vecmath;

public interface IMyVector2f
        extends I_Vector2f_ {

    void product(javax.vecmath.Vector2f v);

    IMyVector2f product_(javax.vecmath.Vector2f v);

    float angle(Vector2f vector);

    float arg();

    void arg(float angle);

    IMyVector2f arg_(float angle);

    void rotate(float angle);

    IMyVector2f rotate_(float angle);

    IMyVector2f ccw90_();

    IMyVector2f cw90_();

}
