package net.bodz.bas.text.model;

import net.bodz.bas.primitive.RealMath;

public class CharFeature {

    public static final byte[] c2n = new byte[] {//
    // 0 1 2 3 4 5 6 7 8 9 A B C D E F
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, 45, -1, 43, -1, 92, // +-, /->\, !->!
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, 62, -1, 60, 63, // <>, ?->?
            -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, // A-O
            25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 93, 47, 91, -1, -1, // P-Z, [], \->/
            -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, // a-o
            25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1, // p-z
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, //
    };

    private static final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final char[] n2cu = alphabet.toCharArray();
    public static final char[] n2cl = alphabet.toLowerCase().toCharArray();

    public static final int maxRadix = 32;
    public static final int intDigits[];
    public static final int longDigits[];
    static {
        intDigits = new int[] { 0, -2147483647, -32, 20, -16, 13, 12, 11, 10, 10, 9, 9, 8, 8, 8, 8, -8, 7, 7, 7, 7, 7,
                7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6 };
        longDigits = new int[] { 0, -2147483647, -64, 40, -32, 27, 24, 22, 21, 20, 19, 18, 17, 17, 16, 16, -16, 15, 15,
                15, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 12, 12 };
    }

    private static int r2dig(double r) {
        int safe = RealMath.roundFloor(r);
        int max = RealMath.roundCeil(r);
        if (max == safe)
            return -safe;
        return safe;
    }

    public static int getDigits(int radix, int maxValue) {
        if (maxValue == Integer.MAX_VALUE)
            return intDigits[radix];
        double r = Math.log(maxValue) / Math.log(radix);
        return r2dig(r);
    }

    public static int getDigits(int radix, long maxValue) {
        if (maxValue == Long.MAX_VALUE)
            return longDigits[radix];
        double r = Math.log(maxValue) / Math.log(radix);
        return r2dig(r);
    }

}
