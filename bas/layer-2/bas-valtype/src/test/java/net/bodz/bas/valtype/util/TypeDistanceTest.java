package net.bodz.bas.valtype.util;

import junit.framework.TestCase;

import net.bodz.bas.valtype.util.TypeDistance;

import org.junit.Test;

public class TypeDistanceTest
        extends TestCase {

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
        int dist = TypeDistance.getClassExtendsCount(A.class, A.class);
        assertEquals(0, dist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClassExtendsCount_Interface()
            throws Exception {
        TypeDistance.getClassExtendsCount(X.class, A.class);
    }

    @Test
    public void testGetClassExtendsCount_1()
            throws Exception {
        int dist = TypeDistance.getClassExtendsCount(A.class, B.class);
        assertEquals(1, dist);
    }

    @Test
    public void testGetClassExtendsCount_2()
            throws Exception {
        int dist = TypeDistance.getClassExtendsCount(A.class, C.class);
        assertEquals(2, dist);
    }

    @Test
    public void testDist()
            throws Exception {
        class D {
            void o(Class<?> decl, Class<?> actual, int expected) {
                int dist = TypeDistance.dist(decl, actual);
                assertEquals(expected, dist);
            }
        }
        D d = new D();

        // A → Y
        d.o(Y.class, A.class, 1);

        // A → Y → X
        d.o(X.class, A.class, 2);

        // B → A → Y → X
        // B → Z2 → Y → X
        d.o(X.class, B.class, 3);

        // C → B → A → Y → X
        // C → Z1 → X
        d.o(X.class, C.class, 2);

        // C → B → A → Y
        // C → B → Z2 → Y
        // C → Z1 → Y
        d.o(Y.class, C.class, 2);

    }

    static Class<?>[] _(Class<?>... types) {
        return types;
    }

    @Test
    public void testDistSum()
            throws Exception {
        class D {
            void o(Class<?>[] decl, Class<?>[] actual, int expected) {
                int dist = TypeDistance.dist(decl, actual);
                assertEquals(expected, dist);
            }
        }
        D d = new D();

        // illegal numbers
        d.o(_(A.class), _(B.class, C.class), -1);

        // B → A
        // C → B
        d.o(_(A.class, B.class), _(B.class, C.class), 2);

        // A → Y → X
        // C → Z1 → Y
        d.o(_(X.class, Y.class), _(A.class, C.class), 4);
    }

}
