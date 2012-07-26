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

    public final void normal() {
        rotate((float) (Math.PI / 2f));
    }

    public final void normalOrig() {
        rotate((float) (-Math.PI / 2f));
    }

    public final void product(Vector2f v) {
        float _x = x * v.y;
        float _y = -y * v.x;
        x = _x;
        y = _y;
    }

    // toNews

    public final Vector2f toScale(float k) {
        Vector2f out = new Vector2f(this);
        out.scale(k);
        return out;
    }

    public final Vector2f toNegate() {
        Vector2f out = new Vector2f(this);
        out.negate();
        return out;
    }

    public final Vector2f toNormalize() {
        Vector2f out = new Vector2f(this);
        out.normalize();
        return out;
    }

    public final Vector2f toAngle(float angle) {
        Vector2f out = new Vector2f(this);
        out.angle(angle);
        return out;
    }

    public final Vector2f toRotate(float angle) {
        Vector2f out = new Vector2f(this);
        out.rotate(angle);
        return out;
    }

    // getNews

    public final Vector2f getNormal() {
        return toRotate((float) (Math.PI / 2f));
    }

    public final Vector2f getNormalOrig() {
        return toRotate((float) (-Math.PI / 2f));
    }

    public final Vector2f getProduct(Vector2f v) {
        return new Vector2f(x * v.y, -y * v.x);
    }

    @Override
    public Vector2f clone() {
        return new Vector2f(x, y);
    }

}
