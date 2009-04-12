package net.bodz.bas.types.util;

import static net.bodz.bas.test.TestDefs.ArrayEQU;
import static net.bodz.bas.test.TestDefs.END;

import java.util.Arrays;

import net.bodz.bas.lang.Proc1;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class ComparatorsTest {

    class Root {
    }

    class Bar extends Root {
    }

    class ZBar extends Bar {
    }

    class Foo extends Root {
    };

    class AFoo extends Foo {
    }

    interface RootI {
    }

    interface BarI extends RootI {
    }

    interface ZBarI extends BarI {
    }

    interface FooI extends RootI {
    };

    interface AFooI extends FooI {
    }

    static String vstr(Class<?>[] v) {
        StringBuffer buf = null;
        for (Class<?> c : v) {
            if (buf == null)
                buf = new StringBuffer();
            else
                buf.append(", "); //$NON-NLS-1$
            buf.append(c.getSimpleName());
        }
        return buf.toString();
    }

    @Test
    public void testTYPE_HIER() {

        final TestEval<Class<?>[]> orderTest = new TestEval<Class<?>[]>() {
            public Object eval(Class<?>[] input) throws Throwable {
                Class<?>[] typeHierOrder = Arrays.copyOf(input, input.length);
                Arrays.sort(typeHierOrder, Comparators.TYPE_HIER);
                System.out.print(vstr(input));
                System.out.print(" => "); //$NON-NLS-1$
                System.out.println(vstr(typeHierOrder));
                return typeHierOrder;
            }
        };

        final Class<?>[] correct = { Root.class, Bar.class, ZBar.class,
                Foo.class, AFoo.class };

        final Class<?>[] iv = { RootI.class, BarI.class, ZBarI.class,
                FooI.class, AFooI.class };
        Perms.iterate(correct, new Proc1<Class<?>[]>() {
            @Override
            public void exec(Class<?>[] everyCondition) {
                TestDefs.tests(orderTest, //
                        ArrayEQU(everyCondition, correct), //
                        END);
            }
        });

        Perms.iterate(iv, new Proc1<Class<?>[]>() {
            @Override
            public void exec(Class<?>[] perm) {
                TestDefs.tests(orderTest, //
                        ArrayEQU(perm, iv), //
                        END);
            }
        });

    }
}
