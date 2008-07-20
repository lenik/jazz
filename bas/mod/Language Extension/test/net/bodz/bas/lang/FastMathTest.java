package net.bodz.bas.lang;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class FastMathTest {

    @Test
    public void testOnesInt() {
        TestDefs.tests(new TestEval<Integer>() {
            public Object eval(Integer input) throws Throwable {
                return FastMath.ones(input);
            }
        }, //
                EQ(0, 0), //
                EQ(0xFFFFFFFF, 32), //
                EQ(0x00000001, 1), //
                EQ(0x00101010, 3), //
                EQ(0x88888888, 8), //
                END);
    }

    @Test
    public void testZerosInt() {
        TestDefs.tests(new TestEval<Integer>() {
            public Object eval(Integer input) throws Throwable {
                return FastMath.zeros(input);
            }
        }, //
                EQ(0, 32), //
                EQ(0xFFFFFFFF, 0), //
                EQ(0x00000001, 31), //
                EQ(0x00101010, 29), //
                EQ(0x88888888, 24), //
                END);
    }

}
