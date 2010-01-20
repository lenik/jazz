package net.bodz.bas.collection.comparator;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import net.bodz.bas.closure.alt.Proc1;
import net.bodz.bas.combina.Permutation;

import org.junit.Test;

public class ComparatorsTest {

    class Root {
    }

    class Bar
            extends Root {
    }

    class ZBar
            extends Bar {
    }

    class Foo
            extends Root {
    };

    class AFoo
            extends Foo {
    }

    interface RootI {
    }

    interface BarI
            extends RootI {
    }

    interface ZBarI
            extends BarI {
    }

    interface FooI
            extends RootI {
    };

    interface AFooI
            extends FooI {
    }

    static String vstr(Class<?>[] v) {
        StringBuffer buf = null;
        for (Class<?> c : v) {
            if (buf == null)
                buf = new StringBuffer();
            else
                buf.append(", ");
            buf.append(c.getSimpleName());
        }
        return buf.toString();
    }

    @Test
    public void testTYPE_HIER() {
        class D {
            void o(Class<?>[] input, Class<?>[] expected) {
                Class<?>[] typeHierOrder = Arrays.copyOf(input, input.length);
                Arrays.sort(typeHierOrder, ClassComparator.getInstance());
                System.out.print(vstr(input));
                System.out.print(" => ");
                System.out.println(vstr(typeHierOrder));
                Class<?>[] actual = typeHierOrder;
                assertArrayEquals(expected, actual);
            }
        }
        final D d = new D();

        final Class<?>[] correct = { Root.class, Bar.class, ZBar.class, Foo.class, AFoo.class };

        final Class<?>[] iv = { RootI.class, BarI.class, ZBarI.class, FooI.class, AFooI.class };
        Permutation.iterate(correct, new Proc1<Class<?>[]>() {
            @Override
            public void exec(Class<?>[] everyCondition) {
                d.o(everyCondition, correct);
            }
        });

        Permutation.iterate(iv, new Proc1<Class<?>[]>() {
            @Override
            public void exec(Class<?>[] perm) {
                d.o(perm, iv);
            }
        });
    }

}
