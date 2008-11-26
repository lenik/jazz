package net.bodz.bas.types.util;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.types.util.ArrayOps.Ints;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.lang.IntMath;
import net.bodz.bas.lang.Proc1;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;

import org.junit.Test;

public class PermsTest {

    ALog L     = ALogs.stdout;
    int  count = 0;

    public PermsTest() {
        L.setLevel(ALog.INFO);
    }

    @Test
    public void testIterate() {
        final String orig = "654321";
        final char[] array = orig.toCharArray();
        final Set<String> instances = new HashSet<String>();

        Perms.iterate(array, new Proc1<char[]>() {
            @Override
            public void exec(char[] inst) {
                count++;
                // System.out.println(cc);
                String s = new String(inst);
                assertEquals("iter-" + count, orig.length(), inst.length);
                instances.add(s);
                int pos = s.indexOf('1');
                s = s.replaceAll("[^1]", "0");
                int bin = Integer.parseInt(s, 2);
                L.x.PF("%8d - %8d - %8d", count, pos, bin);
            }
        });
        int expectedCount = IntMath.fac(orig.length()).intValue();
        assert expectedCount == 720;
        assertEquals("iterates", expectedCount, count);
        assertEquals("unified", expectedCount, instances.size());
        assertEquals("modified", orig, new String(array));
    }

    static class PermEval extends _TestEval<Integer> {
        char[] src;
        char[] dst;

        public PermEval(char[] src) {
            this.src = src;
            this.dst = new char[src.length];
        }

        public Object eval(Integer ord) throws Throwable {
            if (isBreakpoint())
                System.out.println(ord);
            Perms.perm(ord, src, dst);
            return new String(dst);
        }
    }

    @Test
    public void testPerm1() {
        TestDefs.tests(new PermEval("1".toCharArray()), //
                EQ(0, "1"), //
                END);
    }

    @Test
    public void testPerm2() {
        TestDefs.tests(new PermEval("12".toCharArray()), //
                EQ(0, "12"), //
                EQ(1, "21"), //
                END);
    }

    @Test
    public void testPerm3() {
        TestDefs.tests(new PermEval("123".toCharArray()), //
                EQ(0 * 2 + 0, "123"), // 1
                EQ(0 * 2 + 1, "132"), //
                EQ(1 * 2 + 0, "213"), //
                EQ(1 * 2 + 1, "231"), //
                EQ(2 * 2 + 0, "312"), //
                EQ(2 * 2 + 1, "321"), //
                // cycles
                EQ(3 * 6 + 0 * 2 + 0, "123"), //
                EQ(3 * 6 + 0 * 2 + 1, "132"), //
                EQ(3 * 6 + 1 * 2 + 0, "213"), // 
                EQ(3 * 6 + 1 * 2 + 1, "231"), // 
                EQ(3 * 6 + 2 * 2 + 0, "312"), // 11
                EQ(3 * 6 + 2 * 2 + 1, "321"), //
                END);
    }

    @Test
    public void testPerm4() {
        TestDefs.tests(new PermEval("1234".toCharArray()), //
                EQ(0 * 6 + 0 * 2 + 0, "1234"), // 1
                EQ(0 * 6 + 0 * 2 + 1, "1243"), //
                EQ(0 * 6 + 1 * 2 + 0, "1324"), //
                EQ(0 * 6 + 1 * 2 + 1, "1342"), //
                EQ(0 * 6 + 2 * 2 + 0, "1423"), //
                EQ(0 * 6 + 2 * 2 + 1, "1432"), //

                EQ(1 * 6 + 0 * 2 + 0, "2134"), //
                EQ(1 * 6 + 0 * 2 + 1, "2143"), //
                EQ(1 * 6 + 1 * 2 + 0, "2314"), //
                EQ(1 * 6 + 1 * 2 + 1, "2341"), //
                EQ(1 * 6 + 2 * 2 + 0, "2413"), // 11
                EQ(1 * 6 + 2 * 2 + 1, "2431"), //

                EQ(2 * 6 + 0 * 2 + 0, "3124"), // 
                EQ(2 * 6 + 0 * 2 + 1, "3142"), //
                EQ(2 * 6 + 1 * 2 + 0, "3214"), //
                EQ(2 * 6 + 1 * 2 + 1, "3241"), //
                EQ(2 * 6 + 2 * 2 + 0, "3412"), //
                EQ(2 * 6 + 2 * 2 + 1, "3421"), //

                EQ(3 * 6 + 0 * 2 + 0, "4123"), //
                EQ(3 * 6 + 0 * 2 + 1, "4132"), //
                EQ(3 * 6 + 1 * 2 + 0, "4213"), // 21
                EQ(3 * 6 + 1 * 2 + 1, "4231"), //
                EQ(3 * 6 + 2 * 2 + 0, "4312"), //
                EQ(3 * 6 + 2 * 2 + 1, "4321"), //
                END);
    }

