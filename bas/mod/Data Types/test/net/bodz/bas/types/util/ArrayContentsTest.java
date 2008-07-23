package net.bodz.bas.types.util;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQU;
import static net.bodz.bas.types.util.ArrayOps.Ints;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class ArrayContentsTest {

    @Test
    public void test1() {
        final Map<Object, Object> map = new HashMap<Object, Object>();
        int[] a = { 1, 2, 3 };
        int[] b = { 5, 6 };
        int[] c = { 1, 2, 3 };
        Object A = Ints.contents(a);
        Object B = Ints.contents(b);
        Object C = Ints.contents(c);

        map.put(a, "a");
        map.put(b, "b");

        map.put(A, "A");
        map.put(B, "B");

        TestDefs.tests(new TestEval<Object>() {
            public Object eval(Object input) throws Throwable {
                return map.get(input);
            }
        }, //
                EQU(a, "a"), //
                EQU(c, null), //
                EQU(A, "A"), //
                EQU(C, "A"), //
                END);
    }

}
