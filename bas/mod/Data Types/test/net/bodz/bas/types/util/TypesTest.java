package net.bodz.bas.types.util;

import static net.bodz.bas.test.TestDefs.ArrayEQU;
import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQU;
import static org.junit.Assert.assertEquals;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
import net.bodz.bas.test._TestEval;

import org.junit.Test;

public class TypesTest {

    static int seq = 0;

    static class A {
        static {
            seq++;
        }
    }

    @Test
    public void testLoad() {
        assertEquals("seq before load", 0, seq); //$NON-NLS-1$
        Types.load(A.class);
        assertEquals("seq after load", 1, seq); //$NON-NLS-1$
        Types.load(A.class);
        assertEquals("only load once", 1, seq); //$NON-NLS-1$
    }

    @Test
    public void testJoinNamesClassOfQArray() {
        TestDefs.tests(new TestEval<Class<?>[]>() {
            public Object eval(Class<?>[] input) throws Throwable {
                return Types.joinNames(", ", true, input); //$NON-NLS-1$
            }
        }, //
                EQU(new Class<?>[] { java.util.List.class,
                        java.lang.String.class }, //
                        "List, String"), // //$NON-NLS-1$
                END);
    }

    @Test
    public void testGcdClassOfQClassOfQ() {
        TestDefs.tests(new _TestEval<Class<?>[]>() {
            public Object eval(Class<?>[] types) throws Throwable {
                if (isBreakpoint())
                    System.err.println(Types.joinNames(types));
                return Types.gcd(Arrays.asList(types));
            }
        }, //
                EQU(new Class<?>[] { TreeSet.class, HashSet.class, }, //
                        AbstractSet.class), //
                EQU(new Class<?>[] { SortedSet.class, HashSet.class, }, //
                        null), //
                END);
    }

    @Test
    public void testIgcdClassOfQClassOfQ() {
        TestDefs.tests(new _TestEval<Class<?>[]>() {
            public Object eval(Class<?>[] types) throws Throwable {
                if (isBreakpoint())
                    System.err.println(Types.joinNames(types));
                return Types.igcd(types[0], types[1], true);
            }
        }, //
                ArrayEQU(new Class<?>[] { TreeSet.class, HashSet.class, }, //
                        new Class<?>[] { AbstractSet.class }), //
                ArrayEQU(new Class<?>[] { SortedSet.class, HashSet.class, }, //
                        new Class<?>[] { Set.class }), //
                END);
    }

    @Test
    public void testFindMethods() {
        // fail("Not yet implemented");
    }

}
