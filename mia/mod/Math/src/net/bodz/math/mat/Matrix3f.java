package net.bodz.math.mat;

import javax.vecmath.Matrix3d;
import javax.vecmath.Vector2f;

public class Matrix3f extends javax.vecmath.Matrix3f {

    static final long serialVersionUID = -3944326553832127601L;

    public Matrix3f() {
        super();
    }

    public Matrix3f(float f, float f1, float f2, float f3, float f4, float f5,
            float f6, float f7, float f8) {
        super(f, f1, f2, f3, f4, f5, f6, f7, f8);
    }

    public Matrix3f(float[] af) {
        super(af);
    }

    public Matrix3f(Matrix3d matrix3d) {
        super(matrix3d);
    }

    public Matrix3f(javax.vecmath.Matrix3f matrix3f) {
        super(matrix3f);
    }

    public final void mulBy(Matrix3f mat) {
        Matrix3f c = clone();
        set(mat);
        mul(c);
    }

    public final void div(Matrix3f mat) {
        Matrix3f inv = mat.clone();
        inv.invert();
        mul(inv);
    }

    public final void divBy(Matrix3f mat) {
        invert();
        mulBy(mat);
    }

    public void transform(Vector2f vector2f) {
        Vector3f v = new Vector3f(vector2f);
        super.transform(v);
        vector2f.x = v.x / v.z;
        vector2f.y = v.y / v.z;
    }

    /*
     * public void transform(Point2f point2f) { Tuple3f t = new
     * Vector3f(point2f); super.transform(t); point2f.x(t.x / t.z);
     * point2f.y(t.y / t.z); }
     */

    @Override
    public Matrix3f clone() {
        return (Matrix3f) super.clone();
    }

    @Override
    public String toString() {
        return String.format("" + //
                "[ %10.4f %10.4f %10.4f ]\n" + //
                "[ %10.4f %10.4f %10.4f ]\n" + //
                "[ %10.4f %10.4f %10.4f ]\n", //
                m00, m01, m02, //
                m10, m11, m12, //
                m20, m21, m22);
    }

}
