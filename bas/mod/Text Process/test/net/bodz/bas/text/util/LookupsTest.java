package net.bodz.bas.text.util;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static org.junit.Assert.assertEquals;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class LookupsTest {

    @Test
    public void testC2N() {
        String s = "abcdefghijklmnopqrstuvwxyz";
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
                "intDigits = { " + Strings.join(", ", intv) + " }; ");
        System.out.println(//
                "longDigits = { " + Strings.join(", ", longv) + " }; ");
    }

    @Test
    public void testGetDigits() {
        // may be error because of epsilon problems.
        TestDefs.tests(new _TestEval<Integer>() {
            public Object eval(Integer input) throws Throwable {
                if (isBreakpoint())
                    System.err.println(input);
                return Lookups.getDigits(10, input);
            }
        }, //
                EQ(1, 0), //
                EQ(9, 0), //
                EQ(10, -1), //
                EQ(11, 1), //
                EQ(99, 1), //
                EQ(100, -2), //
                EQ(101, 2), //
                EQ(999, 2), //
                EQ(1000, -3), //
                EQ(10000, -4), //
                EQ(100000, -5), //
                EQ(1000000, -6), //
                EQ(10000000, -7), //
                EQ(100000000, -8), //
                EQ(999999999, 8), //
                EQ(1000000000, -9), //
                EQ(1000000001, 9), //
                EQ(Integer.MAX_VALUE, 9), // 64-bit..
                END);
    }

}
