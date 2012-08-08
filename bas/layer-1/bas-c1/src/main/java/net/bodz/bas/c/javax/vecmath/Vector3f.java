package net.bodz.bas.c.javax.vecmath;

import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;

public class Vector3f
        extends javax.vecmath.Vector3f
        implements IVector3fEx {

    private static final long serialVersionUID = 1L;

    public Vector3f() {
        super();
    }

    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    public Vector3f(float[] init) {
        super(init);
    }

    public Vector3f(Tuple3d init) {
        super(init);
    }

    public Vector3f(Tuple3f init) {
        super(init);
    }

    public Vector3f(Vector3d init) {
        super(init);
    }

    public Vector3f(javax.vecmath.Vector3f init) {
        super(init);
    }

    public Vector3f(Vector2f vector2f) {
        super(vector2f.x, vector2f.y, 1.0f);
    }

    @Override
    public Vector3f clone() {
        return new Vector3f(this);
    }

    // ------------------- xxx_(...) for method chaining ------------------------

    @Override
    public I_Tuple3f_ add_(Tuple3f t1) {
        super.add(t1);
        return this;
    }

    @Override
    public I_Tuple3f_ sub_(Tuple3f t1) {
        super.sub(t1);
        return this;
    }

    @Override
    public final Vector3f negate_() {
        super.negate();
        return this;
    }

    @Override
    public final Vector3f scale_(float s) {
        super.scale(s);
        return this;
    }

    @Override
    public final Vector3f scaleAdd_(float s, Tuple3f t1) {
        super.scaleAdd(s, t1);
        return this;
    }

    @Override
    public final Vector3f clamp_(float min, float max) {
        super.clamp(min, max);
        return this;
    }

    @Override
    public final Vector3f clampMin_(float min) {
        super.clampMin(min);
        return this;
    }

    @Override
    public final Vector3f clampMax_(float max) {
        super.clampMax(max);
        return this;
    }

    @Override
    public final Vector3f absolute_() {
        super.absolute();
        return this;
    }

    @Override
    public final Vector3f interpolate_(Tuple3f t1, float alpha) {
        super.interpolate(t1, alpha);
        return this;
    }

    @Override
    public final Vector3f cross_(javax.vecmath.Vector3f v1, javax.vecmath.Vector3f v2) {
        super.cross(v1, v2);
        return this;
    }

    @Override
    public final Vector3f normalize_() {
        super.normalize();
        return this;
    }

}
