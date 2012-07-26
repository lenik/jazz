package net.bodz.bas.c.javax.vecmath;

/**
 * [ Row ] [ M ]
 */
public class RowVectors {

    public static final Matrix3f translate3f(float kx, float ky) {
        Matrix3f mat = ColumnVectors.translate3f(kx, ky);
        mat.transpose();
        return mat;
    }

    public static final Matrix3f scale3f(float kx, float ky) {
        return ColumnVectors.scale3f(kx, ky);
    }

    public static final Matrix3f skew3f(float kx, float ky) {
        Matrix3f mat = ColumnVectors.skew3f(kx, ky);
        mat.transpose();
        return mat;
    }

    public static final Matrix3f rotate3f(float angle) {
        Matrix3f mat = ColumnVectors.rotate3f(angle);
        mat.transpose();
        return mat;
    }

}
