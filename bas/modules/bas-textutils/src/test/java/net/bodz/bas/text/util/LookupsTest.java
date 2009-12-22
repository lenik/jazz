package net.bodz.bas.text.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LookupsTest {

    @Test
    public void testC2N() {
        String s = "abcdefghijklmnopqrstuvwxyz"; //$NON-NLS-1$
        for (int t = 0; t < 2; t++) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int n = Lookups.c2n[c];
                assertEquals(n, 10 + i);
            }
            s = s.toUpperCase();
        }
    }

    @Test
    public void generateRadixs() {
        int[] intv = new int[Lookups.maxRadix + 1];
        int[] longv = new int[Lookups.maxRadix + 1];

        for (int i = 1; i <= Lookups.maxRadix; i++) {
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
        System.out.println(//
                "intDigits = { " + Strings.join(", ", intv) + " }; "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        System.out.println(//
                "longDigits = { " + Strings.join(", ", longv) + " }; "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Test
    public void testGetDigits() {
        // may be error because of epsilon problems.
        class D {
            void o(Integer input, int expected) {
                int actual = Lookups.getDigits(10, input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(1, 0); //
        d.o(9, 0); //
        d.o(10, -1); //
        d.o(11, 1); //
        d.o(99, 1); //
        d.o(100, -2); //
        d.o(101, 2); //
        d.o(999, 2); //
        d.o(1000, -3); //
        d.o(10000, -4); //
        d.o(100000, -5); //
        d.o(1000000, -6); //
        d.o(10000000, -7); //
        d.o(100000000, -8); //
        d.o(999999999, 8); //
        d.o(1000000000, -9); //
        d.o(1000000001, 9); //
        d.o(Integer.MAX_VALUE, 9); // 64-bit..
    }

}
