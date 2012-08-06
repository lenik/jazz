package net.bodz.bas.c.javax.vecmath;

import javax.vecmath.Tuple2d;
import javax.vecmath.Tuple2f;

public class Vector2f
        extends javax.vecmath.Vector2f
        implements IMyVector2f {

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

    @Override
    public final void product(javax.vecmath.Vector2f v) {
        float _x = x * v.y;
        float _y = -y * v.x;
        x = _x;
        y = _y;
    }

    @Override
    public final Vector2f product_(javax.vecmath.Vector2f v) {
        return new Vector2f(x * v.y, -y * v.x);
    }

    @Override
    public final float angle(Vector2f vector) {
        // assert vector != null;
        // float a = angle();
        // float b = vector.angle();
        // b -= a;
        // if (b <= -Math.PI)
        // b += Math.PI * 2;
        // else if (b > Math.PI)
        // b -= Math.PI * 2;
        // return b;
        return super.angle(vector);
    }

    @Override
    public final float arg() {
        return (float) Math.atan2(y, x);
    }

    @Override
    public final void arg(float angle) {
        float l = length();
        // if (l == 0.0f) return;
        float S = (float) Math.sin(angle);
        float C = (float) Math.cos(angle);
        x = C * l;
        y = S * l;
    }

    @Override
    public Vector2f arg_(float angle) {
        arg(angle);
        return this;
    }

    @Override
    public final void rotate(float angle) {
        float S = (float) Math.sin(angle);
        float C = (float) Math.cos(angle);
        float _x = C * x - S * y;
        float _y = S * x + C * y;
        x = _x;
        y = _y;
    }

    @Override
    public final Vector2f rotate_(float angle) {
        rotate(angle);
        return this;
    }

    @Override
    public final Vector2f ccw90_() {
        rotate_((float) (Math.PI / 2f));
        return this;
    }

    @Override
    public final Vector2f cw90_() {
        rotate_((float) (-Math.PI / 2f));
        return this;
    }

    // I_Vector2f_

    @Override
    public final Vector2f add_(Tuple2f t1) {
        this.add(t1);
        return this;
    }

    @Override
    public final Vector2f sub_(Tuple2f t1) {
        this.sub(t1);
        return this;
    }

    @Override
    public final Vector2f normalize_() {
        this.normalize();
        return this;
    }

    @Override
    public final Vector2f negate_(Tuple2f t1) {
        this.negate(t1);
        return this;
    }

    @Override
    public final Vector2f negate_() {
        this.negate();
        return this;
    }

    @Override
    public final Vector2f scale_(float s) {
        this.scale(s);
        return this;
    }

    @Override
    public final Vector2f scaleAdd_(float s, Tuple2f t1) {
        this.scaleAdd(s, t1);
        return this;
    }

    @Override
    public final Vector2f absolute_(Tuple2f t) {
        this.absolute(t);
        return this;
    }

    @Override
    public final Vector2f clamp_(float min, float max) {
        this.clamp(min, max);
        return this;
    }

    @Override
    public final Vector2f clampMin_(float min) {
        this.clampMin(min);
        return this;
    }

    @Override
    public final Vector2f clampMax_(float max) {
        this.clampMax(max);
        return this;
    }

    @Override
    public final Vector2f absolute_() {
        this.absolute();
        return this;
    }

    @Override
    public final Vector2f interpolate_(Tuple2f t1, float alpha) {
        this.interpolate(t1, alpha);
        return this;
    }

}
