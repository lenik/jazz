package net.bodz.bas.c.type;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class TypeGCDTest
        extends Assert {

    @Test
    public void testGcdClassOfQClassOfQ() {
        class D {
            void o(Class<?>[] types, Class<?> expected) {
                Class<?> actual = TypeGCD.getCommonSuperclass(Arrays.asList(types));
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
                Class<?>[] actual = TypeGCD.getCommonInterfaces(types[0], types[1], true);
                assertArrayEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(new Class<?>[] { TreeSet.class, HashSet.class, }, //
                new Class<?>[] { AbstractSet.class }); //
        d.o(new Class<?>[] { SortedSet.class, HashSet.class, }, //
                new Class<?>[] { Set.class }); //
    }

}
