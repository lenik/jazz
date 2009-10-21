package net.bodz.bas.types.util;

import static net.bodz.bas.types.util.ArrayOps.Ints;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.lang.IntMath;
import net.bodz.bas.lang2.Proc1;
import net.bodz.bas.util.LogTerm;
import net.bodz.bas.util.LogTerms;

import org.junit.Test;

public class PermsTest {

    static LogTerm logger = LogTerms.get(1);
    int            count  = 0;

    public PermsTest() {
        logger.setLevel(LogTerm.INFO);
    }

    @Test
    public void testIterate() {
        final String orig = "654321"; //$NON-NLS-1$
        final char[] array = orig.toCharArray();
        final Set<String> instances = new HashSet<String>();

        Perms.iterate(array, new Proc1<char[]>() {
            @Override
            public void exec(char[] inst) {
                count++;
                // System.out.println(cc);
                String s = new String(inst);
                assertEquals("iter-" + count, orig.length(), inst.length); //$NON-NLS-1$
                instances.add(s);
                int pos = s.indexOf('1');
                s = s.replaceAll("[^1]", "0"); //$NON-NLS-1$ //$NON-NLS-2$
                int bin = Integer.parseInt(s, 2);
                Terminal dbg = logger.debug();
                dbg.f("%8d - %8d - %8d\n", count, pos, bin); //$NON-NLS-1$
            }
        });
        int expectedCount = IntMath.fac(orig.length()).intValue();
        assert expectedCount == 720;
        assertEquals("iterates", expectedCount, count); //$NON-NLS-1$
        assertEquals("unified", expectedCount, instances.size()); //$NON-NLS-1$
        assertEquals("modified", orig, new String(array)); //$NON-NLS-1$
    }

    static class TestPermEval {
        char[] src;
        char[] dst;

        public TestPermEval(char[] src) {
            this.src = src;
            this.dst = new char[src.length];
        }

        String eval(Integer ord) {
            Perms.perm(ord, src, dst);
            return new String(dst);
        }