    @Test
    public void testPerm5() throws Throwable {
        PermEval permEval = new PermEval("12345".toCharArray());
        int last = 0;
        for (int i = 0; i < 120; i++) {
            String perm = (String) permEval.eval(i);
            int num = Integer.parseInt(perm);
            assertTrue("increase", num > last);
            last = num;
        }
    }

    static class PermOrd extends _TestEval<String> {
        char[] src;

        public PermOrd(char[] src) {
            this.src = src;
        }

        @Override
        public Object eval(String input) throws Throwable {
            char[] dst = input.toCharArray();
            if (isBreakpoint())
                System.out.println(input);
            return Perms.ord(src, dst);
        }
    }

    @Test
    public void testOrd1() {
        TestDefs.tests(new PermOrd("1".toCharArray()), //
                EQ("1", 0), //
                END);
    }

    @Test
    public void testOrd2() {
        TestDefs.tests(new PermOrd("12".toCharArray()), //
                EQ("12", 0), //
                EQ("21", 1), //
                END);
    }

    @Test
    public void testOrd3() {
        TestDefs.tests(new PermOrd("123".toCharArray()), //
                EQ("123", 0 * 2 + 0), // 1
                EQ("132", 0 * 2 + 1), //
                EQ("213", 1 * 2 + 0), //
                EQ("231", 1 * 2 + 1), //
                EQ("312", 2 * 2 + 0), //
                EQ("321", 2 * 2 + 1), //
                END);
    }

    @Test
    public void testOrd4() {
        TestDefs.tests(new PermOrd("1234".toCharArray()), //
                EQ("1234", 0 * 6 + 0 * 2 + 0), // 1
                EQ("1243", 0 * 6 + 0 * 2 + 1), //
                EQ("1324", 0 * 6 + 1 * 2 + 0), //
                EQ("1342", 0 * 6 + 1 * 2 + 1), //
                EQ("1423", 0 * 6 + 2 * 2 + 0), //
                EQ("1432", 0 * 6 + 2 * 2 + 1), //

                EQ("2134", 1 * 6 + 0 * 2 + 0), //
                EQ("2143", 1 * 6 + 0 * 2 + 1), //
                EQ("2314", 1 * 6 + 1 * 2 + 0), //
                EQ("2341", 1 * 6 + 1 * 2 + 1), //
                EQ("2413", 1 * 6 + 2 * 2 + 0), // 11
                EQ("2431", 1 * 6 + 2 * 2 + 1), //

                EQ("3124", 2 * 6 + 0 * 2 + 0), // 
                EQ("3142", 2 * 6 + 0 * 2 + 1), //
                EQ("3214", 2 * 6 + 1 * 2 + 0), //
                EQ("3241", 2 * 6 + 1 * 2 + 1), //
                EQ("3412", 2 * 6 + 2 * 2 + 0), //
                EQ("3421", 2 * 6 + 2 * 2 + 1), //

                EQ("4123", 3 * 6 + 0 * 2 + 0), //
                EQ("4132", 3 * 6 + 0 * 2 + 1), //
                EQ("4213", 3 * 6 + 1 * 2 + 0), // 21
                EQ("4231", 3 * 6 + 1 * 2 + 1), //
                EQ("4312", 3 * 6 + 2 * 2 + 0), //
                EQ("4321", 3 * 6 + 2 * 2 + 1), //
                END);
    }

    @Test
    public void testIterOrd() {
        final char[] src = "1234".toCharArray();
        final List<Integer> ords = new ArrayList<Integer>();
        Perms.iterate(src, new Proc1<char[]>() {
            @Override
            public void exec(char[] inst) {
                int ord = Perms.ord(src, inst);
                ords.add(ord);
            }
        });
        int[] expecteds = { 0, 1, 3, 2, 4, 5, 9, 8, 10, 11, 6, 7, 16, 17, 12,
                13, 15, 14, 18, 19, 21, 20, 22, 23, };
        int[] actuals = Ints.toArray(ords);
        assertArrayEquals(expecteds, actuals);
    }

}
