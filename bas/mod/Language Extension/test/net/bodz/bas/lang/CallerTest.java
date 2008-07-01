package net.bodz.bas.lang;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class CallerTest {

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            Class<?> clazz = Caller.getCallerClass(i);
            String mesg = String.format("%4d - %s", i, clazz);
            System.out.println(mesg);
        }

        assertSame(CallerTest.class, //
                Caller.getCallerClass(1));

        assertSame(CallerTest.class, //
                Caller.getCallerClass());
    }

}