        void o(Integer ord, String expected) {
            Perms.perm(ord, src, dst);
            String actual = new String(dst);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testPerm1() {
        TestPermEval d = new TestPermEval("1".toCharArray()); // //$NON-NLS-1$
        d.o(0, "1"); // //$NON-NLS-1$
    }

    @Test
    public void testPerm2() {
        TestPermEval d = new TestPermEval("12".toCharArray()); // //$NON-NLS-1$
        d.o(0, "12"); // //$NON-NLS-1$
        d.o(1, "21"); // //$NON-NLS-1$
    }

    @Test
    public void testPerm3() {
        TestPermEval d = new TestPermEval("123".toCharArray()); // //$NON-NLS-1$
        d.o(0 * 2 + 0, "123"); // 1 //$NON-NLS-1$
        d.o(0 * 2 + 1, "132"); // //$NON-NLS-1$
        d.o(1 * 2 + 0, "213"); // //$NON-NLS-1$
        d.o(1 * 2 + 1, "231"); // //$NON-NLS-1$
        d.o(2 * 2 + 0, "312"); // //$NON-NLS-1$
        d.o(2 * 2 + 1, "321"); // //$NON-NLS-1$
        // cycles
        d.o(3 * 6 + 0 * 2 + 0, "123"); // //$NON-NLS-1$
        d.o(3 * 6 + 0 * 2 + 1, "132"); // //$NON-NLS-1$
        d.o(3 * 6 + 1 * 2 + 0, "213"); //  //$NON-NLS-1$
        d.o(3 * 6 + 1 * 2 + 1, "231"); //  //$NON-NLS-1$
        d.o(3 * 6 + 2 * 2 + 0, "312"); // 11 //$NON-NLS-1$
        d.o(3 * 6 + 2 * 2 + 1, "321"); // //$NON-NLS-1$
    }

    @Test
    public void testPerm4() {
        TestPermEval d = new TestPermEval("1234".toCharArray()); // //$NON-NLS-1$
        d.o(0 * 6 + 0 * 2 + 0, "1234"); // 1 //$NON-NLS-1$
        d.o(0 * 6 + 0 * 2 + 1, "1243"); // //$NON-NLS-1$
        d.o(0 * 6 + 1 * 2 + 0, "1324"); // //$NON-NLS-1$
        d.o(0 * 6 + 1 * 2 + 1, "1342"); // //$NON-NLS-1$
        d.o(0 * 6 + 2 * 2 + 0, "1423"); // //$NON-NLS-1$
        d.o(0 * 6 + 2 * 2 + 1, "1432"); // //$NON-NLS-1$

        d.o(1 * 6 + 0 * 2 + 0, "2134"); // //$NON-NLS-1$
        d.o(1 * 6 + 0 * 2 + 1, "2143"); // //$NON-NLS-1$
        d.o(1 * 6 + 1 * 2 + 0, "2314"); // //$NON-NLS-1$
        d.o(1 * 6 + 1 * 2 + 1, "2341"); // //$NON-NLS-1$
        d.o(1 * 6 + 2 * 2 + 0, "2413"); // 11 //$NON-NLS-1$
        d.o(1 * 6 + 2 * 2 + 1, "2431"); // //$NON-NLS-1$

        d.o(2 * 6 + 0 * 2 + 0, "3124"); //  //$NON-NLS-1$
        d.o(2 * 6 + 0 * 2 + 1, "3142"); // //$NON-NLS-1$
        d.o(2 * 6 + 1 * 2 + 0, "3214"); // //$NON-NLS-1$
        d.o(2 * 6 + 1 * 2 + 1, "3241"); // //$NON-NLS-1$
        d.o(2 * 6 + 2 * 2 + 0, "3412"); // //$NON-NLS-1$
        d.o(2 * 6 + 2 * 2 + 1, "3421"); // //$NON-NLS-1$

        d.o(3 * 6 + 0 * 2 + 0, "4123"); // //$NON-NLS-1$
        d.o(3 * 6 + 0 * 2 + 1, "4132"); // //$NON-NLS-1$
        d.o(3 * 6 + 1 * 2 + 0, "4213"); // 21 //$NON-NLS-1$
        d.o(3 * 6 + 1 * 2 + 1, "4231"); // //$NON-NLS-1$
        d.o(3 * 6 + 2 * 2 + 0, "4312"); // //$NON-NLS-1$
        d.o(3 * 6 + 2 * 2 + 1, "4321"); // //$NON-NLS-1$
    }

    @Test
    public void testPerm5() throws Throwable {
        TestPermEval d = new TestPermEval("12345".toCharArray()); //$NON-NLS-1$
        int last = 0;
        for (int i = 0; i < 120; i++) {
            String perm = (String) d.eval(i);
            int num = Integer.parseInt(perm);
            assertTrue("increase", num > last); //$NON-NLS-1$
            last = num;
        }
    }

    static class TestPermOrd {
        char[] src;

        public TestPermOrd(char[] src) {
            this.src = src;
        }

        public void o(String input, int expected) {
            char[] dst = input.toCharArray();
            int actual = Perms.ord(src, dst);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testOrd1() {
        TestPermOrd d = new TestPermOrd("1".toCharArray());
        d.o("1", 0); // //$NON-NLS-1$
    }

    @Test
    public void testOrd2() {
        TestPermOrd d = new TestPermOrd("12".toCharArray()); // //$NON-NLS-1$
        d.o("12", 0); // //$NON-NLS-1$
        d.o("21", 1); // //$NON-NLS-1$
    }

    @Test
    public void testOrd3() {
        TestPermOrd d = new TestPermOrd("123".toCharArray()); // //$NON-NLS-1$
        d.o("123", 0 * 2 + 0); // 1 //$NON-NLS-1$
        d.o("132", 0 * 2 + 1); // //$NON-NLS-1$
        d.o("213", 1 * 2 + 0); // //$NON-NLS-1$
        d.o("231", 1 * 2 + 1); // //$NON-NLS-1$
        d.o("312", 2 * 2 + 0); // //$NON-NLS-1$
        d.o("321", 2 * 2 + 1); // //$NON-NLS-1$
    }

    @Test
    public void testOrd4() {
        TestPermOrd d = new TestPermOrd("1234".toCharArray()); // //$NON-NLS-1$
        d.o("1234", 0 * 6 + 0 * 2 + 0); // 1 //$NON-NLS-1$
        d.o("1243", 0 * 6 + 0 * 2 + 1); // //$NON-NLS-1$
        d.o("1324", 0 * 6 + 1 * 2 + 0); // //$NON-NLS-1$
        d.o("1342", 0 * 6 + 1 * 2 + 1); // //$NON-NLS-1$
        d.o("1423", 0 * 6 + 2 * 2 + 0); // //$NON-NLS-1$
        d.o("1432", 0 * 6 + 2 * 2 + 1); // //$NON-NLS-1$

        d.o("2134", 1 * 6 + 0 * 2 + 0); // //$NON-NLS-1$
        d.o("2143", 1 * 6 + 0 * 2 + 1); // //$NON-NLS-1$
        d.o("2314", 1 * 6 + 1 * 2 + 0); // //$NON-NLS-1$
        d.o("2341", 1 * 6 + 1 * 2 + 1); // //$NON-NLS-1$
        d.o("2413", 1 * 6 + 2 * 2 + 0); // 11 //$NON-NLS-1$
        d.o("2431", 1 * 6 + 2 * 2 + 1); // //$NON-NLS-1$

        d.o("3124", 2 * 6 + 0 * 2 + 0); //  //$NON-NLS-1$
        d.o("3142", 2 * 6 + 0 * 2 + 1); // //$NON-NLS-1$
        d.o("3214", 2 * 6 + 1 * 2 + 0); // //$NON-NLS-1$
        d.o("3241", 2 * 6 + 1 * 2 + 1); // //$NON-NLS-1$
        d.o("3412", 2 * 6 + 2 * 2 + 0); // //$NON-NLS-1$
        d.o("3421", 2 * 6 + 2 * 2 + 1); // //$NON-NLS-1$

        d.o("4123", 3 * 6 + 0 * 2 + 0); // //$NON-NLS-1$
        d.o("4132", 3 * 6 + 0 * 2 + 1); // //$NON-NLS-1$
        d.o("4213", 3 * 6 + 1 * 2 + 0); // 21 //$NON-NLS-1$
        d.o("4231", 3 * 6 + 1 * 2 + 1); // //$NON-NLS-1$
        d.o("4312", 3 * 6 + 2 * 2 + 0); // //$NON-NLS-1$
        d.o("4321", 3 * 6 + 2 * 2 + 1); // //$NON-NLS-1$
    }

    @Test
    public void testIterOrd() {
        final char[] src = "1234".toCharArray(); //$NON-NLS-1$
        final List<Integer> ords = new ArrayList<Integer>();
        Perms.iterate(src, new Proc1<char[]>() {
            @Override
            public void exec(char[] inst) {
                int ord = Perms.ord(src, inst);
                ords.add(ord);
            }
        });
        int[] expecteds = { 0, 1, 3, 2, 4, 5, 9, 8, 10, 11, 6, 7, 16, 17, 12, 13, 15, 14, 18, 19,
                21, 20, 22, 23, };
        int[] actuals = Ints.toArray(ords);
        assertArrayEquals(expecteds, actuals);
    }

}
