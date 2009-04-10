package net.bodz.math.mat;

public class Mats {

    public static final Matrix3f ident3f;
    public static final Matrix3f zero3f;
    public static final Matrix3f ones3f;

    static {
        ident3f = new Matrix3f(//
                1.0f, 0.0f, 0.0f, //
                0.0f, 1.0f, 0.0f, //
                0.0f, 0.0f, 1.0f);
        zero3f = new Matrix3f(//
                0.0f, 0.0f, 0.0f, //
                0.0f, 0.0f, 0.0f, //
                0.0f, 0.0f, 0.0f);
        ones3f = new Matrix3f(//
                1.0f, 1.0f, 1.0f, //
                1.0f, 1.0f, 1.0f, //
                1.0f, 1.0f, 1.0f);
    }

}
