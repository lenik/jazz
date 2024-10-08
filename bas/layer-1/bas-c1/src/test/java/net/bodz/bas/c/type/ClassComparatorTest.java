package net.bodz.bas.c.type;

import java.util.Arrays;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.Permutation;

public class ClassComparatorTest
        extends Assert {

    static class C {
    }

    static class C_A
            extends C {
    }

    static class C_A_B
            extends C_A {
    }

    static class C_X
            extends C {
    };

    static class C_X_Y
            extends C_X {
    }

    static interface IF {
    }

    static interface IF_P
            extends
                IF {
    }

    static interface IF_P_Q
            extends
                IF_P {
    }

    static interface IF_U
            extends
                IF {
    };

    static interface IF_U_V
            extends
                IF_U {
    }

    static String joinSimpleNames(Class<?>[] typeArray) {
        StringBuilder buf = null;
        for (Class<?> c : typeArray) {
            if (buf == null)
                buf = new StringBuilder();
            else
                buf.append(", ");
            buf.append(c.getSimpleName());
        }
        return buf.toString();
    }

    void sortTypes(Class<?>[] input, Class<?>[] expected) {
        Class<?>[] array = Arrays.copyOf(input, input.length);

        Arrays.sort(array, ClassComparator.getInstance());

        // System.out.print(joinSimpleNames(input) + " ⇒ " + joinSimpleNames(array));

        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortClassTypes() {
        final Class<?>[] expectedClassOrder = { C.class, C_A.class, C_A_B.class, C_X.class, C_X_Y.class };

        Permutation.iterate(expectedClassOrder, new Consumer<Class<?>[]>() {
            @Override
            public void accept(Class<?>[] eachOrder) {
                sortTypes(eachOrder, expectedClassOrder);
            }
        });
    }

    // @Test
    public void testSortInterfaceTypes() {
        final Class<?>[] expectedInterfaceOrder = { IF.class, IF_P.class, IF_P_Q.class, IF_U.class, IF_U_V.class };

        Permutation.iterate(expectedInterfaceOrder, new Consumer<Class<?>[]>() {
            @Override
            public void accept(Class<?>[] eachOrder) {
                sortTypes(eachOrder, expectedInterfaceOrder);
            }
        });
    }

}
