package net.bodz.bas.test;

import static net.bodz.bas.test.TestDefs.EQ;

import org.junit.Test;

public class TestDefsTest {

    @Test
    public void test1() {
        TestDefs.tests(new TestEval<Integer>() {
            @Override
            public Object eval(Integer input) throws Throwable {
                return input;
            }
        }, //
                EQ("example +", 1 + 1, 2), //
                EQ("example -", 1 - 1, 0), //
                EQ("example *", 1 * 1, 1), //
                EQ("example /", 1 / 1, 1));
    }

}
