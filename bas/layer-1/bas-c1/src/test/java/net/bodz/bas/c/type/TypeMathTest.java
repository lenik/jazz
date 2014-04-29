package net.bodz.bas.c.type;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class TypeMathTest
        extends Assert {

    static class A
            implements Y {
    }

    static class B
            extends A
            implements Z2 {
    }

    static class C
            extends B
            implements Z1 {
    }

    static interface X {
    }

    static interface Y
            extends X {
    }

    static interface Z1
            extends X, Y {
    }

    static interface Z2
            extends Y {
    }

    @Test
    public void testGetClassExtendsCount_Same()
            throws Exception {
        int dist = TypeMath.getClassExtendsCount(A.class, A.class);
        assertEquals(0, dist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClassExtendsCount_Interface()
            throws Exception {
        TypeMath.getClassExtendsCount(X.class, A.class);
    }

    @Test
    public void testGetClassExtendsCount_1()
            throws Exception {
        int dist = TypeMath.getClassExtendsCount(A.class, B.class);
        assertEquals(1, dist);
    }

    @Test
    public void testGetClassExtendsCount_2()
            throws Exception {
        int dist = TypeMath.getClassExtendsCount(A.class, C.class);
        assertEquals(2, dist);
    }

    @Test
    public void testGcdClassOfQClassOfQ() {
        class D {
            void o(Class<?>[] types, Class<?> expected) {
                Class<?> actual = TypeMath.getCommonSuperclass(Arrays.asList(types));
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
                Class<?>[] actual = TypeMath.getCommonInterfaces(types[0], types[1], true);
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
