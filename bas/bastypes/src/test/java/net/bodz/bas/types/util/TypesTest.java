package net.bodz.bas.types.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
        class D {
            void o(Class<?>[] input, String expected) {
                String actual = Types.joinNames(", ", true, input); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(new Class<?>[] { java.util.List.class, java.lang.String.class }, //
                "List, String"); //$NON-NLS-1$
    }

    @Test
    public void testGcdClassOfQClassOfQ() {
        class D {
            void o(Class<?>[] types, Class<?> expected) {
                Class<?> actual = Types.gcd(Arrays.asList(types));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(new Class<?>[] { TreeSet.class, HashSet.class, }, //
                AbstractSet.class); //
        d.o(new Class<?>[] { SortedSet.class, HashSet.class, }, //
                null); //
    }

    @Test
    public void testIgcdClassOfQClassOfQ() {
        class D {
            void o(Class<?>[] types, Class<?>[] expected) {
                Class<?>[] actual = Types.igcd(types[0], types[1], true);
                assertArrayEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(new Class<?>[] { TreeSet.class, HashSet.class, }, //
                new Class<?>[] { AbstractSet.class }); //
        d.o(new Class<?>[] { SortedSet.class, HashSet.class, }, //
                new Class<?>[] { Set.class }); //
    }

    @Test
    public void testFindMethods() {
        // fail("Not yet implemented");
    }

}
