package net.bodz.bas.c.type;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.type.TypeMathTest.A;
import net.bodz.bas.c.type.TypeMathTest.B;
import net.bodz.bas.c.type.TypeMathTest.C;
import net.bodz.bas.c.type.TypeMathTest.X;
import net.bodz.bas.c.type.TypeMathTest.Y;

public class TypeSpaceTest
        extends Assert {

    @Test
    public void testDist()
            throws Exception {
        final TypeSpace typeSpace = TypeSpace.getDefault();

        class D {
            void o(Class<?> decl, Class<?> actual, int expected) {
                int dist = typeSpace.dist(decl, actual);
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

    static Class<?>[] __(Class<?>... types) {
        return types;
    }

    @Test
    public void testDistSum()
            throws Exception {
        final TypeSpace typeSpace = TypeSpace.getDefault();

        class D {
            void o(Class<?>[] decl, Class<?>[] actual, int expected) {
                int dist = typeSpace.dist(decl, actual);
                assertEquals(expected, dist);
            }
        }
        D d = new D();

        // illegal numbers
        d.o(__(A.class), __(B.class, C.class), -1);

        // B → A
        // C → B
        d.o(__(A.class, B.class), __(B.class, C.class), 2);

        // A → Y → X
        // C → Z1 → Y
        d.o(__(X.class, Y.class), __(A.class, C.class), 4);
    }

}
