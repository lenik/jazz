package net.bodz.bas.geom.spec0_f.tr;

import net.bodz.bas.c.javax.vecmath.Matrices;
import net.bodz.bas.c.javax.vecmath.Matrix2f;
import net.bodz.bas.c.javax.vecmath.Matrix3f;
import net.bodz.bas.geom.spec1_f.Point2d;

public class MatrixTransformer2d
        implements ITransformer2d {

    Matrix3f matrix;

    public MatrixTransformer2d() {
        this.matrix = Matrices.ident3f.clone();
    }

    public MatrixTransformer2d(Matrix3f matrix3f) {
        this.matrix = matrix3f;
    }

    public MatrixTransformer2d(float x00, float x01, float x02, float x10, float x11, float x12, float x20, float x21,
            float x22) {
        this.matrix = new Matrix3f(x00, x01, x02, x10, x11, x12, x20, x21, x22);
    }

    public MatrixTransformer2d(Matrix2f matrix2f) {
        matrix = new Matrix3f(//
                matrix2f.x00, matrix2f.x01, 0.0f,//
                matrix2f.x10, matrix2f.x11, 0.0f, //
                0.0f, 0.0f, 1.0f);
    }

    public MatrixTransformer2d(float x00, float x01, float x10, float x11) {
        matrix = new Matrix3f(//
                x00, x01, 0.0f, //
                x10, x11, 0.0f, //
                0.0f, 0.0f, 1.0f);
    }

    @Override
    public void invert() {
        matrix.invert();
    }

    @Override
    public void transform(Point2d point) {
        javax.vecmath.Vector2f vector = new javax.vecmath.Vector2f(point.x, point.y);
        matrix.transform(vector);
        point.x = vector.x;
        point.y = vector.y;
    }

    @Override
    public void transform(javax.vecmath.Vector2f vector) {
        matrix.transform(vector);
    }

    @Override
    public void inverse(Point2d point) {
        Matrix3f inv = matrix.clone();
        inv.invert();

        javax.vecmath.Vector2f vector = new javax.vecmath.Vector2f(point.x, point.y);
        inv.transform(vector);
        point.x = vector.x;
        point.y = vector.y;
    }

    @Override
    public void inverse(javax.vecmath.Vector2f vector) {
        Matrix3f inv = matrix.clone();
        inv.invert();
        inv.transform(vector);
    }

    // Matrix operations

    public void mulBy(Matrix3f mat) {
        matrix.mulBy(mat);
    }

    public void mul(float scalar) {
        matrix.mul(scalar);
    }

    public void mul(float scalar, javax.vecmath.Matrix3f m1) {
        matrix.mul(scalar, m1);
    }

    public void mul(javax.vecmath.Matrix3f m1) {
        matrix.mul(m1);
    }

    public void mulNormalize(javax.vecmath.Matrix3f m1) {
        matrix.mulNormalize(m1);
    }

    public void normalize() {
        matrix.normalize();
    }

    @Override
    public String toString() {
        return matrix.toString();
    }

}
