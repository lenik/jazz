package net.bodz.bas.lang.util;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.commons.exceptions.IllegalUsageError;
import net.bodz.bas.commons.scripting.util.Members;
import net.bodz.bas.commons.scripting.util.VarArgcMethod;

import org.junit.Test;

public class VarArgcMethodTest {

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
        playf = new VarArgcMethod("play", Members.publicMethods( //$NON-NLS-1$
                VarArgcMethodTest.class, "play")); //$NON-NLS-1$
    }

    @Test
    public void test1() throws Exception {
        assertEquals(0, playf.invoke(this));
        assertEquals(1, playf.invoke(this, "Hello")); //$NON-NLS-1$
        assertEquals(2, playf.invoke(this, null, 3.14));
        assertEquals(3, playf.invoke(this, 1.0f, 3.14, 100));
    }

    @Test
    public void testParser() throws Exception {
        assertEquals(0, playf.invoke(this));
        assertEquals(1, playf.invoke(this, "Hello")); //$NON-NLS-1$
        assertEquals(2, playf.invoke(this, "java.util.List", "3.14")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(3, playf.invoke(this, "1.0", "3.14", "100")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Test(expected = IllegalUsageError.class)
    public void testBad() throws Exception {
        new VarArgcMethod("bad", Members.publicMethods(// //$NON-NLS-1$
                VarArgcMethodTest.class, "bad")); //$NON-NLS-1$
    }

}
