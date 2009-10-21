package net.bodz.math.mat;

/**
 * [ M ] [ Col ]'
 */
public class ColMats {

    public static final Matrix3f translate3f(float dx, float dy) {
        return new Matrix3f(//
                1.0f, 0.0f, dx, //
                0.0f, 1.0f, dy, //
                0.0f, 0.0f, 1.0f);
    }

    public static final Matrix3f scale3f(float kx, float ky) {
        return new Matrix3f(//
                kx, 0.0f, 0.0f, //
                0.0f, ky, 0.0f, //
                0.0f, 0.0f, 1.0f);
    }

    public static final Matrix3f skew3f(float kx, float ky) {
        return new Matrix3f(//
                1.0f, kx, 0.0f, //
                ky, 1.0f, 0.0f, //
                0.0f, 0.0f, 1.0f);
    }

    public static final Matrix3f rotate3f(float angle) {
        float S = (float) Math.sin(angle);
        float C = (float) Math.cos(angle);
        return new Matrix3f(//
                C, -S, 0.0f, //
                S, C, 0.0f, //
                0.0f, 0.0f, 1.0f);
    }

}
