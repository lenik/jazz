package net.bodz.geom.transform;

import javax.vecmath.Matrix3f;
import javax.vecmath.Vector2f;


public class MatViewTransformer2f extends AbstractTransformer2f implements ViewTransformer2f {

    public Matrix3f matrix;

    public MatViewTransformer2f() {
        matrix = Mats.ident3f.clone();
    }

    public MatViewTransformer2f(javax.vecmath.Matrix3f matrix3f) {
        matrix = new Matrix3f(matrix3f);
    }

    public MatViewTransformer2f(Matrix3f matrix3f, boolean copy) {
        if (copy)
            matrix = new Matrix3f(matrix3f);
        else
            matrix = matrix3f;
    }

    public MatViewTransformer2f(float x00, float x01, float x02, float x10, float x11, float x12,
            float x20, float x21, float x22) {
        matrix = new Matrix3f(x00, x01, x02, x10, x11, x12, x20, x21, x22);
    }

    public MatViewTransformer2f(Matrix2f matrix2f) {
        matrix = new Matrix3f(matrix2f.x00, matrix2f.x01, 0.0f, matrix2f.x10, matrix2f.x11, 0.0f,
                0.0f, 0.0f, 1.0f);
    }

    public MatViewTransformer2f(float x00, float x01, float x10, float x11) {
        matrix = new Matrix3f(x00, x01, 0.0f, x10, x11, 0.0f, 0.0f, 0.0f, 1.0f);
    }

    // -o Transformer

    @Override
    public void mul(Transformer2f suffix) {
        assert suffix instanceof MatViewTransformer2f;
        MatViewTransformer2f mat = (MatViewTransformer2f) suffix;
        matrix.mul(mat.matrix);
    }

    @Override
    public void mulBy(Transformer2f prefix) {
        assert prefix instanceof MatViewTransformer2f;
        MatViewTransformer2f mat = (MatViewTransformer2f) prefix;
        mat.matrix.mul(matrix);
        matrix = mat.matrix;
    }

    @Override
    public void invert() {
        matrix.invert();
    }

    @Override
    public void transform(Vector2f vector) {
        matrix.transform(vector);
    }

    @Override
    public void invert(Vector2f vector) {
        Matrix3f inv = matrix.clone();
        inv.invert();
        inv.transform(vector);
    }

    // -o ViewTransformer

    public void translateX(float x) {
        matrix.mul(ColMats.translate3f(x, 0.0f));
    }

    public void translateY(float y) {
        matrix.mul(ColMats.translate3f(0.0f, y));
    }

    public void translate(float x, float y) {
        matrix.mul(ColMats.translate3f(x, y));
    }

    public void translate(Vector2f dv) {
        matrix.mul(ColMats.translate3f(dv.x, dv.y));
    }

    public void scale(Vector2f kv) {
        matrix.mul(ColMats.scale3f(kv.x, kv.y));
    }

    public void scale(float kx, float ky) {
        matrix.mul(ColMats.scale3f(kx, ky));
    }

    public void scaleX(float k) {
        matrix.mul(ColMats.scale3f(k, 1.0f));
    }

    public void scaleY(float k) {
        matrix.mul(ColMats.scale3f(1.0f, k));
    }

    public void rotate(float angle) {
        matrix.mul(ColMats.rotate3f(angle));
    }

    @Override
    public String toString() {
        return matrix.toString();
    }
}
