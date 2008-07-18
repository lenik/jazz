package net.bodz.bas.types.util;

import static net.bodz.bas.test.TestDefs.ArrayEQU;
import static net.bodz.bas.test.TestDefs.END;

import java.util.Arrays;

import net.bodz.bas.lang.Closure;
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
                buf.append(", ");
            buf.append(c.getSimpleName());
        }
        return buf.toString();
    }

    @Test
    public void testTYPE_HIER() {

        final TestEval<Class<?>[]> sort = new TestEval<Class<?>[]>() {
            public Object eval(Class<?>[] input) throws Throwable {
                Class<?>[] sort = Arrays.copyOf(input, input.length);
                Arrays.sort(sort, Comparators.TYPE_HIER);
                System.out.print(vstr(input));
                System.out.print(" => ");
                System.out.println(vstr(sort));
                return sort;
            }
        };

        final Class<?>[] cv = { Root.class, Bar.class, ZBar.class, Foo.class,
                AFoo.class };

        final Class<?>[] iv = { RootI.class, BarI.class, ZBarI.class,
                FooI.class, AFooI.class };
        Perms.iterate(cv, new Closure<Class<?>[]>() {
            @Override
            public void execute(Class<?>[] perm) {
                TestDefs.tests(sort, //
                        ArrayEQU(perm, cv), //
                        END);
            }
        });

        Perms.iterate(iv, new Closure<Class<?>[]>() {
            @Override
            public void execute(Class<?>[] perm) {
                TestDefs.tests(sort, //
                        ArrayEQU(perm, iv), //
                        END);
            }
        });

    }
}
