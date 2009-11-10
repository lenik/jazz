package net.bodz.math.mat;

import javax.vecmath.Matrix3d;
import javax.vecmath.Vector2f;

public class Matrix3f extends javax.vecmath.Matrix3f {

    static final long serialVersionUID = -3944326553832127601L;

    public Matrix3f() {
        super();
    }

    public Matrix3f(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        super(m00, m01, m02, m10, m11, m12, m20, m21, m22);
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
     * public void transform(Point2f point2f) { Tuple3f t = new Vector3f(point2f);
     * super.transform(t); point2f.x(t.x / t.z); point2f.y(t.y / t.z); }
     */

    @Override
    public Matrix3f clone() {
        return (Matrix3f) super.clone();
    }

    @Override
    public String toString() {
        return String.format("" + // //$NON-NLS-1$
                "[ %10.4f %10.4f %10.4f ]\n" + // //$NON-NLS-1$
                "[ %10.4f %10.4f %10.4f ]\n" + // //$NON-NLS-1$
                "[ %10.4f %10.4f %10.4f ]\n", // //$NON-NLS-1$
                m00, m01, m02, //
                m10, m11, m12, //
                m20, m21, m22);
    }

}
