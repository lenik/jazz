package net.bodz.math.mat;

/**
 * [ Row ] [ M ]
 */
public class RowMats {

    public static final Matrix3f translate3f(float kx, float ky) {
        Matrix3f mat = ColMats.translate3f(kx, ky);
        mat.transpose();
        return mat;
    }

    public static final Matrix3f scale3f(float kx, float ky) {
        return ColMats.scale3f(kx, ky);
    }

    public static final Matrix3f skew3f(float kx, float ky) {
        Matrix3f mat = ColMats.skew3f(kx, ky);
        mat.transpose();
        return mat;
    }

    public static final Matrix3f rotate3f(float angle) {
        Matrix3f mat = ColMats.rotate3f(angle);
        mat.transpose();
        return mat;
    }

}
