package net.bodz.bas.lang.util;

import junit.framework.TestCase;
import net.bodz.bas.exceptions.IllegalUsageError;

import org.junit.Test;

public class VarArgcMethodTest
        extends TestCase {

    public int play() {
        return 0;
    }

    public int play(String s) {
        return 1;
    }

    public int play(Class<?> clazz, Double d) {
        return 2;
    }

    public int play(float a, double b, int c) {
        return 3;
    }

    public int bad() {
        return 0;
    }

    public int bad(int n) {
        return 1;
    }

    public int bad(float f) {
        return 1;
    }

    VarArgcMethod playf;
    {
        playf = new VarArgcMethod("play", Members.publicMethods(VarArgcMethodTest.class, "play"));
    }

    @Test
    public void test1()
            throws Exception {
        assertEquals(0, playf.invoke(this));
        assertEquals(1, playf.invoke(this, "Hello"));
        assertEquals(2, playf.invoke(this, null, 3.14));
        assertEquals(3, playf.invoke(this, 1.0f, 3.14, 100));
    }

    @Test
    public void testParser()
            throws Exception {
        assertEquals(0, playf.invoke(this));
        assertEquals(1, playf.invoke(this, "Hello"));
        assertEquals(2, playf.invoke(this, "java.util.List", "3.14"));
        assertEquals(3, playf.invoke(this, "1.0", "3.14", "100"));
    }

    @Test(expected = IllegalUsageError.class)
    public void testBad()
            throws Exception {
        new VarArgcMethod("bad", Members.publicMethods(//
                VarArgcMethodTest.class, "bad"));
    }

}
