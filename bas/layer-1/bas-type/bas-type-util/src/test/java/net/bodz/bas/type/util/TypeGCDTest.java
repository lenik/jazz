package net.bodz.bas.type.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class TypeGCDTest {

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
