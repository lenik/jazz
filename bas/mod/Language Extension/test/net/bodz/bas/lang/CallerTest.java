package net.bodz.bas.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class CallerTest {

    @Test
    public void test1() {
        for (int i = -10; i < 10; i++) {
            Class<?> clazz = Caller.getCallerClass(i);
            String mesg = String.format(Messages.getString("CallerTest.0"), i, clazz); //$NON-NLS-1$
            System.out.println(mesg);
        }

        assertSame(CallerTest.class, //
                Caller.getCallerClass(0));

        assertSame(CallerTest.class, //
                Caller.getCallerClass());
    }

    @Test
    public void test2() {
        StackTraceElement stack = Caller.getCallerStack();
        Class<?> clazz = getClass();
        assertEquals(clazz.getName(), stack.getClassName());
        assertEquals(Messages.getString("CallerTest.1"), stack.getMethodName()); //$NON-NLS-1$
    }

}
