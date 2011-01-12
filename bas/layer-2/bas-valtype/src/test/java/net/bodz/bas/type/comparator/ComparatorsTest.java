package net.bodz.bas.type.comparator;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import junit.framework.TestCase;
import net.bodz.bas.closure.alt.Proc1;
import net.bodz.bas.combina.Permutation;
import net.bodz.bas.type.comparator.ClassComparator;

import org.junit.Test;

public class ComparatorsTest
        extends TestCase {

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
            extends IF {
    }

    static interface IF_P_Q
            extends IF_P {
    }

    static interface IF_U
            extends IF {
    };

    static interface IF_U_V
            extends IF_U {
    }

    static String joinSimpleNames(Class<?>[] typeArray) {
        StringBuffer buf = null;
        for (Class<?> c : typeArray) {
            if (buf == null)
                buf = new StringBuffer();
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

        Permutation.iterate(expectedClassOrder, new Proc1<Class<?>[]>() {
            @Override
            public void exec(Class<?>[] eachOrder) {
                sortTypes(eachOrder, expectedClassOrder);
            }
        });
    }

    // @Test
    public void testSortInterfaceTypes() {
        final Class<?>[] expectedInterfaceOrder = { IF.class, IF_P.class, IF_P_Q.class, IF_U.class, IF_U_V.class };

        Permutation.iterate(expectedInterfaceOrder, new Proc1<Class<?>[]>() {
            @Override
            public void exec(Class<?>[] eachOrder) {
                sortTypes(eachOrder, expectedInterfaceOrder);
            }
        });
    }

}
