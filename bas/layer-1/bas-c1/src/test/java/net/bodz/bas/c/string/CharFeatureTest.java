package net.bodz.bas.c.string;

import java.lang.reflect.Array;

import org.junit.Assert;
import org.junit.Test;

public class CharFeatureTest
        extends Assert {

    static String join(String delim, Object array) {
        int len = Array.getLength(array);
        StringBuilder buf = new StringBuilder(len * 30);
        for (int i = 0; i < len; i++) {
            if (i != 0)
                buf.append(delim);
            Object component = Array.get(array, i);
            buf.append(component);
        }
        return buf.toString();
    }

    @Test
    public void testC2N() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        for (int t = 0; t < 2; t++) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int n = CharFeature.c2n[c];
                assertEquals(n, 10 + i);
            }
            s = s.toUpperCase();
        }
    }

    @Test
    public void generateRadixes() {
        int[] intv = new int[CharFeature.maxRadix + 1];
        int[] longv = new int[CharFeature.maxRadix + 1];

        for (int i = 1; i <= CharFeature.maxRadix; i++) {
            double r = Math.log(16) / Math.log(i);
            int ints = (int) (8 * r);
            int longs = (int) (16 * r);
            int intsMax = (int) Math.ceil(8 * r);
            int longsMax = (int) Math.ceil(16 * r);
            if (ints == intsMax)
                ints = -ints;
            if (longs == longsMax)
                longs = -longs;
            intv[i] = ints;
            longv[i] = longs;
        }

        System.out.println("intDigits = { " + join(", ", intv) + " }; ");
        System.out.println("longDigits = { " + join(", ", longv) + " }; ");

        int[] intvExpected = { 0, -2147483647, -32, 20, -16, 13, 12, 11, 10, 10, 9, 9, 8, 8, 8, 8, -8, 7, 7, 7, 7, 7,
                7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6 };
        int[] longvExpected = { 0, -2147483647, -64, 40, -32, 27, 24, 22, 21, 20, 19, 18, 17, 17, 16, 16, -16, 15, 15,
                15, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 12, 12 };

        assertArrayEquals(intvExpected, intv);
        assertArrayEquals(longvExpected, longv);
    }

    @Test
    public void testGetDigits() {
        // may be error because of epsilon problems.
        class D {
            void o(Integer input, int expected) {
                int actual = CharFeature.getDigits(10, input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(1, 1); //
        d.o(9, 1); //
        d.o(10, 2); //
        d.o(11, 2); //
        d.o(99, 2); //
        d.o(100, 3); //
        d.o(101, 3); //
        d.o(999, 3); //
        d.o(1000, 4); //
        d.o(10000, 5); //
        d.o(100000, 6); //
        d.o(1000000, 7); //
        d.o(10000000, 8); //
        d.o(100000000, 9); //
        d.o(999999999, 9); //
        d.o(1000000000, 10); //
        d.o(1000000001, 10); //
        d.o(Integer.MAX_VALUE, 10); // 64-bit..
    }

}
